package com.zl.dappore.common.agora;

import io.agora.rtc.IRtcEngineEventHandler;

public interface IRtcEngineEventListener {

    public void onJoinChannelSuccess(String channel, int uid, int elapsed);

    public void onRejoinChannelSuccess(String channel, int uid, int elapsed);

    public void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats);

    public void onClientRoleChanged(int oldRole, int newRole);

    public void onUserJoined(int uid, int elapsed);

    public void onUserOffline(int uid, int reason);

    public void onUserMuteAudio(int uid, boolean muted);

}
