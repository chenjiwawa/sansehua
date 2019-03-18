package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.fragment.VoiceRoomFragment;
import com.zl.dappore.voiceroom.model.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoomPresenter extends DapporePresenter<VoiceRoomFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(String id) {

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
