package com.zl.dappore.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.widget.flowlayout.FlowLayout;
import com.zl.dappore.search.SearcherActivity;
import com.zl.dappore.search.model.SearchConstants;
import com.zl.dappore.search.presenter.SearchDefaultPresenter;

import java.util.List;


/**
 * @CreateBy qsmaxmin
 * @Date 16/8/3
 * @Description 搜索默认页面
 */
public class SearchDefaultFragment extends QsFragment<SearchDefaultPresenter> {

    @Nullable
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Nullable
    @Bind(R.id.tv_empty)
    TextView tv_empty;
    @Bind(R.id.fl_container)
    FlowLayout fl_container;

    int aDp;

    @Override
    public int layoutId() {
        return R.layout.fragment_searchdefault;
    }

    public static SearchDefaultFragment getInstance() {
        return new SearchDefaultFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (tv_empty != null) {
            tv_empty.setText("搜索你关注的币！");
        }
        if (iv_empty != null) {
        }
        aDp = CommonUtils.dp2px(1);

        showContentView();
    }

    @Override
    public void onActionBar() {
        setActivityTitle("", SearchConstants.KEY_TO_SEARCH_DEFAULT);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateUI(List<String> list) {
        L.i(initTag(), " updateUI " + list);
        if (list != null && list.size() > 0) {
            List<String> finalList = list.subList(0, Math.min(10, list.size()));
            fl_container.removeAllViews();
            for (String str : finalList) {
                fl_container.addView(createTextView(str));
            }
            showContentView();
        } else {
            showEmptyView();
        }
    }

    private TextView createTextView(final String str) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(13f);
        textView.setPadding(aDp * 19, aDp * 7, aDp * 19, aDp * 7);
        textView.setText(str);
        textView.setTextColor(QsHelper.getInstance().getColor(R.color.color_stroke_gray));
        textView.setBackgroundColor(QsHelper.getInstance().getColor(R.color.color_bg_gray));
        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setGravity(Gravity.CENTER);
        layoutParams.setMargins(aDp * 8, aDp * 8, 8, aDp * 8);
        textView.setLayoutParams(layoutParams);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof SearcherActivity) {
                    ((SearcherActivity) getActivity()).executeSearch(str);
                }
            }
        });
        return textView;
    }

    @OnClick({R.id.iv_clean_history, R.id.tv_clean_history})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.iv_clean_history:
            case R.id.tv_clean_history:
                FragmentActivity activity = getActivity();
                if (activity instanceof SearcherActivity) {
                    SearcherActivity searcherActivity = (SearcherActivity) activity;
                    searcherActivity.cleanCache();
                }
                break;
        }
    }

}
