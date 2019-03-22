package com.tricolorflower.heartbeat.userinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.userinfo.fragment.UserInfoFragment;
import com.tricolorflower.heartbeat.userinfo.presenter.UserInfoPresenter;


/**
 * @CreateBy qsmaxmin
 * @Date 16/9/18  下午3:28
 * @Description 用户信息模块
 */
public class UserInfoActivity extends QsActivity<UserInfoPresenter> {
    @Override
    public void initData(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        commitFragment(UserInfoFragment.getInstance(extras == null ? new Bundle() : extras));
    }
}
