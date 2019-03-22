package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.VoiceClientGridFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.VoiceClientListFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class VoiceClientListPresenter extends DapporePresenter<VoiceClientListFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData() {
    }

}
