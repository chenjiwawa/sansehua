package com.tricolorflower.heartbeat.search;

import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.utils.KeyboardHelper;
import com.tricolorflower.heartbeat.search.fragment.SearchDefaultFragment;
import com.tricolorflower.heartbeat.search.fragment.SearchResultFragment;
import com.tricolorflower.heartbeat.search.model.SearchAppRequstModel;
import com.tricolorflower.heartbeat.search.model.SearchConstants;
import com.tricolorflower.heartbeat.search.presenter.SearcherPresenter;

import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/3
 * @Description 搜索入口
 */
public class SearcherActivity extends QsABActivity<SearcherPresenter> implements TextWatcher, TextView.OnEditorActionListener {

    @Bind(R.id.et_input)
    AppCompatAutoCompleteTextView et_input;
    @Bind(R.id.iv_clean)
    ImageView iv_clean;
    @Bind(R.id.ll_actionbar)
    LinearLayout ll_actionbar;

    ArrayAdapter<String> arrayAdapter;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_searcher;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getPresenter().saveToSearchHistory("火币");
        getPresenter().saveToSearchHistory("比特币");
        getPresenter().saveToSearchHistory("莱特币");
        getPresenter().saveToSearchHistory("人名币");

        commitFragment(R.id.rl_content_search, SearchDefaultFragment.getInstance(), SearchDefaultFragment.class.getSimpleName());
        if (!executeSearchIfCan()) {
//            KeyboardHelper.showSoftInputDelay(et_input);
        }
        initEditText();
        getPresenter().requestSearchHistory();
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateEditText(List<String> searchHistory) {
        L.i(initTag(), " updateEditText searchHistory " + searchHistory);

        if (searchHistory != null && !searchHistory.isEmpty()) {
            arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.item_search_history, searchHistory);
            et_input.setAdapter(arrayAdapter);
        } else {
            arrayAdapter = null;
            et_input.setAdapter(null);
        }
        SearchDefaultFragment defaultFragment = getDefaultFragment();
        L.i(initTag(), " updateEditText defaultFragment " + defaultFragment);
        if (defaultFragment != null) {
            defaultFragment.updateUI(searchHistory);
        }
    }

    public void cleanCache() {
        getPresenter().cleanSearchHistory();
    }

    @ThreadPoint(ThreadType.MAIN)
    private void initEditText() {
        et_input.addTextChangedListener(this);
        et_input.setOnEditorActionListener(this);
        et_input.setDropDownVerticalOffset(CommonUtils.dp2px(5));
        et_input.setDropDownBackgroundResource(R.drawable.shape_rect_lightgray_1dp_stroke);
        et_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null && view instanceof TextView) {
                    TextView textView = (TextView) view;
                    executeSearch(textView.getText().toString());
                }
            }
        });
    }

    private boolean executeSearchIfCan() {
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            SearchAppRequstModel modelReq = (SearchAppRequstModel) bundle.getSerializable(SearchConstants.BUNDLE_KEY_SEARCH_MODEL);
//            if (modelReq != null && modelReq.searchMap != null && !TextUtils.isEmpty(modelReq.searchMap.name)) {
//                executeSearch(modelReq.searchMap.name);
//                return true;
//            }
//        }
        return false;
    }


    @OnClick({R.id.iv_clean, R.id.tv_cancel})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.iv_clean:
                setEditTextStr("");
                break;
            case R.id.tv_cancel:
                activityFinish(true);
//                executeSearch(et_input.getText().toString().trim());
                break;
        }
    }


    private void applyBack(boolean canFinish) {
        SearchResultFragment resultFragment = getResultFragment();
        if (resultFragment != null) {
            SearchDefaultFragment defaultFragment = getDefaultFragment();
            if (defaultFragment != null) {
                getPresenter().requestSearchHistory();
            }
            onBackPressed();
        } else {
            if (canFinish) {
                onBackPressed();
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String inputStr = et_input.getText().toString().trim();
        if (TextUtils.isEmpty(inputStr)) {
            iv_clean.setVisibility(View.GONE);
            applyBack(false);
        } else {
            iv_clean.setVisibility(View.VISIBLE);
        }
        L.i(initTag(), "afterTextChanged arrayAdapter " + arrayAdapter);
        if (arrayAdapter != null) {
            L.i(initTag(), "afterTextChanged arrayAdapter " + arrayAdapter.getCount());
        }
        et_input.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            executeSearch(et_input.getText().toString().trim());
        }
        return false;
    }

    private void setEditTextStr(String searchStr) {
        et_input.setText(searchStr);
        et_input.setSelection(et_input.getText().length());
    }

    /**
     * 请求执行搜索字体事件
     */
    @ThreadPoint(ThreadType.MAIN)
    public void executeSearch(String input) {
        L.i(initTag(), "executeSearch input " + input);

        if (TextUtils.isEmpty(input)) {
            QsToast.show("请输入搜索关键词！");
            return;
        }
        KeyboardHelper.hideSoftInput(SearcherActivity.this);
        et_input.setAdapter(null);
        et_input.dismissDropDown();
        SearchAppRequstModel requstModel = new SearchAppRequstModel(new SearchAppRequstModel.SearchMapModel(input));
        setEditTextStr(input);
        getPresenter().saveToSearchHistory(input);
        SearchResultFragment resultFragment = getResultFragment();
        if (resultFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(SearchConstants.BUNDLE_KEY_SEARCH_MODEL, requstModel);
            commitBackStackFragment(R.id.rl_content_search, SearchResultFragment.getInstance(bundle), SearchResultFragment.class.getSimpleName());
        } else {
            L.i(initTag(), "afterTextChanged requstModel " + requstModel);
            resultFragment.updateViewPager(requstModel.searchMap.name);
        }
    }

    private SearchDefaultFragment getDefaultFragment() {
        return (SearchDefaultFragment) getSupportFragmentManager().findFragmentByTag(SearchDefaultFragment.class.getSimpleName());
    }

    private SearchResultFragment getResultFragment() {
        return (SearchResultFragment) getSupportFragmentManager().findFragmentByTag(SearchResultFragment.class.getSimpleName());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KeyboardHelper.hideSoftInputFromWindow(this);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(KeyEvent event, int keyCode) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            applyBack(true);
            return true;
        }
        return super.onKeyDown(event, keyCode);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
