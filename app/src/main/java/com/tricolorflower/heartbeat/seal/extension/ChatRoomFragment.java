package com.tricolorflower.heartbeat.seal.extension;

import android.content.Context;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.widget.adapter.MessageListAdapter;

public class ChatRoomFragment extends ConversationFragment {

    @Override
    public MessageListAdapter onResolveAdapter(Context context) {
        return new ChatRoomAdapter(context);
    }

}
