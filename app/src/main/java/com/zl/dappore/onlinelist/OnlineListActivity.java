package com.zl.dappore.onlinelist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.onlinelist.fragment.OnlineListFragment;

public class OnlineListActivity extends QsABActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;

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
//        tv_title.setText("最新推荐");

        Bundle extras = getIntent().getExtras();
        tv_title.setText("用户列表");

        commitFragment(OnlineListFragment.getInstance(extras == null ? new Bundle() : extras));
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
