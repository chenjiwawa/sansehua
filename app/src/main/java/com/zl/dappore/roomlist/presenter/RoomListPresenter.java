package com.zl.dappore.roomlist.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.roomlist.fragment.RoomListFragment;


public class RoomListPresenter extends DapporePresenter<RoomListFragment> {

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListByNew(boolean isLoadingMore, String created_at_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestAppListByNew(created_at_desc, 1, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppListByNew(created_at_desc, 1, page);
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
            AppList appList = appListHttp.requestAppListByHot(app_taxon_id, access_count_desc, 1, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppListByHot(app_taxon_id, access_count_desc, 1, page);
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
    public void requestAppListBySimilar(boolean isLoadingMore, String app_taxon_id, String access_count_desc) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestAppListBySimilar(app_taxon_id, 1, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppListBySimilar(app_taxon_id, 1, page);
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