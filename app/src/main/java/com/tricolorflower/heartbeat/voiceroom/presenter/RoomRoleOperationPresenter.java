package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.RoomRoleOperationBarFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.RoomRoleOperationFragment;


public class RoomRoleOperationPresenter extends DapporePresenter<RoomRoleOperationFragment> {


    @ThreadPoint(ThreadType.HTTP)
    public void requestData() {
    }


}