package com.zl.dappore.search.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.VideoListHttp;
import com.zl.dappore.common.http.SearcherHttp;
import com.zl.dappore.common.model.BaseRequestModel;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.VideoList;
import com.zl.dappore.search.fragment.SearchVideoFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class SearchVideoPresenter extends DapporePresenter<SearchVideoFragment> {

    private SearcherHttp searcherHttp;

    @ThreadPoint(ThreadType.WORK)
    public void requestSearchData(boolean isLoadingMore, BaseRequestModel requestModel) {
    }


    private int page = 1;

//    @ThreadPoint(ThreadType.HTTP)
//    public void requestVideoListByHotWord(boolean isLoadingMore, String hot_word) {
//
//        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
//        if (isLoadingMore) {
//            if (page < 2) return;
//            VideoList videoList = videoListHttp.requestVideoListByHotWord(hot_word,1, page);
//            showFailMsg(videoList);
//            if (isSuccess(videoList) && videoList.videos != null) {
//                page++;
//                getView().addData(videoList.videos);
//                paging(videoList);
//            }
//        } else {
//            page = 1;
//            VideoList videoList = videoListHttp.requestVideoListByHotWord(hot_word,1, page);
//            showFailMsg(videoList);
//            if (isSuccess(videoList) && videoList.videos != null) {
//                page = 2;
//                getView().setData(videoList.videos);
//                paging(videoList);
//            } else {
//                getView().showErrorView();
//            }
//        }
//    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByHotWord(boolean isLoadingMore, String hot_word) {
        requestVideoList(isLoadingMore);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoList(boolean isLoadingMore) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoList(3, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(videoList.videos);
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoList(3, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page = 2;
                getView().setData(videoList.videos);
                paging(videoList);
            } else {
                getView().showErrorView();
            }
        }
    }
}
