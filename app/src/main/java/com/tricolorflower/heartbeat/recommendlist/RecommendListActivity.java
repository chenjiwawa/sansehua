package com.tricolorflower.heartbeat.recommendlist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.recommendlist.fragment.RecommendListFragment;
import com.tricolorflower.heartbeat.recommendlist.model.RecommendListConstants;

public class RecommendListActivity extends QsABActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title_back;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_recommendlist;
    }

    @Override
    public void initData(Bundle bundle) {
//        tv_title.setText("最新推荐");

        Bundle extras = getIntent().getExtras();
        tv_title.setText(extras.getString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_NAME, "最新推荐"));

        commitFragment(RecommendListFragment.getInstance(extras == null ? new Bundle() : extras));
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
