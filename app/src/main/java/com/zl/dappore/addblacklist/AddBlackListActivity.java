package com.zl.dappore.addblacklist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.addblacklist.fragment.AddBlackListFragment;

public class AddBlackListActivity extends QsABActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.search)
    Button search;
    @Bind(R.id.input)
    EditText input;


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

        commitFragment(AddBlackListFragment.getInstance(extras == null ? new Bundle() : extras));
    }

    @OnClick({R.id.cancel, R.id.confirm})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                activityFinish();
                break;
            case R.id.confirm:
                break;
        }
    }

}
