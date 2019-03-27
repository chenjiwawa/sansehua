package com.tricolorflower.heartbeat.voicerolelist.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

/**
 * Created by zhang on 2017/3/17.
 */
public class VoiceRoleRecyclerAdapterItem extends QsRecycleAdapterItem<VoiceRole> {

    protected VoiceRole data;

    public VoiceRoleRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_voice_role;
    }

    @Override
    protected void onBindItemData(VoiceRole data, int position, int totalCount) {
        this.data = data;
    }
}