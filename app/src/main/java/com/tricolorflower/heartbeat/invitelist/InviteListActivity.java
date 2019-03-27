package com.tricolorflower.heartbeat.invitelist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.invitelist.fragment.InviteListFragment;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.util.ArrayList;
import java.util.List;

public class InviteListActivity extends QsABActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;

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
        return R.layout.activity_voice_role_list;
    }

    @Override
    public void initData(Bundle bundle) {

        initExtrasData();

        Bundle extras = getIntent().getExtras();
        commitFragment(InviteListFragment.getInstance(extras == null ? new Bundle() : extras));

        if (voiceRoom != null) {
            tv_title.setText(voiceRoom.voiceRoomName + "");
        }
    }

    private void initExtrasData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) return;

        voiceRoom = (VoiceRoom) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROOM);
        voiceHolder = (VoiceRole) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_HOLDER);
        voiceClients = (ArrayList<VoiceRole>) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_CLIENTS);
        user = (VoiceRole) extras.getSerializable(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE_USER);

        L.i(initTag(), " initExtrasData voiceRoom " + voiceRoom);
        L.i(initTag(), " initExtrasData voiceHolder " + voiceHolder);
        L.i(initTag(), " initExtrasData voiceClients " + voiceClients);
        L.i(initTag(), " initExtrasData user " + user);

    }

    @OnClick({R.id.ll_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
        }
    }

}
