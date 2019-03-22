package com.tricolorflower.heartbeat.voiceroom.listener;

import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

public interface OnVoiceClientListener {

    void onItemSelect(VoiceRole data, int position, int totalCount);
}