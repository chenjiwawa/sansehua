package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.EmojiHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.emoji.EmojiGridFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.ProductGridFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseEmojiListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseProductListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;


public class EmojiGridPresenter extends DapporePresenter<EmojiGridFragment> {


    private int pageSize = 8;

    @ThreadPoint(ThreadType.HTTP)
    public void requestData(String token, int page) {
        EmojiHttp http = createHttpRequest(EmojiHttp.class);
        EmojiList reponse = http.requestEmojiList(new BaseEmojiListRequestBody(token, page));
        showFailMsg(reponse);
        if (isSuccess(reponse) && reponse.data != null) {
            getView().setData(reponse.data);
        } else {
            getView().showErrorView();
        }
    }


}