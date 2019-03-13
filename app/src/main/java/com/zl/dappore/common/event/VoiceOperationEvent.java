package com.zl.dappore.common.event;

public class VoiceOperationEvent {

    public static class onLogin {
        public LoginState mLoginState;
        public int        mRequestCode;

        public onLogin(LoginState state, int requestCode) {
            this.mLoginState = state;
            this.mRequestCode = requestCode;
        }
    }

    public enum LoginState {
        STATE_SUCCESS,
        STATE_FAIL,
        STATE_LOGOUT
    }
}
