package com.zl.dappore.web;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.web.fragment.WebFragment;
import com.zl.dappore.web.presenter.WebViewPresenter;


/**
 * @CreateBy qsmaxmin
 * @Date 16/8/24 下午4:42
 * @Description 网页视图
 */
public class WebViewActivity extends QsABActivity<WebViewPresenter> {

    @Bind(R.id.tv_title) TextView tv_title;//标题

    /**
     * 初始化标题栏布局
     */
    @Override public int actionbarLayoutId() {//new_layout_title_left_right_share
        return R.layout.actionbar_title_back;
    }

    /**
     * 初始化数据
     */
    @Override public void initData(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        getPresenter().readData(extras);// 读数据
    }


    /**
     * 设置标题
     */
    @Override public void setActivityTitle(Object value, int code) {
        tv_title.setText(String.valueOf(value));
        switch (code) {
            default:
                break;
        }
    }


    @OnClick({R.id.ll_back}) public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                applyBack();
                break;
        }
    }



    @Override
    public boolean onKeyDown(KeyEvent event, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            applyBack();
            return true;
        } else {
            return super.onKeyDown(event, keyCode);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void applyBack() {
        WebFragment webFragment = getPresenter().getWebFragment();
        if (webFragment != null) {
            webFragment.performBack();
        } else {
            activityFinish();
        }
    }
}
