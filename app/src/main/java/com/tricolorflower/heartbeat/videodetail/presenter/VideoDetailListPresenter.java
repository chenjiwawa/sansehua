package com.tricolorflower.heartbeat.videodetail.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.http.VideoListHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.VideoList;
import com.tricolorflower.heartbeat.videodetail.fragment.VideoDetailListFragment;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class VideoDetailListPresenter extends DapporePresenter<VideoDetailListFragment> {

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByTag(boolean isLoadingMore, String name_like) {
        VideoListHttp http = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList list = http.requestVideoListByTag(name_like, 1, page);
            showFailMsg(list);
            if (isSuccess(list) && list.videos != null) {
                page++;
                getView().addData(list.videos);
            }
        } else {
            page = 1;
            VideoList list = http.requestVideoListByTag(name_like, 1, page);
            showFailMsg(list);
            if (isSuccess(list) && list.videos != null) {
                page = 2;
                getView().setData(list.videos);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoList(boolean isLoadingMore, String id) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoList(2, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(videoList.videos);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoList(2, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page = 2;
                getView().setData(videoList.videos);
            } else {
                getView().showErrorView();
            }
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByScore(boolean isLoadingMore, String app_taxon_id, String score_desc) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoListByScore(app_taxon_id, score_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(videoList.videos);
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoListByScore(app_taxon_id, score_desc, 4, page);
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

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByNew(boolean isLoadingMore, String app_taxon_id, String created_at_desc) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoListByNew(app_taxon_id, created_at_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(videoList.videos);
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoListByNew(app_taxon_id, created_at_desc, 4, page);
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

    int pageSize = 3;

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByHot(boolean isLoadingMore, String app_taxon_id, String access_count_desc) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoListByHot(app_taxon_id, access_count_desc, pageSize, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(videoList.videos);
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoListByHot(app_taxon_id, access_count_desc, pageSize, page);
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

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByPage(boolean isLoadingMore, String app_taxon_id, String access_count_desc, int currentPage, int pageSize) {
        L.i(initTag(), " requestVideoListByHot isLoadingMore " + isLoadingMore + " app_taxon_id " + app_taxon_id + " access_count_desc " + access_count_desc + " currentPage " + currentPage);
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (currentPage < 0) return;
            VideoList videoList = videoListHttp.requestVideoListByHot(app_taxon_id, access_count_desc, pageSize, ++currentPage);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                getView().addData(videoList.videos);
                getView().setPageNo(currentPage);
            }
        } else {
            if (currentPage > 0) {
                --currentPage;
            }
            VideoList videoList = videoListHttp.requestVideoListByHot(app_taxon_id, access_count_desc, pageSize, currentPage);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                getView().setData(videoList.videos);
                getView().setPageNo(currentPage);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByPage(boolean isLoadingMore, String videoId, String app_taxon_id, String access_count_desc, int currentPage, int pageSize) {
        L.i(initTag(), " requestVideoListByHot isLoadingMore " + isLoadingMore + " app_taxon_id " + app_taxon_id + " access_count_desc " + access_count_desc + " currentPage " + currentPage);
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (currentPage < 0) return;
            VideoList videoList = videoListHttp.requestVideoList(app_taxon_id, pageSize, ++currentPage);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                getView().addData(videoList.videos);
                getView().setPageNo(currentPage);
            }
        } else {
            if (currentPage > 0) {
                --currentPage;
            }
            VideoList videoList = videoListHttp.requestVideoList(app_taxon_id, pageSize, currentPage);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                getView().setData(videoList.videos);
                getView().setPageNo(currentPage);
            } else {
                getView().showErrorView();
            }
        }
    }

}
