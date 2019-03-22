package com.tricolorflower.heartbeat.userinfo.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.account.model.UserResponse;
import com.tricolorflower.heartbeat.common.http.PersonalInfoHttp;
import com.tricolorflower.heartbeat.common.http.VideoListHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.GridItemVideo;
import com.tricolorflower.heartbeat.home.model.VideoList;
import com.tricolorflower.heartbeat.userinfo.fragment.PersonalInfoFragment;
import com.tricolorflower.heartbeat.userinfo.fragment.PersonalVideoFragment;
import com.tricolorflower.heartbeat.videodetail.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/5
 * @Description
 */
public class PersonalInfoPresenter extends DapporePresenter<PersonalInfoFragment> {


    @ThreadPoint(ThreadType.HTTP)
    public void requestPersonalInfo(String id) {

        PersonalInfoHttp http = createHttpRequest(PersonalInfoHttp.class);
        UserResponse response = http.requestPersonalInfo(new String[]{id});
        showFailMsg(response);
        if (isSuccess(response) && response.user != null) {
            L.i(initTag(), " requestPersonalInfo response.user " + response.user);
            getView().setLikeAndFansView(response.user.videoLikedCount, response.user.followersCount);
        }
    }

}
