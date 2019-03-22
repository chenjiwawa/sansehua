package com.tricolorflower.heartbeat.home.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.http.VideoCategoryHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.fragment.VideoFragment;
import com.tricolorflower.heartbeat.home.model.VideoTaxonsList;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class VideoPresenter extends DapporePresenter<VideoFragment> {

    @ThreadPoint(ThreadType.HTTP) public void requestVideoTaxons() {
        VideoCategoryHttp categoryHttp = createHttpRequest(VideoCategoryHttp.class);
        VideoTaxonsList videoTaxons = categoryHttp.requestVideoTaxons();

        L.i(initTag(), "requestvideoTaxons videoTaxons " + videoTaxons);

        showFailMsg(videoTaxons);
        if (isSuccess(videoTaxons) && videoTaxons.videoTaxons != null) {
            getView().updateViewPager(videoTaxons.videoTaxons);
        } else {
            getView().showErrorView();
        }
    }
}
