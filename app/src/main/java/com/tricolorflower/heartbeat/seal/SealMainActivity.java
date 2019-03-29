package com.tricolorflower.heartbeat.seal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.seal.extension.ChatActivity;
import com.tricolorflower.heartbeat.seal.extension.ChatListActivity;

import java.util.HashMap;
import java.util.Map;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class SealMainActivity extends FragmentActivity {

    public final static String TAG = SealMainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal_main);

        findViewById(R.id.chatroom11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " chatroom11", Toast.LENGTH_LONG).show();
                ChatRoom11(v);
            }
        });

        findViewById(R.id.chatroom12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " chatroom12", Toast.LENGTH_LONG).show();
                ChatRoom12(v);
            }
        });

        findViewById(R.id.ChatRoom13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " ChatRoom13", Toast.LENGTH_LONG).show();
                ChatRoom13(v);
            }
        });

        findViewById(R.id.chatroom21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " chatroom21", Toast.LENGTH_LONG).show();
                ChatRoom21(v);
            }
        });

        findViewById(R.id.chatroom22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " chatroom22", Toast.LENGTH_LONG).show();
                ChatRoom22(v);
            }
        });

        findViewById(R.id.me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " me", Toast.LENGTH_LONG).show();
                me(v);
            }
        });

        findViewById(R.id.other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " other", Toast.LENGTH_LONG).show();
                other(v);
            }
        });

        findViewById(R.id.defaultchatroom1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " defaultchatroom1", Toast.LENGTH_LONG).show();
                defaultchatroom1(v);
            }
        });

//        initRongIM();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    String id = "";
    String token = "";

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
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.e(TAG, " RongIM onError errorcode:" + errorCode.getValue());
                Toast.makeText(getApplicationContext(), " RongIM onError errorcode:" + errorCode.getValue(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ConversationList(View view) {

        startConversationList();
    }

    private void startConversationList() {
        Map<String, Boolean> map = new HashMap<>();
        map.put(Conversation.ConversationType.PRIVATE.getName(), true); // 会话列表需要显示私聊会话, 第二个参数 true 代表私聊会话需要聚合显示
        map.put(Conversation.ConversationType.GROUP.getName(), false);  // 会话列表需要显示群组会话, 第二个参数 false 代表群组会话不需要聚合显示

        RongIM.getInstance().startConversationList(this.getApplicationContext(), map);
    }

    public void Conversation(View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RongIM.getInstance().startConversation(getBaseContext(), Conversation.ConversationType.PRIVATE, "11", "name11");
            }
        });
    }

    public void ConversationList2(View view) {
    }

    public void Conversation2(View view) {

        RongIM.getInstance().startConversation(getBaseContext(), Conversation.ConversationType.PRIVATE, "glxgK6CVP", "nameglxgK6CVP");

    }

    public void ChatList(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatListActivity.class);
        startActivity(intent);
    }

    public void Chat(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    public void ChatRoom11(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatRoom11Activity.class);
        startActivity(intent);
    }

    public void ChatRoom12(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatRoom12Activity.class);
        startActivity(intent);
    }

    public void ChatRoom13(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatRoom13Activity.class);
        startActivity(intent);
    }

    public void ChatRoom21(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatRoom21Activity.class);
        startActivity(intent);
    }

    public void ChatRoom22(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, ChatRoom22Activity.class);
        startActivity(intent);
    }

    public void me(View view) {

        id = "1ZezSpDKJ";
        token = "e8hQINj2SGb+8ifkKP8oWg11i2+YC6CNkzO4cqLKL7wOawXvLCo/RD2B59jVuNqhviAhXGj2ZGTOD0iO5lW5tGc8CQ3lAAL4";

        initRongIM();
    }

    public void other(View view) {

        id = "glxgK6CVP";
        token = "fjTvXavLwRQ5vwpmrNJEid/uyJaByiUMT7trB7cMNkTtHvJY0M9K7WPTEkHuWVucsNxEd2q74MoGKdW3DWcYOQ==";

        initRongIM();
    }

    public void defaultchatroom1(View view) {
        Intent intent = new Intent();
        intent.setClass(SealMainActivity.this, DefaultChatRoom1Activity.class);
        startActivity(intent);
    }

}
