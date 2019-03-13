package com.zl.dappore.voiceroom.fragment.voiceorole;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.VoiceClientGridFragment;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;


public class VoiceInfoFragment extends QsFragment {

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

    String channelId = "";
    int voiceRole = 0;

    public static VoiceInfoFragment getInstance(Bundle extras) {
        VoiceInfoFragment fragment = new VoiceInfoFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_voice_info;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        VoiceClientGridFragment voiceClientGridFragment = (VoiceClientGridFragment) getChildFragmentManager().findFragmentById(R.id.f_voice_room);
        voiceClientGridFragment.setArguments(arguments);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        loadingClose();
        showContentView();
    }


    public void requstData(int voiceRole) {
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
}