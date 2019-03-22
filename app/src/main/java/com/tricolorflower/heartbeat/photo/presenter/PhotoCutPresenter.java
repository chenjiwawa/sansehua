package com.tricolorflower.heartbeat.photo.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.account.model.UserResponse;
import com.tricolorflower.heartbeat.common.event.UserInfoEvent;
import com.tricolorflower.heartbeat.common.http.UploadHttp;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.common.utils.Base64;
import com.tricolorflower.heartbeat.photo.PhotoCutActivity;
import com.tricolorflower.heartbeat.photo.model.UploadRequestBody;


/**
 * @CreateBy qsmaxmin
 * @Date 2016/11/15 15:37
 * @Description
 */
public class PhotoCutPresenter extends DapporePresenter<PhotoCutActivity> {

    /**
     * 提交更改昵称
     */
    @ThreadPoint(ThreadType.HTTP)
    public void requestChangeHead(String account, byte[] head) {
        if (head == null || head.length == 0) {
            QsToast.show(getString(R.string.head_is_null));
            return;
        }
        String str = Base64.encodeBytes(head);
        getView().loading(getString(R.string.head_uploading));
        UploadHttp http = createHttpRequest(UploadHttp.class);
//        UploadRequestBody body = new UploadRequestBody(str, "image/jpeg", 0, "");
        //TODO
//        UserResponse result = http.requestUpload(body);
//        if (isSuccess(result) && result.user != null) {
//            QsToast.show(result.message);
//            UserConfig.getInstance().setAvatarUrl(result.user.avatarUrl);
//            QsHelper.getInstance().eventPost(new UserInfoEvent.OnPhotoCutEvent(head));
//            getView().loadingClose();
//            getView().activityFinish();
//        } else {
//            QsToast.show(result.message);
//        }
    }
}
