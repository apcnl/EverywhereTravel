package com.example.apcnl.travel.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.bean.UpLoadBean;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.util.SpUtil;
import com.example.apcnl.travel.view.EmptyView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpLoadImgActivity extends baseactivity<EmptyView, Emptypresenter> implements EmptyView {


    private static final String TAG = "UpLoadImgActivity";
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.img_upload_img)
    ImageView mImgUploadImg;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    private PopupWindow mPopupWindow;
    private File mFile;
    private Uri mImageUri;
    private String mUrl;

    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_up_load_img;
    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        String param = (String) SpUtil.getParam(Constants.ME_INFO_IMG, "");
        if (!TextUtils.isEmpty(param)) {
            Glide.with(this).load(param).into(mImg);
        } else {
            mImg.setImageResource(R.drawable.zhanweitu_touxiang);
        }
    }


    private void popImgWindow() {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow();
        }
        View inflate = LayoutInflater.from(UpLoadImgActivity.this).inflate(R.layout.pop_img, null, false);
        mPopupWindow.setContentView(inflate);

        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setOutsideTouchable(true);

        mPopupWindow.showAtLocation(mImg, Gravity.BOTTOM, 100, 100);

        //拍照
        inflate.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        //相册
        inflate.findViewById(R.id.btn_albom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePick();
                mPopupWindow.dismiss();
            }
        });

        //点击取消
        inflate.findViewById(R.id.btn_dimiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }




    private void takePick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //打开相册
            openAlbom();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 200);
        }
    }

    private void openAlbom() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, Constants.ALBOM_CODE);
    }


    @OnClick(R.id.img_upload_img)
    public void onClick() {
        popImgWindow();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {
            } else if (requestCode == 200) {
                openAlbom();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.ALBOM_CODE) {
                Uri imageUri = data.getData();

                //2.将Uri路径转换为File文件
                showLoading();
                File file = getFileFromUri(imageUri, this);

                //3.文件上传
                if (file.exists()) {
                    uploadFile(file);
                }

            }
            if (requestCode == Constants.CAMREA_CODE) {
                //uploadFile(mFile);


            }
        }
    }

    private void uploadFile(File mFile) {

        String url = "http://yun918.cn/study/public/file_upload.php";

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1808B")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(string, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null) {
                            if (upLoadBean.getCode() == 200) {
                                //Toast.makeText(UpLoadImgActivity.this,upLoadBean.getRes(),Toast.LENGTH_SHORT).show();
                                if (upLoadBean.getData().getUrl() != null) {
                                    Glide.with(UpLoadImgActivity.this).load(upLoadBean.getData().getUrl()).into(mImg);
                                    hideLoading();
                                    mTvOk.setVisibility(View.VISIBLE);
                                    mImgUploadImg.setVisibility(View.GONE);
                                    mUrl = upLoadBean.getData().getUrl();
                                }
                            } else {
                                Toast.makeText(UpLoadImgActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void iniListener() {
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListenerTvOk(mUrl);
            }
        });
    }

    private void clickListenerTvOk(String url) {
        Intent intent = new Intent();
        intent.putExtra("img", url);
        setResult(Constants.IMG_RESULT_CODE, intent);
        SpUtil.setParam(Constants.ME_INFO_IMG, url);
        Log.e(TAG, "run: " + url);
        finish();
    }

    private File getFileFromUri(Uri imageUri, Context context) {
        if (imageUri == null) {
            return null;
        }
        switch (imageUri.getScheme()) {
            case "content":
                return getFileFromContentUri(imageUri, context);
            case "file":
                return new File(imageUri.getPath());
            default:
                return null;
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }


    @OnClick(R.id.img_back)
    public void onClick2() {
        finish();
    }
}
