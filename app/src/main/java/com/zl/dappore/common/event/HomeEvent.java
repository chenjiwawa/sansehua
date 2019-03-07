package com.zl.dappore.common.event;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class HomeEvent {

    /**
     * DeepLink使用，HomeActivity监听
     */
    public static class OnScrollViewpager {
        public int index;

        public OnScrollViewpager(int index) {
            this.index = index;
        }
    }
}
