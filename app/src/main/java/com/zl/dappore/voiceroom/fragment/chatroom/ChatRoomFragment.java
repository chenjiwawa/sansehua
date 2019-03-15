package com.zl.dappore.voiceroom.fragment.chatroom;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;

import java.util.Locale;

import io.rong.imkit.fragment.ConversationFragment;
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
        setContentFragment();
    }

    protected void setContentFragment() {
        getActivity().getIntent().putExtra("createIfNotExist", true);
        String targetId = "1";

        ConversationFragment fragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase(Locale.US))
                .appendQueryParameter("targetId", targetId)
                .appendQueryParameter("title", "聊天室 I")
                .build();
        fragment.setUri(uri);

        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, fragment, ConversationFragment.class.getSimpleName());
    }

}
