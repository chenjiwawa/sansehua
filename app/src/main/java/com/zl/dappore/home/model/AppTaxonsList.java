package com.zl.dappore.home.model;

import com.google.gson.annotations.SerializedName;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.common.model.BaseModel;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class AppTaxonsList extends BaseModel {

    @SerializedName("app_taxons")
    public List<App.AppTaxon> appTaxons;

    @Override
    public boolean isLastPage() {
        return appTaxons != null && appTaxons.size() > 0;
    }
}
