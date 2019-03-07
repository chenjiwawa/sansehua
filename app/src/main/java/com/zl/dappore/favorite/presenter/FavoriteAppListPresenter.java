package com.zl.dappore.favorite.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.FavoriteListHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.search.fragment.SearchAppFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class FavoriteAppListPresenter extends DapporePresenter<SearchAppFragment> {


    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestFavoriteAppList(boolean isLoadingMore) {
        FavoriteListHttp http = createHttpRequest(FavoriteListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = http.requestFavoriteAppList(FavoriteListHttp.PARAM_APP, 3, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = http.requestFavoriteAppList(FavoriteListHttp.PARAM_APP, 3, page);
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
