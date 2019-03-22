package com.tricolorflower.heartbeat.common.http;


import com.tricolorflower.heartbeat.common.model.BaseRequestModel;
import com.tricolorflower.heartbeat.home.model.AppList;
import com.tricolorflower.heartbeat.search.model.ModelHotWord;
import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/23  上午9:10
 * @Description
 */
public interface SearcherHttp {

    /**
     * 获取搜索热词 备用
     */
    @POST("*******************")
    ModelHotWord requestHotWord(@Body BaseRequestModel req);

    /**
     * 获取搜索过滤数据
     */
    @POST("/api/fontstore/fontdetail/search")
    AppList requestSearchData(@Body BaseRequestModel req);
}
