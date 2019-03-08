package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.fragment.VoiceRoomFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceRoomPresenter extends DapporePresenter<VoiceRoomFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
    }

}
