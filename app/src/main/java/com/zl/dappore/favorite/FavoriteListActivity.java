package com.zl.dappore.favorite;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.favorite.fragment.FavoriteListFragment;

public class FavoriteListActivity extends QsABActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title_back;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_favorite_list;
    }

    @Override
    public void initData(Bundle bundle) {
//        tv_title.setText("最新推荐");
        tv_title.setText("收藏");

        commitFragment(FavoriteListFragment.getInstance());
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
