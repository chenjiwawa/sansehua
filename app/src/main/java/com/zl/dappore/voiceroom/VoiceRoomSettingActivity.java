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
import com.zl.dappore.common.model.UserConfig;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomGreetingDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomLabelDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomLockDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomLogoDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomNameDialogFragment;
import com.zl.dappore.voiceroom.fragment.roomsetting.RoomTypeDialogFragment;
import com.zl.dappore.voiceroom.model.voicerole.VoiceRole;
import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
import com.zl.dappore.voiceroom.presenter.VoiceRoomSettingPresenter;

import java.util.ArrayList;
import java.util.List;

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

    Bundle bundle;
    String token = "";
    String voiceRoomId = "";

    VoiceRoom voiceRoom;
    VoiceRole voiceHolder;
    List<VoiceRole> voiceClients;
    VoiceRole user;

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
        initExtrasData();

        tv_title.setText("房间信息设置");
        showContentView();
    }

    private void initExtrasData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;

        voiceRoom = (VoiceRoom) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " initExtrasData voiceRoom " + voiceRoom);
        L.i(initTag(), " initExtrasData voiceHolder " + voiceHolder);
        L.i(initTag(), " initExtrasData voiceClients " + voiceClients);
        L.i(initTag(), " initExtrasData user " + user);

        if (voiceRoom != null) {
            voiceRoomId = voiceRoom.voiceRoomId;
        }
        token = UserConfig.getInstance().getAuthToken();
        
        L.i(initTag(), " initExtrasData voiceRoomId " + voiceRoomId+" token " + token);
    }

    @OnClick({R.id.ll_back, R.id.rl_name_voiceroom_setting, R.id.rl_type_voiceroom_setting, R.id.rl_lock_voiceroom_setting, R.id.rl_message_voiceroom_setting, R.id.rl_label_voiceroom_setting, R.id.rl_logo_voiceroom_setting, R.id.rl_intro_voiceroom_setting})
    public void onViewClick(View view) {
        super.onViewClick(view);

        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
            case R.id.rl_name_voiceroom_setting:
                RoomNameDialogFragment roomNameDialogFragment = RoomNameDialogFragment.getInstance(getIntent().getExtras());
                roomNameDialogFragment.setOnDialogListener(this);
                roomNameDialogFragment.show();
                break;
            case R.id.rl_type_voiceroom_setting:
                RoomTypeDialogFragment roomTypeDialogFragment = RoomTypeDialogFragment.getInstance(getIntent().getExtras());
                roomTypeDialogFragment.setOnDialogListener(this);
                roomTypeDialogFragment.show();
                break;
            case R.id.rl_lock_voiceroom_setting:
                RoomLockDialogFragment roomLockDialogFragment = RoomLockDialogFragment.getInstance(getIntent().getExtras());
                roomLockDialogFragment.setOnDialogListener(this);
                roomLockDialogFragment.show();
                break;
            case R.id.rl_message_voiceroom_setting:
                break;
            case R.id.rl_label_voiceroom_setting:
                RoomLabelDialogFragment roomLabelDialogFragment = RoomLabelDialogFragment.getInstance(getIntent().getExtras());
                roomLabelDialogFragment.setOnDialogListener(this);
                roomLabelDialogFragment.show();
                break;
            case R.id.rl_logo_voiceroom_setting:
                RoomLogoDialogFragment roomLogoDialogFragment = RoomLogoDialogFragment.getInstance(getIntent().getExtras());
                roomLogoDialogFragment.setOnDialogListener(this);
                roomLogoDialogFragment.show();
                break;
            case R.id.rl_intro_voiceroom_setting:
                RoomGreetingDialogFragment roomGreetingDialogFragment = RoomGreetingDialogFragment.getInstance(getIntent().getExtras());
                roomGreetingDialogFragment.setOnDialogListener(this);
                roomGreetingDialogFragment.show();
                break;
        }
    }

    @Override
    public void onGreetingSetting(String data) {
        getPresenter().setVoiceRoomGreeting(token, voiceRoomId, data);
    }

    @Override
    public void onLabelSetting(String data) {
        getPresenter().setVoiceRoomLabel(token, voiceRoomId, data);
    }

    @Override
    public void onLockSetting(String data) {
        getPresenter().setVoiceRoomPwd(token, voiceRoomId, data);
    }

    @Override
    public void onLogoSetting(String data) {
        getPresenter().setVoiceRoomLogo(token, voiceRoomId, data);
    }

    @Override
    public void onNameSetting(String data) {
        getPresenter().setVoiceRoomName(token, voiceRoomId, data);
    }

    @Override
    public void onTypeSetting(String data) {
        getPresenter().setVoiceRoomType(token, voiceRoomId, data);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setSuccesView() {

        QsHelper.getInstance().eventPost(new VoiceRoomSettingEvent(VoiceRoomSettingEvent.State.STATE_FRESH_AFTER_SETTING));
    }
}
