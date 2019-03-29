package com.tricolorflower.heartbeat.seal.extension;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.seal.Constants;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ChatListActivity extends FragmentActivity {

    public final static String TAG = ChatListActivity.class.getSimpleName();
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        content = (LinearLayout) findViewById(R.id.content);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                initRongIM();
//            }
//        }).start();

        initRongIM();
    }

    String id = Constants.meid;
    String token = Constants.metoken;

    private void initRongIM() {

        L.e(TAG, " RongIM connect token " + token);
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                L.e(TAG, " RongIM onTokenIncorrect");
                Toast.makeText(getApplicationContext(), " RongIM onTokenIncorrect", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(String s) {
                L.e(TAG, " RongIM onSuccess userid:" + s);
                Toast.makeText(getApplicationContext(), " RongIM onSuccess userid:" + s, Toast.LENGTH_LONG).show();
                setContentFragment();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.e(TAG, " RongIM onError errorcode:" + errorCode.getValue());
                Toast.makeText(getApplicationContext(), " RongIM onError errorcode:" + errorCode.getValue(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    ChatListFragment fragment;

    protected void setContentFragment() {

        fragment = new ChatListFragment();

        Uri uri = Uri.parse("rong://" + getBaseContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "true")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "true")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();

        Log.i(TAG, " setContentFragment uri " + uri.toString());

//        uri rong://cn.rongcloud.im/conversationlist?private=true&group=true&public_service=true&app_public_service=true&system=true


        fragment.setUri(uri);  //设置 ConverssationListFragment 的显示属性

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
        FragmentManager fragmentManager = getSupportFragmentManager();
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
