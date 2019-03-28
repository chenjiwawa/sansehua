package com.tricolorflower.heartbeat.voiceroom.fragment.chatroom;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.event.ChatRoomEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomOperationEvent;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.voiceroom.VoiceRoomSettingActivity;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.chatroom.ChatRoomInputMessageRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.presenter.ChatRoomInputMessagePresenter;
import com.tricolorflower.heartbeat.voiceroom.presenter.RoomOperationPresenter;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomInputMessageFragment extends QsFragment<ChatRoomInputMessagePresenter> {

    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.sendmessage)
    Button sendmessage;
    @Bind(R.id.inputmessage)
    EditText inputmessage;

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;
    String token;

    public static ChatRoomInputMessageFragment getInstance(Bundle extras) {
        ChatRoomInputMessageFragment fragment = new ChatRoomInputMessageFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_chatroom_input_message;
    }

    @Override
    public void initData(Bundle bundle) {
        initArgumentData();

        setView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        voiceRoom = (VoiceRoom) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) arguments.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);
        token = UserConfig.getInstance().getAuthToken();

        L.i(initTag(), " initArgumentData voiceRoom " + voiceRoom);
        L.i(initTag(), " initArgumentData voiceHolder " + voiceHolder);
        L.i(initTag(), " initArgumentData voiceClients " + voiceClients);
        L.i(initTag(), " initArgumentData user " + user);

    }

    public void sendMessage(String content) {
        if (voiceRoom == null)
            return;

        ChatRoomInputMessageRequestBody requestBody = new ChatRoomInputMessageRequestBody(token, voiceRoom.voiceRoomId, content, ChatRoomInputMessageRequestBody.TYPE_TEXT);
        getPresenter().sendMessage(requestBody);
    }

    private void setView() {

    }

    @OnClick({R.id.content, R.id.sendmessage})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.content:
                break;
            case R.id.sendmessage:
                if (TextUtils.isEmpty(inputmessage.getText().toString())) {
                    QsToast.show("请输入内容！");
                    return;
                }

                sendMessage(inputmessage.getText().toString());
                break;
        }
    }

    public void setSuccessView() {
        QsHelper.getInstance().eventPost(new ChatRoomEvent.OnDialogFragment(ChatRoomEvent.OnDialogFragment.State.DIDMISS));
    }
}
