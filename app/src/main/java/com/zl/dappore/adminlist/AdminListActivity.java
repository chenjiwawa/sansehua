package com.zl.dappore.adminlist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.addadminlist.AddAdminListActivity;
import com.zl.dappore.adminlist.fragment.AdminListFragment;
import com.zl.dappore.voiceroom.model.voicerole.BaseVoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;

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
                Bundle bundle = new Bundle();
                bundle.putString(VoiceRoomConstants.BUNDLE_KEY_REQUEST_ID, "1");
                bundle.putInt(VoiceRoomConstants.BUNDLE_KEY_REQUEST_VOICE_ROLE, BaseVoiceRole.VOICE_HOLDER);
                QsHelper.getInstance().intent2Activity(AddAdminListActivity.class, bundle);
                break;
        }
    }

}
