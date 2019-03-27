package com.tricolorflower.heartbeat.common.listener;

import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;

public interface ItemListener<T> {

    public void onItemClick(T data, int position, int totalCount);


}
