package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.add;

import android.os.Bundle;
import android.view.View;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.percentlayout.PercentRelativeLayout;
import com.tricolorflower.heartbeat.R;


public class RoomAdminAddFragment extends RoomRoleAddFragment {

    @Bind(R.id.rl_music_voice_add)
    protected PercentRelativeLayout rlMusicVoiceAdd;
    @Bind(R.id.rl_admin_voice_add)
    protected PercentRelativeLayout rlAdminVoiceAdd;
    @Bind(R.id.rl_forbidden_voice_add)
    protected PercentRelativeLayout rlForbiddenVoiceAdd;
    @Bind(R.id.rl_theme_voice_add)
    protected PercentRelativeLayout rlThemeVoiceAdd;

    public static RoomAdminAddFragment getInstance(Bundle extras) {
        RoomAdminAddFragment fragment = new RoomAdminAddFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected void setRoomRoleOperationView() {
        super.setRoomRoleOperationView();

        rlMusicVoiceAdd.setVisibility(View.GONE);
        rlThemeVoiceAdd.setVisibility(View.GONE);
    }

    @OnClick({R.id.rl_music_voice_add, R.id.rl_admin_voice_add, R.id.rl_forbidden_voice_add, R.id.rl_theme_voice_add})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.rl_music_voice_add:
                break;
            case R.id.rl_admin_voice_add:
                roomRole2AdminList();
                break;
            case R.id.rl_forbidden_voice_add:
                roomRole2BlackList();
                break;
            case R.id.rl_theme_voice_add:
                break;
        }
    }
}