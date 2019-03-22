package com.tricolorflower.heartbeat.voiceroom.fragment.chatroom;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.dialog.QsDialogFragment;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

public class InputMessageDialogFragment extends QsDialogFragment {

    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.sendmessage)
    Button sendmessage;
    @Bind(R.id.inputmessage)
    EditText inputmessage;

    public static InputMessageDialogFragment getInstance() {
        return new InputMessageDialogFragment();
    }

    @Override
    protected int getDialogTheme() {
        return R.style.evaluate_dialog_style;
    }

    @Override
    protected View getDialogView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_input_message, viewGroup);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    public void onStart() {
        super.onStart();
        L.i(initTag(), " onStart ");

        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @OnClick({R.id.content, R.id.sendmessage})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.content:
                break;
            case R.id.sendmessage:
                if (TextUtils.isEmpty(inputmessage.getText().toString())) {
                    QsToast.show("请输入内容！");
                    return;
                }

                TextMessage myTextMessage = TextMessage.obtain(inputmessage.getText().toString());
                Message myMessage = Message.obtain(VoiceRoomConstants.chatroonId, Conversation.ConversationType.CHATROOM, myTextMessage);

                RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                    @Override
                    public void onAttached(Message message) {
                        //消息本地数据库存储成功的回调

                        L.i(initTag(), " onAttached " + message);
                    }

                    @Override
                    public void onSuccess(Message message) {
                        //消息通过网络发送成功的回调

                        L.i(initTag(), " onSuccess " + message);
                    }

                    @Override
                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                        //消息发送失败的回调

                        L.i(initTag(), " onError " + message + " " + errorCode);
                    }
                });

                break;
        }
        dismissAllowingStateLoss();
    }
}
