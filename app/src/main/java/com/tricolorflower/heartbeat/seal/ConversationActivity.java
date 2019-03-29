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
import com.tricolorflower.heartbeat.seal.extension.ChatListActivity;

import java.util.Locale;

import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ConversationActivity extends FragmentActivity {

    public final static String TAG = ChatListActivity.class.getSimpleName();
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        content = (LinearLayout) findViewById(R.id.content);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                initRongIM();
//            }
//        }).start();

        initRongIM();
    }

    private void initRongIM() {

        String id = "1";
        String token = "";
        String token1 = "IlESoZoBS7SKwNJdSsprvalf58DRmwpDSJE23SJ68mSyzXhsxHg9iONIXo4YoIbPsKvzm2Gt1UR0Mz6mqNFYRQ==";
        String token11 = "5Pn1Rp1T0lQGMN5gxXWwlalf58DRmwpDSJE23SJ68mSyzXhsxHg9iFKvLZZ3/rm25hgd//9DZUZ0Mz6mqNFYRQ==";
        if (id.equals("1")) {
            token = token1;
        }
        if (id.equals("11")) {
            token = token11;
        }
        id = "1ZezSpDKJ";
        token = "e8hQINj2SGb+8ifkKP8oWg11i2+YC6CNkzO4cqLKL7wOawXvLCo/RD2B59jVuNqhviAhXGj2ZGTOD0iO5lW5tGc8CQ3lAAL4";

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

    String targetId = "glxgK6CVP";
    String title = "Name" + targetId;

    protected void setContentFragment() {

        ConversationFragment fragment = new ConversationFragment();

        Uri uri = Uri.parse("rong://" + Constants.PROCESSNAME_RONGCLOUD_IM)
                .buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase(Locale.US))
                .appendQueryParameter("targetId", targetId)
                .appendQueryParameter("title", title).build();

        Log.i(TAG, " setContentFragment uri " + uri.toString());

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
