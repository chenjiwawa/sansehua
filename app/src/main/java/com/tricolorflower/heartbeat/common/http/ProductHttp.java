package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.umeng.socialize.media.Base;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseProductListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface ProductHttp {

    @GET("/api/Gift/get_gift_list")
    ProductList requestProductList(@Body BaseProductListRequestBody body);

    @GET("/api/gift/send_gift")
    BaseModel sendProduct(@Body SendProductRequestBody body);

}