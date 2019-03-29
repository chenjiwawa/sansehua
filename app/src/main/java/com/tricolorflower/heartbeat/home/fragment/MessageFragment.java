package com.tricolorflower.heartbeat.home.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.seal.Constants;
import com.tricolorflower.heartbeat.seal.extension.ChatListFragment;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


public class MessageFragment extends QsFragment {

    @Override
    public int layoutId() {
        return R.layout.fragment_message;
    }

    public static Fragment getInstance() {
        return new MessageFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        initRongIM();
        showContentView();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setContentFragment();
    }

    String id = Constants.meid;
    String token = Constants.metoken;

    private void initRongIM() {

        L.i(initTag(), " RongIM connect token " + token);
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
                setContentFragment();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.i(initTag(), " RongIM onError errorcode:" + errorCode.getValue());
                Toast.makeText(getContext(), " RongIM onError errorcode:" + errorCode.getValue(), Toast.LENGTH_LONG).show();
            }
        });
    }


    ChatListFragment fragment;

    protected void setContentFragment() {

        fragment = new ChatListFragment();

        Uri uri = Uri.parse("rong://" + getContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "true")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "true")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();

       L.i(initTag(), " setContentFragment uri " + uri.toString());

//        uri rong://cn.rongcloud.im/conversationlist?private=true&group=true&public_service=true&app_public_service=true&system=true


        fragment.setUri(uri);  //设置 ConverssationListFragment 的显示属性

        replace(fragment);
    }


    public void replace(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    public void action(View view) {
        if (fragment == null)
            return;

        fragment.showDelelteItemView();
    }


    public void del(View view) {
        if (fragment == null)
            return;

        fragment.showActionDoneView();
    }


}
