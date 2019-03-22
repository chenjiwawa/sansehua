package com.tricolorflower.heartbeat.common.agora;

import android.util.Log;

import com.aspsine.multithreaddownload.util.L;
import com.qsmaxmin.qsbase.QsApplication;
import com.qsmaxmin.qsbase.common.exception.QsException;
import com.qsmaxmin.qsbase.common.exception.QsExceptionType;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.tricolorflower.heartbeat.R;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

public class AgoraHelper {
    public static final String TAG = AgoraHelper.class.getSimpleName();

    private static AgoraHelper helper = new AgoraHelper();

    private QsApplication mApplication;
    private RtcEngine mRtcEngine;
    private boolean isLogOpen = false;
    IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onJoinChannelSuccess " + channel + " " + uid + " " + elapsed);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onJoinChannelSuccess(channel, uid, elapsed);
            }
        }

        @Override
        public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onRejoinChannelSuccess(channel, uid, elapsed);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onRejoinChannelSuccess " + channel + " " + uid + " " + elapsed);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onRejoinChannelSuccess(channel, uid, elapsed);
            }
        }

        @Override
        public void onLeaveChannel(RtcStats stats) {
            super.onLeaveChannel(stats);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onLeaveChannel " + stats);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onLeaveChannel(stats);
            }
        }

        @Override
        public void onClientRoleChanged(int oldRole, int newRole) {
            super.onClientRoleChanged(oldRole, newRole);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onClientRoleChanged " + oldRole + " " + newRole);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onClientRoleChanged(oldRole, newRole);
            }
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onClientRoleChanged " + " " + uid + " " + elapsed);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onUserJoined(uid, elapsed);
            }
        }

        @Override
        public void onUserOffline(int uid, int reason) {
            super.onUserOffline(uid, reason);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onClientRoleChanged " + " " + uid + " " + reason);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onUserOffline(uid, reason);
            }
        }

        @Override
        public void onUserMuteAudio(int uid, boolean muted) {
            super.onUserMuteAudio(uid, muted);
            AgoraLog.i(TAG, " mRtcEventHandler " + " onClientRoleChanged " + " " + uid + " " + muted);

            if (iRtcEngineEventListener != null) {
                iRtcEngineEventListener.onUserMuteAudio(uid, muted);
            }
        }
    };

    IRtcEngineEventListener iRtcEngineEventListener;

    private AgoraHelper() {
    }

    public static AgoraHelper getInstance() {
        return helper;
    }

    public void init(QsApplication application) {
        if (application == null) {
            throw new QsException(QsExceptionType.UNEXPECTED, TAG + " init ", " application == null ");
        }

        mApplication = application;
        initializeAgoraEngine();
    }

    public void setIRtcEngineEventListener(IRtcEngineEventListener iRtcEngineEventListener) {
        this.iRtcEngineEventListener = iRtcEngineEventListener;
    }

    public QsApplication getApplication() {
        return mApplication;
    }


    private void initializeAgoraEngine() {
        AgoraLog.init(true);
        try {
            mRtcEngine = RtcEngine.create(mApplication, QsHelper.getInstance().getString(R.string.agora_app_id), mRtcEventHandler);
        } catch (Exception e) {
            L.i(TAG, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    private void checkRtcEngine() {
        if (mRtcEngine == null) {
            throw new QsException(QsExceptionType.UNEXPECTED, TAG + " checkRtcEngine ", " mRtcEngine == null ");
        }
    }

    public void joinChannel(String channelName, int optionalUid) {
        checkRtcEngine();

        mRtcEngine.joinChannel(null, channelName, "Extra Optional Data", optionalUid);
    }

    public void leaveChannel() {
        checkRtcEngine();

        mRtcEngine.leaveChannel();
    }

    public void setClientRole(int clientRole) {
        checkRtcEngine();

        mRtcEngine.setClientRole(clientRole);
    }

    public void setClientRoleBroadcaster() {
        checkRtcEngine();

        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
    }

    public void setClientRoleAudience() {
        checkRtcEngine();

        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
    }

    public void enableAudio() {
        checkRtcEngine();

        mRtcEngine.enableAudio();
    }

    public void disableAudio() {
        checkRtcEngine();

        mRtcEngine.disableAudio();
    }

    public void muteLocalAudioStream(boolean muted) {
        checkRtcEngine();

        mRtcEngine.muteLocalAudioStream(muted);
    }

    public void muteRemoteAudioStream(int uid, boolean muted) {
        checkRtcEngine();

        mRtcEngine.muteRemoteAudioStream(uid, muted);
    }

}
