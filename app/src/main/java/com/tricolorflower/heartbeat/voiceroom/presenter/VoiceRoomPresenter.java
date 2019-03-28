package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VoiceRoomHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.VoiceRoomFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRole;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.CreateVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.EnterVoiceRoomResponse;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoomPresenter extends DapporePresenter<VoiceRoomFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int id) {

        String token = "";
        String token1 = "IlESoZoBS7SKwNJdSsprvalf58DRmwpDSJE23SJ68mSyzXhsxHg9iONIXo4YoIbPsKvzm2Gt1UR0Mz6mqNFYRQ==";
        String token11 = "5Pn1Rp1T0lQGMN5gxXWwlalf58DRmwpDSJE23SJ68mSyzXhsxHg9iFKvLZZ3/rm25hgd//9DZUZ0Mz6mqNFYRQ==";
        if (id==1) {
            token = token1;
        }
        if (id==11) {
            token = token11;
        }

//        RongIM.connect(token, new RongIMClient.ConnectCallback() {
//            @Override
//            public void onTokenIncorrect() {
//                L.e(initTag(), "onTokenIncorrect");
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                L.e(initTag(), "onSuccess userid:" + s);
//                getView().initChatRoom();
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                L.e(initTag(), "onError errorcode:" + errorCode.getValue());
//            }
//        });


        VoiceRole voiceHolder = new VoiceRole();
        voiceHolder.id = 1;
        voiceHolder.name = "name1";
        voiceHolder.position = 0;
        voiceHolder.logo = "http://staging.dappore.com/jak5hfxCGXqhWADNmKf1DM4b";

        List<VoiceRole> voiceRoles = new ArrayList<>();
        VoiceRole voiceRole1 = new VoiceRole();
        voiceRole1.id = 11;
        voiceRole1.name = "name11";
        voiceRole1.logo = "http://staging.dappore.com/VQKLStxifZVFu2Dz3SFxxUjm";
        voiceRole1.position = 1;

        VoiceRole voiceRole2 = new VoiceRole();
        voiceRole2.id = 12;
        voiceRole2.name = "name12";
        voiceRole2.logo = "http://staging.dappore.com/xNdqnHirMbzFYW9BXkmKPZ3n";
        voiceRole2.position = 2;

        voiceRoles.add(voiceRole1);
        voiceRoles.add(voiceRole2);
        for (int i = 0; i < 6; i++) {
            voiceRoles.add(new VoiceRole());
        }

        VoiceRoom data = new VoiceRoom();
        data.voiceRoomId = "1";
        data.voiceRoomName = "我的房间";
        data.voiceRoomGreeting = "问候一下";
        data.voiceRoomAnnounce = "公告一下";

        EnterVoiceRoomResponse responce=new EnterVoiceRoomResponse();
        responce.voiceRoom = data;
        responce.voiceHolder = voiceHolder;
        responce.voiceClients = voiceRoles;
        responce.voiceUserPermission = BaseVoiceRole.PERMISSION_HOLDER;

        setView(responce);
    }

    private void setView(EnterVoiceRoomResponse responce) {
        if (responce == null)
            return;


        getView().setCurrentData(responce);
        getView().updateVoiceRoomView(responce.voiceRoom);
        getView().updateVoiceHolderView(responce.voiceHolder);
        getView().updateVoiceClientGridView(responce.voiceClients);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void createVoiceRoom(String token, int room_type) {
        VoiceRoomHttp http = createHttpRequest(VoiceRoomHttp.class);
        EnterVoiceRoomResponse response = http.createVoiceRoom(new CreateVoiceRoomRequestBody(token, room_type));
        showFailMsg(response);
        if (isSuccess(response)) {
            setView(response);
        } else {
            getView().showErrorView();
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void joinVoiceRoom(String token, String room_id) {
        VoiceRoomHttp http = createHttpRequest(VoiceRoomHttp.class);
        EnterVoiceRoomResponse response = http.joinVoiceRoom(new BaseVoiceRoomRequestBody(token, room_id));
        showFailMsg(response);
        if (isSuccess(response)) {
            setView(response);
        } else {
            getView().showErrorView();
        }
    }

}
