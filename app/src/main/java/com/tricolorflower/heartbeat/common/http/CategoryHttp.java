package com.tricolorflower.heartbeat.common.http;

import com.tricolorflower.heartbeat.home.model.AppTaxonsList;
import com.qsmaxmin.qsbase.common.aspect.GET;


public interface CategoryHttp {

    @GET("/api/app_taxons")
    AppTaxonsList requestAppTaxons();

}
