package com.zl.dappore.blacklist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.addblacklist.AddBlackListActivity;
import com.zl.dappore.blacklist.fragment.BlackListFragment;
import com.zl.dappore.voiceroom.VoiceRoomActivity;
import com.zl.dappore.voiceroom.model.BaseVoiceRole;
import com.zl.dappore.voiceroom.model.VoiceRoomConstants;

public class BlackListActivity extends QsABActivity {

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
        return R.layout.activity_black_list;
    }

    @Override
    public void initData(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        title.setText("黑名单");
        confirm.setText("添加");

        commitFragment(BlackListFragment.getInstance(extras == null ? new Bundle() : extras));
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
                QsHelper.getInstance().intent2Activity(AddBlackListActivity.class, bundle);
                break;
        }
    }

}
