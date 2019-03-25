package com.tricolorflower.heartbeat.voiceroom.fragment.voicerole;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceRoleInfoPresenter;


public class VoiceRoleInfoFragment extends QsFragment<VoiceRoleInfoPresenter> {

    @Bind(R.id.frame)
    LinearLayout frame;
    @Bind(R.id.iv_img_user)
    ImageView ivImgUser;
    @Bind(R.id.tv_title_user)
    TextView tvTitleUser;
    @Bind(R.id.tv_id_user)
    TextView tvIdUser;
    @Bind(R.id.tv_age_user)
    TextView tvAgeUser;
    @Bind(R.id.tv_horoscope_user)
    TextView tvHoroscopeUser;
    @Bind(R.id.tv_rank_user)
    TextView tvRankUser;
    @Bind(R.id.tv_followers_user)
    TextView tvFollowersUser;
    @Bind(R.id.tv_followings_user)
    TextView tvFollowingsUser;
    @Bind(R.id.rl_item_user)
    RelativeLayout rlItemUser;
    @Bind(R.id.btn_user_homepage)
    Button btnUserHomepage;
    @Bind(R.id.btn_user_message)
    Button btnUserMessage;
    @Bind(R.id.btn_user_gift)
    Button btnUserGift;
    @Bind(R.id.btn_user_following)
    Button btnUserFollowing;

    protected VoiceRole user;//当前用户
    protected VoiceRole data;

    public static VoiceRoleInfoFragment getInstance(Bundle extras) {
        VoiceRoleInfoFragment fragment = new VoiceRoleInfoFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_voice_role_info;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);
        data = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENT_OR_AUDITOR);
        L.i(initTag(), " user " + user + " data " + data);

        if (data != null && data.isVoiceAuditor()) {
            frame.setVisibility(View.GONE);
        }

        loadingClose();
        showContentView();

        requstData(0);
    }


    public void requstData(int voiceRole) {
        L.i(initTag(), " requstData ");
        getPresenter().requstData("", "");
    }

    public void setVoiceRoleInfoView(VoiceRoleList.VoiceRole data) {
        L.i(initTag(), " setVoiceRoleInfoView " + data);

        loadingClose();
        showContentView();

        if (data == null)
            return;

        QsHelper.getInstance().getImageHelper().createRequest().load(data.pic).circleCrop().into(ivImgUser);
        tvTitleUser.setText(data.nickname);
        tvIdUser.setText(data.id);
        tvAgeUser.setText(data.age + "");
        tvHoroscopeUser.setText(data.constellation);
        tvRankUser.setText(data.vip + "");
        tvFollowersUser.setText(data.vip + "");//TODO
        tvFollowingsUser.setText(data.vip + "");
    }

    @OnClick({R.id.iv_img_user, R.id.tv_rank_user, R.id.tv_followers_user, R.id.tv_followings_user, R.id.rl_item_user, R.id.btn_user_homepage, R.id.btn_user_message, R.id.btn_user_gift, R.id.btn_user_following})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.iv_img_user:
                break;
            case R.id.tv_rank_user:
                break;
            case R.id.tv_followers_user:
                break;
            case R.id.tv_followings_user:
                break;
            case R.id.rl_item_user:
                break;
            case R.id.btn_user_homepage:
                break;
            case R.id.btn_user_message:
                break;
            case R.id.btn_user_gift:
                break;
            case R.id.btn_user_following:
                break;
        }
    }

    @Override
    public boolean isOpenViewState() {
        return true;
    }
}