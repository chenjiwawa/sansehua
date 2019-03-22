package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.http.CategoryHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.AppTaxonsList;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.ProductPagerFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class ProductPagerPresenter extends DapporePresenter<ProductPagerFragment> {

    @ThreadPoint(ThreadType.HTTP) public void requestAppTaxons() {
        CategoryHttp categoryHttp = createHttpRequest(CategoryHttp.class);
        AppTaxonsList appTaxons = categoryHttp.requestAppTaxons();

        L.i(initTag(), "requestAppTaxons appTaxons " + appTaxons);

        showFailMsg(appTaxons);
        if (isSuccess(appTaxons) && appTaxons.appTaxons != null) {
            getView().updateViewPager(appTaxons.appTaxons);
        } else {
            getView().showErrorView();
        }
    }
}
