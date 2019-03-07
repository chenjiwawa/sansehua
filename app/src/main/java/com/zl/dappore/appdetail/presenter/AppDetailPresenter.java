package com.zl.dappore.appdetail.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.exception.QsException;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.zl.dappore.appdetail.fragment.AppDetailFragment;
import com.zl.dappore.appdetail.model.App;
import com.zl.dappore.appdetail.model.AppResponse;
import com.zl.dappore.comment.model.Comment;
import com.zl.dappore.comment.model.CommentRequstBody;
import com.zl.dappore.common.event.AppDetailEvent;
import com.zl.dappore.common.http.AppDetailHttp;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.http.CollectionHttp;
import com.zl.dappore.common.http.CommentHttp;
import com.zl.dappore.common.http.PraiseHttp;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.common.model.BaseRequstBody;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;

import java.util.ArrayList;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class AppDetailPresenter extends DapporePresenter<AppDetailFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppDetail(String id) {
        AppDetailHttp appHttp = createHttpRequest(AppDetailHttp.class);
        AppResponse appResponse = appHttp.requestAppDetail(new String[]{id});

        if (appResponse != null) {
            if (appResponse.app == null) {
                appResponse.app = new App();
            }
            if (appResponse.app.comments == null) {
                appResponse.app.comments = new ArrayList<Comment>();
            }
            if (appResponse.app.contracts == null) {
                appResponse.app.contracts = new ArrayList<App.Contract>();
            }

            getView().setHeader(appResponse.app);
            getView().setData(appResponse.app.comments);
            getView().scrollToPosition();
        }
        L.i(initTag(), "requestRankAppList appDetail.comments- " + appResponse.app.comments);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestAppListBySimilar() {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        AppList appList = appListHttp.requestAppListByNew("1", 4, 0);
        showFailMsg(appList);
        if (isSuccess(appList) && appList.apps != null) {
            getView().setSimilarList(appList.apps);
            paging(appList);
        } else {
            getView().hideSimilarList();
        }
    }

    public void requestAppListBySimilar(String app_taxon_id) {
        AppListHttp appListHttp = createHttpRequest(AppListHttp.class);
        AppList appList = appListHttp.requestAppListBySimilar(app_taxon_id, 4, 0);
        showFailMsg(appList);
        if (isSuccess(appList) && appList.apps != null) {
            getView().setSimilarList(appList.apps);
            paging(appList);
        } else {
            getView().hideSimilarList();
        }
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestPraise(String attitudinal_id) {
        PraiseHttp http = createHttpRequest(PraiseHttp.class);
        BaseModel baseModel = http.requestPraise(new String[]{"Comment/"}, new String[]{attitudinal_id}, new String[]{PraiseHttp.praisepath}, new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("点赞成功！");
            getView().onRefresh();
        } else {
            QsToast.show("点赞失败！");
        }
        getView().enablePraise(true);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestNoPraise(String attitudinal_id) {
        PraiseHttp http = createHttpRequest(PraiseHttp.class);
        BaseModel baseModel = http.requestNoPraise(new String[]{"Comment/"}, new String[]{attitudinal_id}, new String[]{PraiseHttp.nopraisepath},new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("取消点赞成功！");
            QsHelper.getInstance().eventPost(new AppDetailEvent(AppDetailEvent.AppDetailState.STATE_REFRESH));
        } else {
            QsToast.show("取消点赞失败！");
        }
        getView().enablePraise(true);
    }

    @ThreadPoint(ThreadType.HTTP)
    public void requestComfirmCollections(String starred_id) {
        CollectionHttp http = createHttpRequest(CollectionHttp.class);
        BaseModel baseModel = http.requestComfirmCollections(new String[]{CollectionHttp.TYPE_APP}, new String[]{starred_id}, new String[]{CollectionHttp.path}, new BaseRequstBody());
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("收藏成功！");
            QsHelper.getInstance().eventPost(new AppDetailEvent(AppDetailEvent.AppDetailState.STATE_REFRESH));
        } else {
            QsToast.show("收藏失败！");
        }
        getView().enableCollection(true);
    }

    /* {protocol=http/1.1, code=204, message=No Content, url=http://dappore.store/api/App/4027/stars}*/
    @ThreadPoint(ThreadType.HTTP)
    public void requestCancelCollections(String starred_id) {
        try {
            CollectionHttp http = createHttpRequest(CollectionHttp.class);
            BaseModel baseModel = http.requestCancelCollections(new String[]{CollectionHttp.TYPE_APP}, new String[]{starred_id}, new String[]{CollectionHttp.path}, new BaseRequstBody());
            L.i(initTag(), " requestCancelCollections " + baseModel);
            QsToast.show("取消收藏成功！");
            QsHelper.getInstance().eventPost(new AppDetailEvent(AppDetailEvent.AppDetailState.STATE_REFRESH));
        } catch (QsException e) {
            QsToast.show("取消收藏失败！");
        } finally {
            getView().enableCollection(true);
        }
    }


    @ThreadPoint(ThreadType.HTTP)
    public void requestCommentList(String commentable_id, CommentRequstBody body) {
        CommentHttp http = createHttpRequest(CommentHttp.class);
        BaseModel baseModel = http.requestComments(new String[]{"App/"}, new String[]{commentable_id}, new String[]{CommentHttp.path}, body);
        showFailMsg(baseModel);
        if (isSuccess(baseModel)) {
            QsToast.show("评论成功！");
            getView().setCommentSuccess();
            getView().onRefresh();
        } else {
            QsToast.show("评论失败！");
        }
    }

}
