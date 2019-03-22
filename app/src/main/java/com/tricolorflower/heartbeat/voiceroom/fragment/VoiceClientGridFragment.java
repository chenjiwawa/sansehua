package com.tricolorflower.heartbeat.voiceroom.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.adapter.VoiceClientGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.listener.OnVoiceClientListener;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.presenter.VoiceClientGridPresenter;

import java.util.List;


public class VoiceClientGridFragment extends QsRecyclerFragment<VoiceClientGridPresenter, VoiceRole> {

    public static VoiceClientGridFragment getInstance() {
        VoiceClientGridFragment fragment = new VoiceClientGridFragment();
        return fragment;
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_voice_client_grid;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));

        showContentView();
    }


    public void requstData() {
        getPresenter().requstData();
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateVoiceClientGridView(List<VoiceRole> data) {
        if (data == null || data.size() <= 0)
            return;

        L.i(initTag(), " updateVoiceClientGridView " + data);
        setData(data);

    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        OnVoiceClientListener onVoiceClientListener = null;
        if (getParentFragment() != null && getParentFragment() instanceof OnVoiceClientListener) {
            onVoiceClientListener = (OnVoiceClientListener) getParentFragment();
        }
        return new VoiceClientGridRecyclerAdapterItem(mInflater, parent, onVoiceClientListener);
    }
}