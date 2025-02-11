package com.zl.dappore.userinfo.presenter;


import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.account.model.UserResponse;
import com.zl.dappore.common.http.PersonalInfoHttp;
import com.zl.dappore.common.http.VideoListHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.GridItemVideo;
import com.zl.dappore.home.model.VideoList;
import com.zl.dappore.userinfo.fragment.PersonalInfoFragment;
import com.zl.dappore.userinfo.fragment.PersonalVideoFragment;
import com.zl.dappore.videodetail.model.Video;

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
