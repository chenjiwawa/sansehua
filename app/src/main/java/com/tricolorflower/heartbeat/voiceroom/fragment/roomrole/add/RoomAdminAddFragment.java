package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.add;

import android.os.Bundle;
import android.view.View;


public class RoomAdminAddFragment extends RoomRoleAddFragment {

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
}