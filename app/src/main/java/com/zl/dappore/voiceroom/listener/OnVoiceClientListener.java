package com.zl.dappore.voiceroom.listener;

import com.zl.dappore.voiceroom.model.VoiceRole;

public interface OnVoiceClientListener {

    void onItemSelect(VoiceRole data, int position, int totalCount);
}