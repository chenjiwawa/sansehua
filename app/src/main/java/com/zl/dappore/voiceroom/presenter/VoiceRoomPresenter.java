package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.fragment.VoiceRoomFragment;
import com.zl.dappore.voiceroom.model.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoom;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoomPresenter extends DapporePresenter<VoiceRoomFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(String id) {

        String token = "";
        String token1 = "IlESoZoBS7SKwNJdSsprvalf58DRmwpDSJE23SJ68mSyzXhsxHg9iONIXo4YoIbPsKvzm2Gt1UR0Mz6mqNFYRQ==";
        String token11 = "5Pn1Rp1T0lQGMN5gxXWwlalf58DRmwpDSJE23SJ68mSyzXhsxHg9iFKvLZZ3/rm25hgd//9DZUZ0Mz6mqNFYRQ==";
        if (id.equals("1")) {
            token = token1;
        }
        if (id.equals("11")) {
            token = token11;
        }

        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                L.e(initTag(), "onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                L.e(initTag(), "onSuccess userid:" + s);
                getView().initChatRoom();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.e(initTag(), "onError errorcode:" + errorCode.getValue());
            }
        });


        VoiceRole voiceHolder = new VoiceRole();
        voiceHolder.id = "1";
        voiceHolder.name = "name1";
        voiceHolder.position = 0;
        voiceHolder.logo = "http://staging.dappore.com/jak5hfxCGXqhWADNmKf1DM4b";

        List<VoiceRole> voiceRoles = new ArrayList<>();
        VoiceRole voiceRole1 = new VoiceRole();
        voiceRole1.id = "11";
        voiceRole1.name = "name11";
        voiceRole1.logo = "http://staging.dappore.com/VQKLStxifZVFu2Dz3SFxxUjm";
        voiceRole1.position = 1;

        VoiceRole voiceRole2 = new VoiceRole();
        voiceRole2.id = "12";
        voiceRole2.name = "name12";
        voiceRole2.logo = "http://staging.dappore.com/xNdqnHirMbzFYW9BXkmKPZ3n";
        voiceRole2.position = 2;

        voiceRoles.add(voiceRole1);
        voiceRoles.add(voiceRole2);
        for (int i = 0; i < 6; i++) {
            voiceRoles.add(new VoiceRole());
        }

        VoiceRoom data = new VoiceRoom("1", 1, "交友聊天", "我的房间", "", false, "", false, "问候一下", voiceHolder, voiceRoles);

        getView().updateVoiceRoomView(data);
        getView().updateVoiceHolderView(voiceHolder);
        getView().updateVoiceClientGridView(voiceRoles);
    }

}
