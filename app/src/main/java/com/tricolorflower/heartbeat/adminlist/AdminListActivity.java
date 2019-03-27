package com.tricolorflower.heartbeat.adminlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.addadminlist.AddAdminListActivity;
import com.tricolorflower.heartbeat.adminlist.fragment.AdminListFragment;
import com.tricolorflower.heartbeat.common.event.VoiceRoleOperationEvent;
import com.tricolorflower.heartbeat.common.event.VoiceRoomEvent;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseVoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;

import org.greenrobot.eventbus.Subscribe;

public class AdminListActivity extends QsABActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.confirm)
    TextView confirm;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_cancel_title_confirm;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_admin_list;
    }

    @Override
    public void initData(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        title.setText("管理员");
        confirm.setText("添加");

        commitFragment(AdminListFragment.getInstance(extras == null ? new Bundle() : extras));
    }

    @OnClick({R.id.cancel, R.id.confirm})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                activityFinish();
                break;
            case R.id.confirm:
                QsHelper.getInstance().intent2Activity(AddAdminListActivity.class, getIntent().getExtras());
                break;
        }
    }

}
