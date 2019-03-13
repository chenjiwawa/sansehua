package com.zl.dappore.voiceroom.fragment.roomrole;

import android.os.Bundle;
import android.view.View;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentRelativeLayout;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;


public class RoomRoleAddFragment extends QsFragment {

    @Bind(R.id.rl_music_voice_add)
    PercentRelativeLayout rlMusicVoiceAdd;
    @Bind(R.id.rl_admin_voice_add)
    PercentRelativeLayout rlAdminVoiceAdd;
    @Bind(R.id.rl_forbidden_voice_add)
    PercentRelativeLayout rlForbiddenVoiceAdd;
    @Bind(R.id.rl_theme_voice_add)
    PercentRelativeLayout rlThemeVoiceAdd;

    String channelId = "";
    int voiceRole = 0;

    public static RoomRoleAddFragment getInstance(Bundle extras) {
        RoomRoleAddFragment fragment = new RoomRoleAddFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public int layoutId() {
        return R.layout.fragment_room_role_add;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        //TODO
        arguments = new Bundle();
        if (arguments == null) return;

        channelId = arguments.getString(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_CHANNEL_ID);
        voiceRole = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_FAVORITE_REQUEST_VOICE_ROLE);

        L.i(initTag(), " channelId " + channelId + " voiceRole " + voiceRole);
        showContentView();
    }


    public void requstData(int voiceRole) {
    }


    @OnClick({R.id.rl_music_voice_add, R.id.rl_admin_voice_add, R.id.rl_forbidden_voice_add, R.id.rl_theme_voice_add})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_music_voice_add:
                break;
            case R.id.rl_admin_voice_add:
                break;
            case R.id.rl_forbidden_voice_add:
                break;
            case R.id.rl_theme_voice_add:
                break;
        }
    }
}