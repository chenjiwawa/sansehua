package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.tricolorflower.heartbeat.common.http.VoiceRoomSettingHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomsetting.TypeGridFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.BaseVoiceRoomSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.TypeList;


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