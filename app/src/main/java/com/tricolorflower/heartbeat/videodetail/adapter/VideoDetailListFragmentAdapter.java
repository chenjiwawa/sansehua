package com.tricolorflower.heartbeat.videodetail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tricolorflower.heartbeat.videodetail.fragment.VideoDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailListFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public VideoDetailListFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((VideoDetailFragment)fragments.get(position)).getTitle();
    }

    public static class Holder {
        private final List<Fragment> fragments = new ArrayList<>();
        private FragmentManager manager;
        public Holder(FragmentManager manager) {
            this.manager = manager;
        }

        public Holder add(Fragment f) {
            fragments.add(f);
            return this;
        }

        public VideoDetailListFragmentAdapter set() {
            return new VideoDetailListFragmentAdapter(manager, fragments);
        }

        public List<Fragment> getFragments() {
            return fragments;
        }
    }
}
