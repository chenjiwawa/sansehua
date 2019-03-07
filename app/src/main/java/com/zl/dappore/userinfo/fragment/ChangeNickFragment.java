package com.zl.dappore.userinfo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


public class ChangeNickFragment extends QsFragment<ChangeNicknamePresenter> {
    
    @Bind(R.id.et_nick_userinfo)
    EditText etNickUserinfo;
    @Bind(R.id.iv_clean_userinfo)
    ImageView ivCleanUserinfo;
    @Bind(R.id.iv_tips_nick_userinfo)
    TextView ivTipsNickUserinfo;


    private String oldNickname;

    public static Fragment getInstance() {
        return new ChangeNickFragment();
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_change_nick;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        oldNickname = UserConfig.getInstance().getName();
        etNickUserinfo.setText(oldNickname);
        etNickUserinfo.setSelection(oldNickname.length());
//        etNickUserinfo.addTextChangedListener(this);
        new EditTextUtil().setTextCNLimit(getActivity(), etNickUserinfo, "昵称过长！", 20, null, new EditTextUtil.EditTextWatcher() {
            @Override
            public void afterChanged() {
//                if (etNickUserinfo != null && ivCleanUserinfo != null && text_submit != null) {
//                    if (!TextUtils.isEmpty(etNickUserinfo.getText())) {
//                        ivCleanUserinfo.setVisibility(View.VISIBLE);
//                        text_submit.setEnabled(true);
//                    } else {
//                        ivCleanUserinfo.setVisibility(View.GONE);
//                        text_submit.setEnabled(false);
//                    }
//
//                }

            }
        });

        showContentView();
    }

    @Override
    public void onActionBar() {
        super.onActionBar();

        KeyboardHelper.showSoftInputDelay(etNickUserinfo);
        setActivityTitle("修改昵称");
    }

    @OnClick({R.id.iv_clean_userinfo})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
//            case R.id.text_submit:
//                String nickName = etNickUserinfo.getText().toString().trim();
//                if (TextUtils.isEmpty(nickName)) {
//                    QsToast.show("昵称为空！");
//                    return;
//                }
//                getPresenter().requestChangeNick(UserConfig.getInstance().getId(), nickName);
//                break;

            case R.id.iv_clean_userinfo:
                etNickUserinfo.setText("");
                break;
            default:
                break;

        }
    }


    public void requestBackPress() {
        getActivity().onBackPressed();
    }

}
