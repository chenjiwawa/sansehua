package com.zl.dappore.search.model;

import android.os.Bundle;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;

/**
 * 字工场原创字体
 *
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class SearchResultFragment3 extends QsFragment{


    @Override
    public int layoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData(Bundle bundle) {

    }

    public static SearchResultFragment3 getInstance(Bundle bundle) {
        return new SearchResultFragment3();
    }


    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager(String hotWord) {
    }


}
