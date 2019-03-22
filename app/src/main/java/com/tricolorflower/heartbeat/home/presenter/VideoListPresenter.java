package com.tricolorflower.heartbeat.home.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.http.VideoListHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.fragment.VideoListFragment;
import com.tricolorflower.heartbeat.home.model.GridItemVideo;
import com.tricolorflower.heartbeat.home.model.VideoList;
import com.tricolorflower.heartbeat.videodetail.model.Video;

import java.util.ArrayList;
import java.util.List;


public class VideoListPresenter extends DapporePresenter<VideoListFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requestRankVideoList(String app_taxon_id, String created_at_desc) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        VideoList videoList = videoListHttp.requestRankVideoList(app_taxon_id, created_at_desc);

        L.i(initTag(), "requestRankVideoList videoList " + videoList);

        if (videoList != null && videoList.videos != null) {
            getView().setData(setData(videoList.videos));
        }
        L.i(initTag(), "requestRankVideoList videoList- " + videoList);
    }

    private List<GridItemVideo> setData(List<Video> videos) {
        List<GridItemVideo> videoGridList = new ArrayList<>();
        if (videos != null) {
            int i = 0;
            GridItemVideo gridItemVideo = new GridItemVideo();
            gridItemVideo.videos = new ArrayList<>();
            for (Video video : videos) {
                if (gridItemVideo != null && gridItemVideo.videos != null && gridItemVideo.videos.size() == 2) {
                    videoGridList.add(gridItemVideo);
                    gridItemVideo = new GridItemVideo();
                    gridItemVideo.videos = new ArrayList<>();
                }
                gridItemVideo.videos.add(video);
            }
        }
        return videoGridList;
    }

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestRankVideoList(boolean isLoadingMore, String app_taxon_id, String created_at_desc) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestRankVideoList(app_taxon_id, created_at_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(setData(videoList.videos));
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestRankVideoList(app_taxon_id, created_at_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page = 2;
                getView().setData(setData(videoList.videos));
                paging(videoList);
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
                getView().addData(setData(videoList.videos));
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoListByScore(app_taxon_id, score_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page = 2;
                getView().setData(setData(videoList.videos));
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
                getView().addData(setData(videoList.videos));
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoListByNew(app_taxon_id, created_at_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page = 2;
                getView().setData(setData(videoList.videos));
                paging(videoList);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoListByHot(boolean isLoadingMore, String app_taxon_id, String access_count_desc) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoListByHot(app_taxon_id, access_count_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(setData(videoList.videos));
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoListByHot(app_taxon_id, access_count_desc, 4, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page = 2;
                getView().setData(setData(videoList.videos));
                paging(videoList);
            } else {
                getView().showErrorView();
            }
        }
    }

}