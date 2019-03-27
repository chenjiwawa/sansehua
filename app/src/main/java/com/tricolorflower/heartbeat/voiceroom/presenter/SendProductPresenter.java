package com.tricolorflower.heartbeat.voiceroom.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.ProductHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.SendProductFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;

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
            getView().setSendProductSuccessView();
        } else {
        }
    }

}
