package com.zl.dappore.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.account.LoginActivity;
import com.zl.dappore.account.model.LoginConstant;
import com.zl.dappore.common.event.LoginEvent;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.home.presenter.MinePresenter;
import com.zl.dappore.userinfo.UserInfoActivity;

import org.greenrobot.eventbus.Subscribe;

public class MineFragment extends QsFragment<MinePresenter> {

    @Bind(R.id.iv_logo_mine)
    ImageView ivLogoMine;
    @Bind(R.id.tv_name_mine)
    TextView tvNameMine;
    @Bind(R.id.rl_userinfo_mine)
    RelativeLayout rlUserinfoMine;
    @Bind(R.id.ll_person_mine)
    RelativeLayout llPersonMine;
    @Bind(R.id.ll_message_mine)
    RelativeLayout llMessageMine;
    @Bind(R.id.ll_favorite_mine)
    RelativeLayout llFavoriteMine;
    @Bind(R.id.ll_history_mine)
    RelativeLayout llHistoryMine;
    @Bind(R.id.rl_earn_mine)
    RelativeLayout rlEarnMine;
    @Bind(R.id.rl_task_mine)
    RelativeLayout rlTaskMine;
    @Bind(R.id.rl_feedback_mine)
    RelativeLayout rlFeedbackMine;
    @Bind(R.id.rl_about_mine)
    RelativeLayout rlAboutMine;

    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onActionBar() {
        setActivityTitle("mine");
    }

    public static Fragment getInstance() {
        return new MineFragment();
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        setUserInfoView();
        //TODO
//        getPresenter().checkUpdate(false);
    }

    public void setUserInfoView() {
        if (UserConfig.getInstance().isLogin()) {
            QsHelper.getInstance().getImageHelper().createRequest().load(UserConfig.getInstance().getAvatarUrl()).circleCrop().into(ivLogoMine);
            tvNameMine.setText(UserConfig.getInstance().getName());
        } else {
            tvNameMine.setText("登录/注册");
        }
    }

    @Override
    public boolean isOpenViewState() {
        return false;
    }

    @Subscribe
    public void onEvent(LoginEvent.onLogin event) {
        if (event.mLoginState == LoginEvent.LoginState.STATE_SUCCESS) {
            setUserInfoView();
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }

    @OnClick({R.id.rl_userinfo_mine, R.id.ll_person_mine, R.id.ll_message_mine, R.id.ll_favorite_mine, R.id.ll_history_mine, R.id.rl_earn_mine, R.id.rl_task_mine, R.id.rl_feedback_mine, R.id.rl_about_mine})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_userinfo_mine:
                if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_USERINFO)) {
                    intent2Activity(UserInfoActivity.class);
                }
                break;
            case R.id.ll_person_mine:
                break;
            case R.id.ll_message_mine:
                break;
            case R.id.ll_favorite_mine:
                break;
            case R.id.ll_history_mine:
                break;
            case R.id.rl_earn_mine:
                break;
            case R.id.rl_task_mine:
                break;
            case R.id.rl_feedback_mine:
                break;
            case R.id.rl_about_mine:
                break;
        }
    }

}
