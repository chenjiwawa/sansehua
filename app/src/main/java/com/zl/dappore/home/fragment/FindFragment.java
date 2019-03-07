package com.zl.dappore.home.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;
import com.zl.dappore.common.widget.textview.ExpandTxtTextView;


public class FindFragment extends QsFragment {

    @Bind(R.id.text1)
    ExpandTxtTextView text1;
    @Bind(R.id.text2)
    TextView text2;
    @Bind(R.id.text3)
    TextView text3;

    @Override
    public int layoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void onActionBar() {
        setActivityTitle("mine");
    }

    public static Fragment getInstance() {
        return new FindFragment();
    }


    @Override
    public void initData(Bundle savedInstanceState) {

        text1.setText("网易旗下利用大数据技术提供移动互联网应用的子公司,过去8年,先后推出有道词典、有道翻译官、有道云笔记、惠惠网、有道推广、有道精品课、有道口语大师等系列产品,总网易旗下利用大数据技术提供移动互联网应用的子公司,过去8年,先后推出有道词典、有道翻译官、有道云笔记、惠惠网、有道推广、有道精品课、有道口语大师等系列产品,总网易旗下利用大数据技术提供移动互联网应用的子公司,过去8年,先后推出有道词典、有道翻译官、有道云笔记、惠惠网、有道推广、有道精品课、有道口语大师等系列产品,总");
        text1.setTrimCollapsedText("展开");
        text1.setTrimExpandedText("收起");
        text1.setTrimMode(0);
        text1.setTrimLines(1);

        String text = "网易旗下利用大数据技术提供移动互联网应用的子公司,过去8年,先后推出有道词典、有道翻译官、有道云笔记、惠惠网、有道推广、有道精品课、有道口语大师等系列产品,总网易旗下利用大数据技术提供移动互联网应用的子公司！!";
//        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, text.length());
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_detail_up);
//        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//        s.setSpan(imageSpan, text.length() - 1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

//        SpannableString spanString = new SpannableString(text);
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_test);
//        ImageSpan imageSpan = new ImageSpan(drawable);
//        spanString.setSpan(imageSpan, spanString.length() - 2, spanString.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        text2.setText(spanString);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test);
        ImageSpan imageSpan = new ImageSpan(getContext(), bitmap);
        SpannableString spannableString = new SpannableString("网易旗下利用大数据技术提供移动互联网应用的子公司,过去8年,先后推出有道词典、有道翻译官、有道云笔记、惠惠网、有道推广、有道精品课!");
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text2.setText(spannableString);

        text3.setText("2018TVB颁奖典礼已经在昨日举行完毕，这个颁奖典礼受到的关注度还是蛮高的，尤其是最终的获奖名单更是成为一大焦点。现在TVB视帝视后大奖已经揭晓，千年老二马国明再陪跑，而他风光无限。");
    }


    @Override
    public boolean isOpenViewState() {
        return false;
    }

}
