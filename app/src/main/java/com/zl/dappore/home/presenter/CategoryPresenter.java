package com.zl.dappore.home.presenter;

import com.zl.dappore.common.http.CategoryHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.fragment.CategoryFragment;
import com.zl.dappore.home.model.AppTaxonsList;
import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class CategoryPresenter extends DapporePresenter<CategoryFragment> {

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
