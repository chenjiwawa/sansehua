package com.zl.dappore.home.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.zl.dappore.common.http.AppDetailHttp;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.http.PosterHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.fragment.RecommendFragment;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.home.model.AppTaxonsList;
import com.zl.dappore.home.model.PosterList;


public class RecommendPresenter extends DapporePresenter<RecommendFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requestPosterList() {
        PosterHttp posterHttp = createHttpRequest(PosterHttp.class);
        PosterList posterList = posterHttp.requestPosterList();

        L.i(initTag(), "requestPosterList posterList " + posterList);
        showFailMsg(posterList);
        getView().loadingClose();
        if (isSuccess(posterList) && posterList.posters != null) {
            getView().updateBanner(posterList);
        } else {
            getView().hideBanners();
            QsToast.show("海报加载失败！可下拉重试！");
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppTaxonsList() {
        AppDetailHttp appHttp = createHttpRequest(AppDetailHttp.class);
        AppTaxonsList appTaxonsList = appHttp.requestAppTaxonsList();

        L.i(initTag(), "requestAppTaxonsList appTaxonsList " + appTaxonsList);

        showFailMsg(appTaxonsList);
        getView().loadingClose();
        if (isSuccess(appTaxonsList) && appTaxonsList.appTaxons != null) {
            getView().setData(appTaxonsList.appTaxons);
            paging(appTaxonsList);
        } else {
            getView().showErrorView();
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListByNew() {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        AppList appList = appListHttp.requestAppListByNew("1", 5, 0);
        L.i(initTag(), "requestAppListByNew appList " + appList);
        showFailMsg(appList);
        getView().loadingClose();
        if (isSuccess(appList) && appList.apps != null) {
            getView().setRecommendList(appList.apps);
            paging(appList);
        } else {
            getView().hideRecommendList();
            QsToast.show("推荐应用加载失败！可下拉重试！");
        }
    }
}