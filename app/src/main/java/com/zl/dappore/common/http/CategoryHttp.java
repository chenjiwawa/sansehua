package com.zl.dappore.common.http;

import com.zl.dappore.home.model.AppTaxonsList;
import com.qsmaxmin.qsbase.common.aspect.GET;


public interface CategoryHttp {

    @GET("/api/app_taxons")
    AppTaxonsList requestAppTaxons();

}
