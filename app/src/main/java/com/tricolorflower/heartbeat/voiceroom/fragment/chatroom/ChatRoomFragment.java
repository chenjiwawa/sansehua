package com.tricolorflower.heartbeat.voiceroom.fragment.chatroom;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.seal.Constants;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


public class ChatRoomFragment extends QsFragment {

    @Bind(R.id.content)
    protected RelativeLayout content;
    @Bind(R.id.fragmentlayout)
    protected RelativeLayout fragmentlayout;

    @Override
    public int layoutId() {
        return R.layout.fragment_chat_room;
    }

    public static Fragment getInstance() {
        return new ChatRoomFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showContentView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRongIM();
    }

    String id = Constants.meid;
    String token = Constants.metoken;

    private void initRongIM() {

        L.i(initTag(), " RongIM connect token " + token);
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                L.i(initTag(), " RongIM onTokenIncorrect");
                Toast.makeText(getContext(), " RongIM onTokenIncorrect", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(String s) {
                L.i(initTag(), " RongIM onSuccess userid:" + s);
                Toast.makeText(getContext(), " RongIM onSuccess userid:" + s, Toast.LENGTH_LONG).show();

                if (Constants.isChatRoomExtentsion) {
                    setChatRoomMessageListFragment();
                } else {
                    setContentFragment();
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.i(initTag(), " RongIM onError errorcode:" + errorCode.getValue());
                Toast.makeText(getContext(), " RongIM onError errorcode:" + errorCode.getValue(), Toast.LENGTH_LONG).show();
            }
        });
    }

    String targetId = Constants.chatRoomId;
    String title = Constants.chatRoomName + "";

    protected void setContentFragment() {

        ConversationFragment fragment = new ConversationFragment();

        Uri uri = Uri.parse("rong://" + Constants.PROCESSNAME_RONGCLOUD_IM)
                .buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase(Locale.US))
                .appendQueryParameter("targetId", targetId)
                .appendQueryParameter("title", title).build();

        L.i(initTag(), " setContentFragment uri " + uri.toString());

        fragment.setUri(uri);

        replace(fragment);
    }

    protected void setChatRoomMessageListFragment() {

        ChatRoomMessageListFragment fragment = new ChatRoomMessageListFragment();

        Uri uri = Uri.parse("rong://" + Constants.PROCESSNAME_RONGCLOUD_IM)
                .buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase(Locale.US))
                .appendQueryParameter("targetId", targetId)
                .appendQueryParameter("title", title).build();

        L.i(initTag(), " setContentFragment uri " + uri.toString());
// def_chatroom1 uri rong://cn.rongcloud.im/conversation/chatroom?targetId=OIBbeKlkx&title=%E8%81%8A%E5%A4%A9%E5%AE%A4%20I

        fragment.setUri(uri);

        replace(fragment);
    }

    //        if (!mSecondFragment.isAdded()) {
//            fragmentTransaction.add(R.id.fragment_container1, mSecondFragment);
//        }
//        fragmentTransaction.remove(mSecondFragment);
//        if (!mSecondFragment.isAdded()) {
//            fragmentTransaction.replace(R.id.fragment_container2, mSecondFragment);
//        }

    public void replace(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragmentlayout, fragment);
        fragmentTransaction.commit();
    }

}
