package com.tricolorflower.heartbeat.voiceroom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.ProductCategoryFragment;

public class ProductCategoryActivity extends QsABActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title_back;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_product_category;
    }

    @Override
    public void initData(Bundle bundle) {
//        tv_title.setText("最新推荐");
        tv_title.setText("");

        commitFragment(ProductCategoryFragment.getInstance(bundle));

        showContentView();
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
