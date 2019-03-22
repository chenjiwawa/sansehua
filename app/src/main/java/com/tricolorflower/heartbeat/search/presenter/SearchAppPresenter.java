package com.tricolorflower.heartbeat.search.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.AppListHttp;
import com.tricolorflower.heartbeat.common.http.SearcherHttp;
import com.tricolorflower.heartbeat.common.model.BaseRequestModel;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.AppList;
import com.tricolorflower.heartbeat.search.fragment.SearchAppFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class SearchAppPresenter extends DapporePresenter<SearchAppFragment> {

    private SearcherHttp searcherHttp;

    @ThreadPoint(ThreadType.WORK)
    public void requestSearchData(boolean isLoadingMore, BaseRequestModel requestModel) {
    }


    private int page = 1;

//    @ThreadPoint(ThreadType.HTTP)
//    public void requestAppListByHotWord(boolean isLoadingMore, String hot_word) {
//        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
//        if (isLoadingMore) {
//            if (page < 2) return;
//            AppList appList = appListHttp.requestAppListByHotWord(hot_word, 1, page);
//            showFailMsg(appList);
//            if (isSuccess(appList) && appList.apps != null) {
//                page++;
//                getView().addData(appList.apps);
//                paging(appList);
//            }
//        } else {
//            page = 1;
//            AppList appList = appListHttp.requestAppListByHotWord(hot_word, 1, page);
//            showFailMsg(appList);
//            if (isSuccess(appList) && appList.apps != null) {
//                page = 2;
//                getView().setData(appList.apps);
//                paging(appList);
//            } else {
//                getView().showErrorView();
//            }
//        }
//    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListByHotWord(boolean isLoadingMore, String hot_word) {
        requestAppList(isLoadingMore);
    }


    @ThreadPoint(ThreadType.HTTP)
    public void requestAppList(boolean isLoadingMore) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            AppList appList = appListHttp.requestAppList(3, page);
            showFailMsg(appList);
            if (isSuccess(appList) && appList.apps != null) {
                page++;
                getView().addData(appList.apps);
                paging(appList);
            }
        } else {
            page = 1;
            AppList appList = appListHttp.requestAppList(3, page);
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
