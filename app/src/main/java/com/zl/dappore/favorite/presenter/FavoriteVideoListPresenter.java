package com.zl.dappore.favorite.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.FavoriteListHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.favorite.fragment.FavoriteVideoListFragment;
import com.zl.dappore.home.model.VideoList;

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
