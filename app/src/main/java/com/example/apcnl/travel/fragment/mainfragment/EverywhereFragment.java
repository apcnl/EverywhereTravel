package com.example.apcnl.travel.fragment.mainfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.activity.HomeBundleWebViewActivity;
import com.example.apcnl.travel.activity.MainActivity;
import com.example.apcnl.travel.activity.PathParticularsActivity;
import com.example.apcnl.travel.adapter.MainEveryWhereRlvAdapter;
import com.example.apcnl.travel.base.BaseFragment;
import com.example.apcnl.travel.bean.EveryWhereBean;
import com.example.apcnl.travel.bean.VersionNameBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.net.EveryWhereService;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.presenter.EveryWherepresenter;
import com.example.apcnl.travel.util.InstallUtil;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.util.Tools;
import com.example.apcnl.travel.view.EmptyView;
import com.example.apcnl.travel.view.EveryWhereView;
import com.just.agentweb.LogUtils;
import com.youth.banner.Banner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

/**
 * Created by apcnl on 2019/5/5.
 */

public class EverywhereFragment extends BaseFragment<EveryWhereView, EveryWherepresenter> implements EveryWhereView {

    private static final String TAG = "EverywhereFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private String mToken = "JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g";
    private ArrayList<EveryWhereBean.ResultBean.BannersBean> mBannersList;
    public ArrayList<EveryWhereBean.ResultBean.RoutesBean> mArticleList;
    private MainEveryWhereRlvAdapter mAdapter;

    @Override
    protected EveryWherepresenter initPresenter() {
        return new EveryWherepresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_everywhere;
    }

    @Override
    protected void initView() {
        showLoading();
        mBannersList = new ArrayList<>();
        mArticleList = new ArrayList<>();

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new MainEveryWhereRlvAdapter(getContext(), mBannersList, mArticleList);
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        String param = (String) SpUtil.getParam(Constants.TOKEN, "");
        Log.d(TAG, "initData: "+param);
//        Log.d(TAG, "initData: "+param);SpUtil.getParam(Constants.TOKEN, mToken)+""
        mPresenter.initData(param);
        //版本检测
        mPresenter.VersionNameData(param);
    }

    @Override
    public void onSuccess(EveryWhereBean bean) {
        Log.d(TAG, "onSuccess: "+bean);
        if (bean.getCode() == EveryWhereService.SUCCESS_CODE){
            hideLoading();
            mAdapter.addData(bean);
        }

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onSuccessVersionName(VersionNameBean bean) {
        String version = bean.getResult().getInfo().getVersion();
        Log.d(TAG, "onSuccessVersionName: "+bean.getResult().getInfo().getVersion());
        String versionName = Tools.getVersionName();
        Log.d(TAG, "onSuccessVersionName: "+versionName);
        if (!version.equals(versionName)){
            showAleatDialog(version,versionName);
        }
    }

    /**
     *
     * @param version 服务器版本号
     * @param versionName 当前版本号
     */
    private void showAleatDialog(String version, String versionName) {
        new AlertDialog.Builder(getContext())
                .setTitle("发现新版本")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("最新版本："+version+"\n\n"
                        +"当前版本："+versionName+"\n\n"
                        +"更新内容：\n"+"  1.增加地图功能\n"+"  2.用户界面优化")
                .setNegativeButton("以后再说",null)
                .setPositiveButton("立即升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.downloadAppData();
                    }
                })
                .show();
    }

    @Override
    public void setData(ResponseBody responseBody) {
        InputStream inputStream = responseBody.byteStream();
        long max = responseBody.contentLength();

        saveFile(inputStream,mSd+"/"+"abc.apk",max);
    }

    private void saveFile(InputStream inputStream, final String string, long max) {
        //读写的进度
        long count = 0;
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(string));

            byte[] bys = new byte[1024*10];
            int length = -1;

            while((length = inputStream.read(bys))!=-1){
                outputStream.write(bys,0,length);

                count += length;

                Log.d(TAG, "progress: "+count +"  max:"+max);
            }

            inputStream.close();
            outputStream.close();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toastShort("下载完成");

                    InstallUtil.installApk(getContext(),string);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        mAdapter.setonItemClickListener(new MainEveryWhereRlvAdapter.onItemClickListener() {
            @Override
            public void clickListener(int position) {
                EveryWhereBean.ResultBean.RoutesBean bean = mArticleList.get(position);
                SpUtil.setParam(Constants.PATH_ID,bean.getId());
                startActivity(new Intent(getContext(),PathParticularsActivity.class));
            }
        });
        mAdapter.setonItemBundleClickListener(new MainEveryWhereRlvAdapter.onItemBundleClickListener() {
            @Override
            public void clickListener(int position) {
                EveryWhereBean.ResultBean.RoutesBean bean = mArticleList.get(position);
                String url = bean.getContentURL();
                String title = bean.getTitle();
                Intent intent = new Intent(getContext(), HomeBundleWebViewActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }
}
