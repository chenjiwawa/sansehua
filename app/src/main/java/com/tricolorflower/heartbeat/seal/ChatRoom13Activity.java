package com.tricolorflower.heartbeat.seal;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.seal.extension.ChatRoomFragment;

import java.util.Locale;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ChatRoom13Activity extends FragmentActivity {

    public final static String TAG = ChatRoom13Activity.class.getSimpleName();
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom13);
        content = (LinearLayout) findViewById(R.id.content);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                initRongIM();
//            }
//        }).start();

        initRongIM();
    }

    String id = "";
    String token = "";

    private void initRongIM() {

        id = "uwUBmOqXv";
        token = "xdopgHStomBspAQeUSvdfq+YsUIoF3ojin3K277sfOkI+CVP3rXUkOrCmQBy+eXBNEVvYzFurTFodc4YYdyycatdpZUyLdaH";

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

                if(Constants.isChatRoomExtentsion){
                    setChatRoomFragment();
                }else {
                    setContentFragment();
                }
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

    String targetId = Constants.chatRoomId;
    String title = Constants.chatRoomName;

    protected void setContentFragment() {

        ConversationFragment fragment = new ConversationFragment();

        Uri uri = Uri.parse("rong://" + Constants.PROCESSNAME_RONGCLOUD_IM)
                .buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase(Locale.US))
                .appendQueryParameter("targetId", targetId)
                .appendQueryParameter("title", title).build();

        Log.i(TAG, " setContentFragment uri " + uri.toString());

        fragment.setUri(uri);

        replace(fragment);
    }

    protected void setChatRoomFragment() {

        ChatRoomFragment fragment = new ChatRoomFragment();

        Uri uri = Uri.parse("rong://" + Constants.PROCESSNAME_RONGCLOUD_IM)
                .buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase(Locale.US))
                .appendQueryParameter("targetId", targetId)
                .appendQueryParameter("title", title).build();

        Log.i(TAG, " setContentFragment uri " + uri.toString());
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

}
