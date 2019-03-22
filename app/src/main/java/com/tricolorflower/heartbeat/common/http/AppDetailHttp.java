package com.tricolorflower.heartbeat.common.http;

import com.tricolorflower.heartbeat.appdetail.model.AppResponse;
import com.tricolorflower.heartbeat.home.model.AppTaxonsList;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Path;

public interface AppDetailHttp {
    @GET("/api/apps/")
    AppResponse requestAppDetail(@Path String[] id);

    @GET("/api/app_taxons/good")
    AppTaxonsList requestAppTaxonsList();
}
