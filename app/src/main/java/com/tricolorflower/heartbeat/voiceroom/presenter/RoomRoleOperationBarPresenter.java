package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.EmojiHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.RoomRoleOperationBarFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.emoji.EmojiGridFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseEmojiListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;


public class RoomRoleOperationBarPresenter extends DapporePresenter<RoomRoleOperationBarFragment> {


    @ThreadPoint(ThreadType.HTTP)
    public void requestData() {
    }


}