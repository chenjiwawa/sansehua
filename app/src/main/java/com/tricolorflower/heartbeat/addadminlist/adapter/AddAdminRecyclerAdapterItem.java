package com.tricolorflower.heartbeat.addadminlist.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tricolorflower.heartbeat.voicerolelist.adapter.VoiceRoleRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;


/**
 * Created by zhang on 2017/3/17.
 */
public class AddAdminRecyclerAdapterItem extends VoiceRoleRecyclerAdapterItem {

    public AddAdminRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected void onBindItemData(VoiceRoleList.VoiceRole data, int position, int totalCount) {
        super.onBindItemData(data, position, totalCount);

        llActionVoiceRole.setVisibility(View.VISIBLE);
        btnActionVoiceRole.setVisibility(View.GONE);
        if (data.isSelect) {
            tvActionVoiceRole.setText("已选中");
        } else {
            tvActionVoiceRole.setText("");
        }

    }

}