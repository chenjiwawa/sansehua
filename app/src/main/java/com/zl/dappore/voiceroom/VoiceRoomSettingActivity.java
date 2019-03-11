package com.zl.dappore.voiceroom;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;

public class VoiceRoomSettingActivity extends QsABActivity {
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
//        tv_title.setText("最新推荐");
        tv_title.setText("房间信息设置");
        Bundle extras = getIntent().getExtras();

        showContentView();
    }


    @OnClick({R.id.ll_back,R.id.rl_name_voiceroom_setting, R.id.rl_type_voiceroom_setting, R.id.rl_lock_voiceroom_setting, R.id.rl_message_voiceroom_setting, R.id.rl_label_voiceroom_setting, R.id.rl_logo_voiceroom_setting, R.id.rl_intro_voiceroom_setting})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
            case R.id.rl_name_voiceroom_setting:
                break;
            case R.id.rl_type_voiceroom_setting:
                break;
            case R.id.rl_lock_voiceroom_setting:
                break;
            case R.id.rl_message_voiceroom_setting:
                break;
            case R.id.rl_label_voiceroom_setting:
                break;
            case R.id.rl_logo_voiceroom_setting:
                break;
            case R.id.rl_intro_voiceroom_setting:
                break;
        }
    }
}
