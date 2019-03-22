package com.tricolorflower.heartbeat.comment.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.comment.fragment.CommentListFragment;
import com.tricolorflower.heartbeat.comment.model.CommentList;
import com.tricolorflower.heartbeat.comment.model.CommentRequstBody;
import com.tricolorflower.heartbeat.common.event.AppDetailEvent;
import com.tricolorflower.heartbeat.common.http.CommentHttp;
import com.tricolorflower.heartbeat.common.http.PraiseHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;


public class CommentListPresenter extends DapporePresenter<CommentListFragment> {

    private int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestCommentList(boolean isLoadingMore, String commentable_id) {
        CommentHttp http = createHttpRequest(CommentHttp.class);
        if (isLoadingMore) {
            if (page < 2) return;
            CommentList list = http.requestComments(new String[]{"App/"}, new String[]{commentable_id}, new String[]{CommentHttp.path}, 5, page);
            showFailMsg(list);
            if (isSuccess(list) && list.comments != null) {
                page++;
                getView().addData(list.comments);
                paging(list);
            }
        } else {
            page = 1;
            CommentList list = http.requestComments(new String[]{"App/"}, new String[]{commentable_id}, new String[]{CommentHttp.path}, 5, page);
            showFailMsg(list);
            if (isSuccess(list) && list.comments != null) {
                page = 2;
                getView().setData(list.comments);
                paging(list);
            } else {
                getView().showErrorView();
            }
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestCommentList(String commentable_id, CommentRequstBody body) {
        CommentHttp http = createHttpRequest(CommentHttp.class);
        BaseModel baseModel = http.requestComments(new String[]{"App/"}, new String[]{commentable_id}, new String[]{CommentHttp.path}, body);
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("评论成功！");
            getView().onRefresh();
            getView().setCommentSuccess();
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

}