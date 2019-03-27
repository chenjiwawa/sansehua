package com.tricolorflower.heartbeat.addblacklist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.addadminlist.AddAdminListActivity;
import com.tricolorflower.heartbeat.addblacklist.fragment.AddBlackListFragment;

public class AddBlackListActivity extends QsABActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.btn_commit_add_black_list)
    Button commit;

    AddBlackListFragment fragment;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_cancel_title;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_add_black_list;
    }

    @Override
    public void initData(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        title.setText("添加黑名单");

        fragment=AddBlackListFragment.getInstance(extras == null ? new Bundle() : extras);
        commitFragment(fragment);
    }

    @OnClick({R.id.cancel, R.id.btn_confrim_admin_list})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                activityFinish();
                break;
            case R.id.btn_confrim_admin_list:
                if (fragment != null) {
                    fragment.commit();
                }
                break;
        }
    }

}
