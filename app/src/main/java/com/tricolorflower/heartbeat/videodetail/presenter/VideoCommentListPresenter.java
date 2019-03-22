package com.tricolorflower.heartbeat.videodetail.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.comment.model.CommentList;
import com.tricolorflower.heartbeat.comment.model.CommentResponse;
import com.tricolorflower.heartbeat.common.event.AppDetailEvent;
import com.tricolorflower.heartbeat.common.http.CommentHttp;
import com.tricolorflower.heartbeat.common.http.PraiseHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.videodetail.fragment.VideoCommentListFragment;
import com.tricolorflower.heartbeat.videodetail.model.CommentRequstBody;
import com.tricolorflower.heartbeat.videodetail.model.VideoCommentResponse;


public class VideoCommentListPresenter extends DapporePresenter<VideoCommentListFragment> {

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestCommentList(boolean isLoadingMore, String commentable_id) {
        CommentHttp http = createHttpRequest(CommentHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            CommentList list = http.requestComments(new String[]{CommentHttp.TYPE_VIDEO}, new String[]{commentable_id}, new String[]{CommentHttp.path}, 5, page);
            if (list != null && list.comments != null) {
                for (Comment comment : list.comments) {
                    L.i(initTag(), " requestCommentList  " + isLoadingMore + " " + comment.id);
                }
            }

            showFailMsg(list);
            if (isSuccess(list) && list.comments != null) {
                page++;
                getView().addData(list.comments);
                paging(list);
                if (list != null && list.comments != null) {
                    L.i(initTag(), " requestCommentList  " + isLoadingMore + " " + list.comments.size());
                }
            }
        } else {
            page = 1;
            CommentList list = http.requestComments(new String[]{CommentHttp.TYPE_VIDEO}, new String[]{commentable_id}, new String[]{CommentHttp.path}, 5, page);
            showFailMsg(list);
            if (isSuccess(list) && list.comments != null) {
                page = 2;
                getView().setData(list.comments, false);
                paging(list);
                if (list != null && list.comments != null) {
                    L.i(initTag(), " requestCommentList  " + isLoadingMore + " " + list.comments.size());
                }
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestCommentList(String commentable_id, CommentRequstBody body) {
        CommentHttp http = createHttpRequest(CommentHttp.class);
        CommentResponse response = http.requestComments(new String[]{CommentHttp.TYPE_VIDEO}, new String[]{commentable_id}, new String[]{CommentHttp.path}, body);
        showFailMsg(response);
        if (isSuccess(response)) {
            QsToast.show("评论成功！");
            getView().onRefresh();
            getView().setCommentSuccess(response.reward);
            QsHelper.getInstance().eventPost(new AppDetailEvent(AppDetailEvent.AppDetailState.STATE_REFRESH));
        } else {
            QsToast.show("评论失败！");
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestFavorite(int pos, String attitudinal_id) {
        PraiseHttp http = createHttpRequest(PraiseHttp.class);
        BaseModel baseModel = http.requestPraise(new String[]{"Comment/"}, new String[]{attitudinal_id}, new String[]{PraiseHttp.praisepath}, new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("点赞成功！");
            getView().setFavoriteSuccess(pos);
        } else {
            QsToast.show("点赞失败！");
        }
        getView().enablePraise(true);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestNoFavorite(int pos, String attitudinal_id) {
        PraiseHttp http = createHttpRequest(PraiseHttp.class);
        BaseModel baseModel = http.requestNoPraise(new String[]{"Comment/"}, new String[]{attitudinal_id}, new String[]{PraiseHttp.nopraisepath},new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("取消点赞成功！");
            getView().setNoFavoriteSuccess(pos);
        } else {
            QsToast.show("取消点赞失败！");
        }
        getView().enablePraise(true);
    }

}