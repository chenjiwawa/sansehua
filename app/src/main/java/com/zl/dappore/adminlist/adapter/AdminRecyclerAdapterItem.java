package com.zl.dappore.adminlist.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.dappore.voicerolelist.adapter.VoiceRoleRecyclerAdapterItem;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;


/**
 * Created by zhang on 2017/3/17.
 */
public class AdminRecyclerAdapterItem extends VoiceRoleRecyclerAdapterItem {

    public AdminRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected void onBindItemData(VoiceRoleList.VoiceRole data, int position, int totalCount) {
        super.onBindItemData(data, position, totalCount);

        llActionVoiceRole.setVisibility(View.VISIBLE);
        tvActionVoiceRole.setVisibility(View.GONE);
        btnActionVoiceRole.setText("移除");

    }

}