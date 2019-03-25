package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.emoji;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.widget.itemdecoration.DividerGridItemDecoration;
import com.tricolorflower.heartbeat.voiceroom.adapter.EmojiGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.adapter.ProductGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.presenter.EmojiGridPresenter;
import com.tricolorflower.heartbeat.voiceroom.presenter.ProductGridPresenter;


public class EmojiGridFragment extends QsRecyclerFragment<EmojiGridPresenter, EmojiList.Emoji> {

    int pageNo = 1;
    int pageSize = 8;

    public static EmojiGridFragment getInstance(Bundle bundle) {
        EmojiGridFragment fragment = new EmojiGridFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initArgumentData();
        String token = UserConfig.getInstance().getAuthToken();
        requestData(token, pageNo);

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));
        showContentView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        pageNo = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_PRODUCTLIST_REQUEST_PAGE_NO, 1);
        L.i(initTag(), " initArgumentData pageNo " + pageNo);
    }


    public void requestData(String token, int page) {
        getPresenter().requestData(token, page);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new EmojiGridRecyclerAdapterItem(mInflater, parent);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

}