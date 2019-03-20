package com.zl.dappore.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.ProductHttp;
import com.zl.dappore.common.http.VoiceRoleHttp;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voiceroom.fragment.roomrole.product.SendProductFragment;
import com.zl.dappore.voiceroom.model.voicerole.BaseVoiceRoleRequestBody;
import com.zl.dappore.voiceroom.model.voicerole.ProductList;
import com.zl.dappore.voiceroom.model.voicerole.SendProductRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class SendProductPresenter extends DapporePresenter<SendProductFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(int voiceRole) {
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(SendProductRequestBody requestBody) {
        ProductHttp http = createHttpRequest(ProductHttp.class);
        BaseModel response = http.sendProduct(requestBody);
        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setsendProductSuccessView();
        } else {
        }
    }

}
