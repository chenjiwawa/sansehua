package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseEmojiListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseProductListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiPageList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductPageList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface EmojiHttp {

    @GET("/api/Gift/page_list")
    EmojiPageList requestEmojiPageList(@Body BaseRequstBody body);

    @GET("/api/Gift/get_gift_list")
    EmojiList requestEmojiList(@Body BaseEmojiListRequestBody body);

}