package com.zl.dappore.common.http;

import com.zl.dappore.appdetail.model.AppResponse;
import com.zl.dappore.home.model.AppTaxonsList;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Path;

public interface AppDetailHttp {
    @GET("/api/apps/")
    AppResponse requestAppDetail(@Path String[] id);

    @GET("/api/app_taxons/good")
    AppTaxonsList requestAppTaxonsList();
}
