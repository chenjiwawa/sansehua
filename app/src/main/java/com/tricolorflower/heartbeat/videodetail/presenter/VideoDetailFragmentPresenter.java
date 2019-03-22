package com.tricolorflower.heartbeat.videodetail.presenter;

import com.qsmaxmin.qsbase.common.aspect.Path;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.exception.QsException;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.appdetail.model.AppResponse;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.common.event.AppDetailEvent;
import com.tricolorflower.heartbeat.common.http.AppDetailHttp;
import com.tricolorflower.heartbeat.common.http.CollectionHttp;
import com.tricolorflower.heartbeat.common.http.PraiseHttp;
import com.tricolorflower.heartbeat.common.http.RewardHttp;
import com.tricolorflower.heartbeat.common.http.VideoDetailHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.videodetail.fragment.VideoDetailFragment;
import com.tricolorflower.heartbeat.videodetail.model.RewardResponse;
import com.tricolorflower.heartbeat.videodetail.model.Video;
import com.tricolorflower.heartbeat.videodetail.model.VideoResponse;

import java.util.ArrayList;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class VideoDetailFragmentPresenter extends DapporePresenter<VideoDetailFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoDetail(String id) {
        VideoDetailHttp http = createHttpRequest(VideoDetailHttp.class);
        VideoResponse response = http.requestVideoDetail(new String[]{id});

        if (response != null) {
            if (response.video == null) {
                response.video = new Video();
            }
            if (response.video.media == null) {
                response.video.media = new Video.Media();
            }

            getView().setView(response.video);
        }
        L.i(initTag(), "requestVideoDetail " + response);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestVideoDetailViewed(String id) {
        VideoDetailHttp http = createHttpRequest(VideoDetailHttp.class);
        BaseModel response = http.requestVideoDetailViewed(new String[]{id}, new String[]{VideoDetailHttp.path_viewed});

        showFailMsg(response);
        if (isSuccess(response)) {
            getView().setVideoDetailViewed(true);
        } else {
            getView().setVideoDetailViewed(false);
        }
        L.i(initTag(), "requestVideoDetail " + response);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestComfirmCollections(String starred_id) {
        CollectionHttp http = createHttpRequest(CollectionHttp.class);
        BaseModel baseModel = http.requestComfirmCollections(new String[]{CollectionHttp.TYPE_VIDEO}, new String[]{starred_id}, new String[]{CollectionHttp.path}, new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("收藏成功！");
            getView().setCollectionViewAndRefreshData(true);
            QsHelper.getInstance().eventPost(new AppDetailEvent(AppDetailEvent.AppDetailState.STATE_REFRESH));
        } else {
            QsToast.show("收藏失败！");
            getView().setCollectionView(false);
        }
        getView().enableCollection(true);
    }

    /* {protocol=http/1.1, code=204, message=No Content, url=http://dappore.store/api/App/4027/stars}*/
    @ThreadPoint(ThreadType.HTTP)
    public void requestCancelCollections(String starred_id) {
        try {
            CollectionHttp http = createHttpRequest(CollectionHttp.class);
            BaseModel baseModel = http.requestCancelCollections(new String[]{CollectionHttp.TYPE_VIDEO}, new String[]{starred_id}, new String[]{CollectionHttp.path}, new BaseRequstBody());
            L.i(initTag(), " requestCancelCollections " + baseModel);
            QsToast.show("取消收藏成功！");
            getView().setCollectionViewAndRefreshData(false);
            QsHelper.getInstance().eventPost(new AppDetailEvent(AppDetailEvent.AppDetailState.STATE_REFRESH));
        } catch (QsException e) {
            QsToast.show("取消收藏失败！");
            getView().setCollectionView(true);
        } finally {
            getView().enableCollection(true);
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestPraise(String attitudinal_id) {
        PraiseHttp http = createHttpRequest(PraiseHttp.class);
        BaseModel baseModel = http.requestPraise(new String[]{PraiseHttp.TYPE_VIDEO}, new String[]{attitudinal_id}, new String[]{PraiseHttp.praisepath}, new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("点赞成功！");
            getView().setPraiseViewAndRefreshData(true);
        } else {
            QsToast.show("点赞失败！");
            getView().setPraiseView(false);
        }
        getView().enablePraise(true);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestNoPraise(String attitudinal_id) {
        PraiseHttp http = createHttpRequest(PraiseHttp.class);
        BaseModel baseModel = http.requestNoPraise(new String[]{PraiseHttp.TYPE_VIDEO}, new String[]{attitudinal_id}, new String[]{PraiseHttp.nopraisepath}, new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("取消点赞成功！");
            getView().setPraiseViewAndRefreshData(true);
        } else {
            QsToast.show("取消点赞失败！");
            getView().setPraiseView(false);
        }
        getView().enablePraise(true);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestRewardByShare(String entity_id) {
        RewardHttp http = createHttpRequest(RewardHttp.class);
        RewardResponse response = http.requestReward(new String[]{RewardHttp.TYPE_VIDEO}, new String[]{entity_id}, new String[]{RewardHttp.rewardpath}, RewardHttp.QUERY_PARAM_SHARE_VIDEO, new BaseRequstBody());

        if (response != null) {
            if (response.reward == null) {
                response.reward = new RewardResponse.Reward();
            }
            getView().setShareRewardAndRefreshData(response.reward);
        }
        L.i(initTag(), "requestRewardByShare " + response);
    }

}
