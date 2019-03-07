package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.appdetail.model.TradeHistoryResponse;

public interface TradeHistoryHttp {
    public static final String path = "/history";
    public static final String[] NAME_TYPE = {"day", "week", "month"};

    @GET("/api/apps/")
    TradeHistoryResponse requestTradeHistory(@Path String[] id, @Path String[] history, @Query("type") String type);

}
