package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.comment.model.CommentList;
import com.zl.dappore.common.http.CommentHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.fragment.VoiceClientFragment;
import com.zl.dappore.voiceroom.fragment.VoiceRoomFragment;
import com.zl.dappore.voiceroom.model.VoiceClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceClientPresenter extends DapporePresenter<VoiceClientFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
        List<VoiceClient> voiceClientList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            voiceClientList.add(new VoiceClient());
        }
        getView().addData(voiceClientList);
    }

}
