package com.tricolorflower.heartbeat.voiceroom.fragment.chatroom;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.seal.extension.ChatRoomAdapter;
import com.tricolorflower.heartbeat.voiceroom.adapter.ChatRoomMessageListAdapter;

import io.rong.imkit.RongExtension;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.widget.adapter.MessageListAdapter;

public class ChatRoomMessageListFragment extends ConversationFragment {

//    RongExtension rcExtension;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        RongExtension rcExtension = view.findViewById(R.id.rc_extension);
        rcExtension.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public MessageListAdapter onResolveAdapter(Context context) {
        return new ChatRoomMessageListAdapter(context);
    }


}
