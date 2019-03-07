package com.zl.dappore.home.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.common.http.VideoCategoryHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.fragment.VideoFragment;
import com.zl.dappore.home.model.VideoTaxonsList;

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
