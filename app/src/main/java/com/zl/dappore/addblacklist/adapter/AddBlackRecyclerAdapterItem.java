package com.zl.dappore.addblacklist.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.dappore.voicerolelist.adapter.VoiceRoleRecyclerAdapterItem;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;


/**
 * Created by zhang on 2017/3/17.
 */
public class AddBlackRecyclerAdapterItem extends VoiceRoleRecyclerAdapterItem {

    public AddBlackRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
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