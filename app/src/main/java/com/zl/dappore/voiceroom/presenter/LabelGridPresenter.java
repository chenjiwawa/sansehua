package com.zl.dappore.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.zl.dappore.common.http.VoiceRoomSettingHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.voiceroom.fragment.roomsetting.TypeGridFragment;
import com.zl.dappore.voiceroom.model.voiceroomsetting.BaseVoiceRoomSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.TypeList;


public class LabelGridPresenter extends DapporePresenter<TypeGridFragment> {


    int page = 1;

    @ThreadPoint(ThreadType.HTTP)
    public void requestData(String token) {
        VoiceRoomSettingHttp http = createHttpRequest(VoiceRoomSettingHttp.class);
        TypeList response = http.getVoiceRoomTypeList(new BaseVoiceRoomSettingRequestBody(token));
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().setData(response.data);
        } else {
            getView().showErrorView();
        }
    }

}