package com.zl.dappore.userinfo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.KeyboardHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.common.utils.EditTextUtil;
import com.zl.dappore.userinfo.presenter.ChangeNicknamePresenter;


public class ChangePsdFragment extends QsFragment<ChangeNicknamePresenter> {

    @Bind(R.id.tv_id_userinfo)
    TextView tvIdUserinfo;
    @Bind(R.id.et_code_userinfo)
    EditText etCodeUserinfo;
    @Bind(R.id.tv_code_userinfo)
    TextView tvCodeUserinfo;
    @Bind(R.id.rl_code_userinfo)
    RelativeLayout rlCodeUserinfo;
    @Bind(R.id.tv_psw_userinfo)
    EditText tvPswUserinfo;
    @Bind(R.id.rl_psw_userinfo)
    RelativeLayout rlPswUserinfo;
    @Bind(R.id.tv_repsw_userinfo)
    EditText tvRepswUserinfo;
    @Bind(R.id.rl_repsw_userinfo)
    RelativeLayout rlRepswUserinfo;

    public static Fragment getInstance() {
        return new ChangePsdFragment();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_change_psd;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        showContentView();
    }

    @Override
    public void onActionBar() {
        super.onActionBar();

        setActivityTitle("修改昵称");
    }

    @OnClick({R.id.tv_code_userinfo})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.tv_code_userinfo:
                break;
            default:
                break;
        }
    }

    public void requestBackPress() {
        getActivity().onBackPressed();
    }
}
