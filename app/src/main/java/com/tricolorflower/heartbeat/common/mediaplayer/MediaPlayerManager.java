package com.tricolorflower.heartbeat.common.mediaplayer;

import android.media.MediaPlayer;
import android.text.TextUtils;

import com.qsmaxmin.qsbase.common.log.L;

import java.io.IOException;

public class MediaPlayerManager {
    private static final String TAG = MediaPlayerManager.class.getSimpleName();

    private static MediaPlayerManager mediaPlayerManager;

    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_COMPLETED = 5;

    private String mCurrentUrl = "";
    private int mCurrentState;
    private int mSeekWhenPrepared;  // recording the seek position while preparing

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;


    public static MediaPlayerManager getInstance() {
        if (mediaPlayerManager == null) {
            synchronized (MediaPlayerManager.class) {
                if (mediaPlayerManager == null) {
                    mediaPlayerManager = new MediaPlayerManager();
                }
            }
        }
        return mediaPlayerManager;
    }

    private MediaPlayerManager() {
        mCurrentState = STATE_IDLE;
        mSeekWhenPrepared = 0;
    }

    public void play(String url) {
        play(url, false);
    }

    public String getUrl() {
        return mCurrentUrl;
    }

    public void play(String url, boolean loop) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mCurrentUrl = url;

        try {
            stopPlayback();
            mMediaPlayer.setDataSource(url);
            //异步准备
            mMediaPlayer.prepareAsync();
            mCurrentState = STATE_PREPARING;

            if (loop) {
                mMediaPlayer.setLooping(true);
            }

            //添加准备好的监听
            mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnCompletionListener(mCompleteListener);
        } catch (IOException e) {
            L.e(TAG, "Unable to open content: " + url);
            mCurrentState = STATE_ERROR;
        } catch (IllegalArgumentException e) {
            L.e(TAG, "Unable to open content: " + url);
            mCurrentState = STATE_ERROR;
        }
    }

    public boolean isPlaying() {
        return isInPlaybackState() && mMediaPlayer.isPlaying();
    }

    public void start() {
        if (isInPlaybackState() && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            mCurrentState = STATE_PLAYING;
        }
    }

    public void pause() {
        if (isInPlaybackState() && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mCurrentState = STATE_PAUSED;
        }
    }

    public void stopPlayback() {
        if (mMediaPlayer != null) {
            if (isInPlaybackState()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mCurrentState = STATE_IDLE;
        }
    }

    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
            mCurrentState = STATE_IDLE;
            mCurrentUrl = "";
        }
    }

    public int getDuration() {
        if (isInPlaybackState()) {
            return mMediaPlayer.getDuration();
        }

        return -1;
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int msec) {
        if (isInPlaybackState()) {
            mMediaPlayer.seekTo(msec);
            mSeekWhenPrepared = 0;
        } else {
            mSeekWhenPrepared = msec;
        }
    }

    public void seekToPlay(String url, int msec) {
        mSeekWhenPrepared = msec;
        play(url);
    }

    public boolean isInPlaybackState() {
        return mMediaPlayer != null &&
                mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        mOnPreparedListener = l;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener l) {
        mOnCompletionListener = l;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener l) {
        mOnErrorListener = l;
    }

    // 加载完成监听
    private MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mCurrentState = STATE_PREPARED;

            if (mOnPreparedListener != null) {
                mOnPreparedListener.onPrepared(mMediaPlayer);
            }

            int seekWhenPrepared = mSeekWhenPrepared;
            if (seekWhenPrepared != 0) {
                seekTo(seekWhenPrepared);
            }

            start();
        }
    };

    // 播放错误监听
    private MediaPlayer.OnErrorListener mErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            L.e(TAG, "Play error: " + what + ", " + extra);
            mCurrentState = STATE_ERROR;
            if (mOnErrorListener != null) {
                if (mOnErrorListener.onError(mMediaPlayer, what, extra)) {
                    return true;
                }
            }
            return true;
        }
    };

    // 播放完成监听
    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mCurrentState = STATE_COMPLETED;
            if (mOnCompletionListener != null) {
                mOnCompletionListener.onCompletion(mMediaPlayer);
            }
        }
    };

}
