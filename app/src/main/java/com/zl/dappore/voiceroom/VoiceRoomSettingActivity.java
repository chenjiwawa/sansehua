package com.zl.dappore.voiceroom;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.common.event.VoiceRoomSettingEvent;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomGreetingDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomLabelDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomLockDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomLogoDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomNameDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomTypeDialogFragment;
import com.zl.dappore.voiceroom.model.VoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceRoomSettingPresenter;

public class VoiceRoomSettingActivity extends QsABActivity<VoiceRoomSettingPresenter> implements RoomNameDialogFragment.OnDialogListener, RoomLogoDialogFragment.OnDialogListener, RoomGreetingDialogFragment.OnDialogListener, RoomLockDialogFragment.OnDialogListener, RoomLabelDialogFragment.OnDialogListener, RoomTypeDialogFragment.OnDialogListener {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.rl_name_voiceroom_setting)
    RelativeLayout rlNameVoiceroomSetting;
    @Bind(R.id.rl_type_voiceroom_setting)
    RelativeLayout rlTypeVoiceroomSetting;
    @Bind(R.id.rl_lock_voiceroom_setting)
    RelativeLayout rlLockVoiceroomSetting;
    @Bind(R.id.rl_message_voiceroom_setting)
    RelativeLayout rlMessageVoiceroomSetting;
    @Bind(R.id.rl_label_voiceroom_setting)
    RelativeLayout rlLabelVoiceroomSetting;
    @Bind(R.id.rl_logo_voiceroom_setting)
    RelativeLayout rlLogoVoiceroomSetting;
    @Bind(R.id.rl_intro_voiceroom_setting)
    RelativeLayout rlIntroVoiceroomSetting;

    VoiceRoom room;
    VoiceRole user;
    Bundle bundle;
    String token = "";
    String roomId = "";

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title_back;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_voiceroom_setting;
    }

    @Override
    public void initData(Bundle bundle) {
        tv_title.setText("房间信息设置");

        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;

        room = (VoiceRoom) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        user = (VoiceRole) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " room " + room + " user " + user);

        showContentView();
    }


    @OnClick({R.id.ll_back, R.id.rl_name_voiceroom_setting, R.id.rl_type_voiceroom_setting, R.id.rl_lock_voiceroom_setting, R.id.rl_message_voiceroom_setting, R.id.rl_label_voiceroom_setting, R.id.rl_logo_voiceroom_setting, R.id.rl_intro_voiceroom_setting})
    public void onViewClick(View view) {
        super.onViewClick(view);

        bundle = new Bundle();
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER, user);
        bundle.putSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM, room);

        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
            case R.id.rl_name_voiceroom_setting:
                RoomNameDialogFragment roomNameDialogFragment = RoomNameDialogFragment.getInstance(bundle);
                roomNameDialogFragment.setOnDialogListener(this);
                roomNameDialogFragment.show();
                break;
            case R.id.rl_type_voiceroom_setting:
                RoomTypeDialogFragment roomTypeDialogFragment = RoomTypeDialogFragment.getInstance(bundle);
                roomTypeDialogFragment.setOnDialogListener(this);
                roomTypeDialogFragment.show();
                break;
            case R.id.rl_lock_voiceroom_setting:
                RoomLockDialogFragment roomLockDialogFragment = RoomLockDialogFragment.getInstance(bundle);
                roomLockDialogFragment.setOnDialogListener(this);
                roomLockDialogFragment.show();
                break;
            case R.id.rl_message_voiceroom_setting:
                break;
            case R.id.rl_label_voiceroom_setting:
                RoomLabelDialogFragment roomLabelDialogFragment = RoomLabelDialogFragment.getInstance(bundle);
                roomLabelDialogFragment.setOnDialogListener(this);
                roomLabelDialogFragment.show();
                break;
            case R.id.rl_logo_voiceroom_setting:
                RoomLogoDialogFragment roomLogoDialogFragment = RoomLogoDialogFragment.getInstance(bundle);
                roomLogoDialogFragment.setOnDialogListener(this);
                roomLogoDialogFragment.show();
                break;
            case R.id.rl_intro_voiceroom_setting:
                RoomGreetingDialogFragment roomGreetingDialogFragment = RoomGreetingDialogFragment.getInstance(bundle);
                roomGreetingDialogFragment.setOnDialogListener(this);
                roomGreetingDialogFragment.show();
                break;
        }
    }

    @Override
    public void onGreetingSetting(String data) {
        getPresenter().setVoiceRoomGreeting(token, roomId, data);
    }

    @Override
    public void onLabelSetting(String data) {
        getPresenter().setVoiceRoomLabel(token, roomId, data);
    }

    @Override
    public void onLockSetting(String data) {
        getPresenter().setVoiceRoomPwd(token, roomId, data);
    }

    @Override
    public void onLogoSetting(String data) {
        getPresenter().setVoiceRoomLogo(token, roomId, data);
    }

    @Override
    public void onNameSetting(String data) {
        getPresenter().setVoiceRoomName(token, roomId, data);
    }

    @Override
    public void onTypeSetting(String data) {
        getPresenter().setVoiceRoomType(token, roomId, data);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setSuccesView() {

        QsHelper.getInstance().eventPost(new VoiceRoomSettingEvent(VoiceRoomSettingEvent.State.STATE_FRESH_AFTER_SETTING));
    }
}
