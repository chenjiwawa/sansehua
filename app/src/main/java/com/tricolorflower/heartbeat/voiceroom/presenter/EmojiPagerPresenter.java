package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.EmojiHttp;
import com.tricolorflower.heartbeat.common.http.ProductHttp;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.emoji.EmojiPagerFragment;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.ProductPagerFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiPageList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductPageList;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class EmojiPagerPresenter extends DapporePresenter<EmojiPagerFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(BaseRequstBody requestBody) {
        EmojiHttp http = createHttpRequest(EmojiHttp.class);
        EmojiPageList response = http.requestEmojiPageList(requestBody);
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().updateViewPager(response.data);
        } else {
            getView().showErrorView();
        }
    }


}
