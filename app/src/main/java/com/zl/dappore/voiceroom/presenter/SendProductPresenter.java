package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.fragment.roomrole.product.SendProductFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class SendProductPresenter extends DapporePresenter<SendProductFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
    }

}
