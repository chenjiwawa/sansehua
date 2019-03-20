package com.zl.dappore.voiceroom.fragment.roomrole.add;

import android.os.Bundle;


public class RoomHolderAddFragment extends RoomRoleAddFragment {

    public static RoomHolderAddFragment getInstance(Bundle extras) {
        RoomHolderAddFragment fragment = new RoomHolderAddFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    protected void setRoomRoleOperationView() {
        super.setRoomRoleOperationView();
    }
}