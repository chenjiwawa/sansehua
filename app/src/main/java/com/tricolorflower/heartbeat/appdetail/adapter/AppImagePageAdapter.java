package com.tricolorflower.heartbeat.appdetail.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tricolorflower.heartbeat.R;
import com.qsmaxmin.qsbase.common.utils.QsHelper;

import java.util.List;

public class AppImagePageAdapter extends PagerAdapter {
    private List<String> images;

    public AppImagePageAdapter(List<String> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        if (images != null) {
            return images.size();
        }

        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RelativeLayout relativeLayout = new RelativeLayout(container.getContext());
        relativeLayout.setGravity(RelativeLayout.CENTER_IN_PARENT);

        ImageView imageView = new ImageView(container.getContext());
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        imageParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(imageParams);
        QsHelper.getInstance().getImageHelper().createRequest().centerCrop().load(images.get(position)).into(imageView);

        ImageView coverView = new ImageView(container.getContext());
        coverView.setVisibility(View.GONE);
        RelativeLayout.LayoutParams coverParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        coverParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        coverView.setLayoutParams(coverParams);
        coverView.setBackgroundResource(R.mipmap.ic_appdetal_play);

        relativeLayout.addView(imageView);
        relativeLayout.addView(coverView);

        container.addView(relativeLayout);

        return relativeLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //   super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
