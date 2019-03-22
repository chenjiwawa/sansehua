package com.tricolorflower.heartbeat.userinfo.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VideoListHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.GridItemVideo;
import com.tricolorflower.heartbeat.home.model.VideoList;
import com.tricolorflower.heartbeat.userinfo.fragment.PersonalVideoFragment;
import com.tricolorflower.heartbeat.videodetail.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class PersonalVideoPresenter extends DapporePresenter<PersonalVideoFragment> {

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

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoList(boolean isLoadingMore, String id) {
        VideoListHttp videoListHttp = createHttpRequest(VideoListHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            VideoList videoList = videoListHttp.requestVideoList(3, page);
            showFailMsg(videoList);
            if (isSuccess(videoList) && videoList.videos != null) {
                page++;
                getView().addData(setData(videoList.videos));
                paging(videoList);
            }
        } else {
            page = 1;
            VideoList videoList = videoListHttp.requestVideoList(3, page);
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
