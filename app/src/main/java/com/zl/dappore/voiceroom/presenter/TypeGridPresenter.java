package com.zl.dappore.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.http.VoiceRoomSettingHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.voiceroom.fragment.roomsetting.LabelGridFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.TypeGridFragment;
import com.zl.dappore.voiceroom.model.BaseVoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.TypeList;
import com.zl.dappore.voiceroom.model.VoiceRoomTypeRequestBody;


public class TypeGridPresenter extends DapporePresenter<TypeGridFragment> {


    int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestData(String token) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        TypeList response = http.getVoiceRoomTypeList(new BaseVoiceRoomRequestBody(token));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().setData(response.data);
        } else {
            getView().showErrorView();
        }
    }

}