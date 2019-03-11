package com.zl.dappore.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.voiceroom.fragment.ProductGridFragment;


public class ProductGridPresenter extends DapporePresenter<ProductGridFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requestRankAppList(String app_taxon_id, String created_at_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        AppList appList = appListHttp.requestRankAppList(app_taxon_id, created_at_desc);

        L.i(initTag(), "requestRankAppList appList " + appList);

        if (appList != null && appList.apps != null) {
            getView().setData(appList.apps);
        }
        L.i(initTag(), "requestRankAppList appList- " + appList);
    }

    private int page = 1;
    private int pageSize = 8;

    @ThreadPoint(ThreadType.HTTP)
    public void requestRankAppList(boolean isLoadingMore, String app_taxon_id, String created_at_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestRankAppList(app_taxon_id, created_at_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestRankAppList(app_taxon_id, created_at_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page = 2;
                getView().setData(appList.apps);
                paging(appList);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListByScore(boolean isLoadingMore, String app_taxon_id, String score_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestAppListByScore(app_taxon_id, score_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppListByScore(app_taxon_id, score_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page = 2;
                getView().setData(appList.apps);
                paging(appList);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListByNew(boolean isLoadingMore, String app_taxon_id, String created_at_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestAppListByNew(app_taxon_id, created_at_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppListByNew(app_taxon_id, created_at_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page = 2;
                getView().setData(appList.apps);
                paging(appList);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListByHot(boolean isLoadingMore, String app_taxon_id, String access_count_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestAppListByHot(app_taxon_id, access_count_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppListByHot(app_taxon_id, access_count_desc, pageSize, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page = 2;
                getView().setData(appList.apps);
                paging(appList);
            } else {
                getView().showErrorView();
            }
        }
    }

}