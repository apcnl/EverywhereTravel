package com.example.apcnl.travel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apcnl.travel.R;
import com.example.apcnl.travel.base.baseactivity;
import com.example.apcnl.travel.net.Constants;
import com.example.apcnl.travel.presenter.Emptypresenter;
import com.example.apcnl.travel.util.ToastUtil;
import com.example.apcnl.travel.view.EmptyView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNameOrSigerActivity extends baseactivity<EmptyView, Emptypresenter> implements EmptyView {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ed_content)
    EditText mEdContent;
    @BindView(R.id.tv_conent_number)
    TextView mTvConentNumber;
    private int mType;

    @Override
    protected Emptypresenter initPresenter() {
        return new Emptypresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_update_name_or_siger;
    }


    @OnClick(R.id.tv_ok)
    public void onClick() {
        if (mType == MainActivity.TYPE_NAME) {
            String name = mEdContent.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("type", 0);
            setResult(Constants.RESULT_CODE, intent);
            finish();
        }
        if (mType == MainActivity.TYPE_SIGER) {
            String name = mEdContent.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("type", 1);
            setResult(Constants.RESULT_CODE, intent);
            finish();
        }
    }

    @Override
    protected void iniView() {
        StatusBarUtil.setLightMode(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mType = getIntent().getIntExtra("type", 0);
        mTvTitle.setText(getIntent().getStringExtra("TabName"));
        mEdContent.setText(getIntent().getStringExtra("Name"));

        mTvConentNumber.setText((27 - mEdContent.getText().toString().length()) + "/27");
    }

    @Override
    protected void iniListener() {
        mEdContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvConentNumber.setText((27 - s.length()) + "/27");
                if (s.length() == 27) {
                    ToastUtil.showShort("字太多，输不了");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick(R.id.img_back)
    public void onClick2() {
        finish();
    }
}
