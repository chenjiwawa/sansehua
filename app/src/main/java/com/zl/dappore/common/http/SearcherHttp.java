package com.zl.dappore.common.http;


import com.zl.dappore.common.model.BaseRequestModel;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.search.model.ModelHotWord;
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
