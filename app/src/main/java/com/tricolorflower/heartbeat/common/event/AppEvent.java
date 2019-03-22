package com.tricolorflower.heartbeat.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/8  下午1:20
 * @Description
 */
public class AppEvent {

    /**
     * 检测到新版本
     */
    public static class HasNewVersion {
        public boolean hasNewVersion;

        public HasNewVersion(boolean hasNewVersion) {
            this.hasNewVersion = hasNewVersion;
        }
    }

    /**
     * 全局字体下载事件，当字体下载状态改变时执行
     */
    public static class OnFontDownloadEvent {
        public static final int STATE_FINISH      = 1 << 1;
        public static final int STATE_FAIL        = 2 << 1;
        public static final int STATE_PAUSE       = 3 << 1;
        public static final int STATE_DOWNLOADING = 4 << 1;
        public static final int STATE_START       = 5 << 1;
        public static final int STATE_WAITE       = 6 << 1;

        public int    downloadState;
        public String fontId;
        public String fontName;

        public OnFontDownloadEvent(int downloadState, String fontId, String fontName) {
            this.downloadState = downloadState;
            this.fontId = fontId;
            this.fontName = fontName;
        }
    }
}
