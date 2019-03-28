package com.tricolorflower.heartbeat.addadminlist;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.addadminlist.fragment.AddAdminListFragment;
import com.tricolorflower.heartbeat.adminlist.fragment.AdminListFragment;

public class AddAdminListActivity extends QsABActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.input)
    EditText input;
    @Bind(R.id.search)
    Button search;
    @Bind(R.id.btn_commit_add_admin_list)
    Button commit;

    AddAdminListFragment fragment;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_cancel_title;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_add_admin_list;
    }

    @Override
    public void initData(Bundle bundle) {
        title.setText("用户列表");

        Bundle extras = getIntent().getExtras();
        fragment = AddAdminListFragment.getInstance(extras == null ? new Bundle() : extras);
        commitFragment(fragment);
    }

    @OnClick({R.id.cancel, R.id.confirm, R.id.search, R.id.btn_confrim_admin_list})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                activityFinish();
                break;
            case R.id.confirm:
                QsHelper.getInstance().intent2Activity(AddAdminListActivity.class, getIntent().getExtras());
                break;
            case R.id.search:
                String searchContent = input.getText().toString();
                if (TextUtils.isEmpty(searchContent)) {
                    QsToast.show("请输入搜索内容！");
                    return;
                }

                if (fragment != null) {
                    fragment.requestData(searchContent);
                }
                break;
            case R.id.btn_confrim_admin_list:
                if (fragment != null) {
                    fragment.commit();
                }
                break;
        }
    }

}
