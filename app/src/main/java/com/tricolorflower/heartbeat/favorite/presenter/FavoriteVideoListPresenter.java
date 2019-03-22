package com.tricolorflower.heartbeat.favorite.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.FavoriteListHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.favorite.fragment.FavoriteVideoListFragment;
import com.tricolorflower.heartbeat.home.model.VideoList;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class FavoriteVideoListPresenter extends DapporePresenter<FavoriteVideoListFragment> {

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestFavoriteVideoList(boolean isLoadingMore) {
        FavoriteListHttp http = createHttpRequest(FavoriteListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList list = http.requestFavoriteVideoList(FavoriteListHttp.PARAM_VIDEO, 3, page);
            showFailMsg(list);
            if (isSuccess(list) && list.videos != null) {
                page++;
                getView().addData(list.videos);
                paging(list);
            }
        } else {
            page = 1;
            VideoList list = http.requestFavoriteVideoList(FavoriteListHttp.PARAM_VIDEO, 3, page);
            showFailMsg(list);
            if (isSuccess(list) && list.videos != null) {
                page = 2;
                getView().setData(list.videos);
                paging(list);
            } else {
                getView().showErrorView();
            }
        }
    }
}
