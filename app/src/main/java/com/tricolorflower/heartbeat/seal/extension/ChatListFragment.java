package com.tricolorflower.heartbeat.seal.extension;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.RongIMClient;

import static com.tricolorflower.heartbeat.seal.extension.ChatListAdapter.ACTION_DEL;

public class ChatListFragment extends ConversationListFragment implements ChatListAdapter.ItemListener {

    ChatListAdapter chatListAdapter = null;

    List<UIConversation> deleteList = new ArrayList<>();

    @Override
    public ChatListAdapter onResolveAdapter(Context context) {
        chatListAdapter = new ChatListAdapter(context, this);
        return chatListAdapter;
    }


    @Override
    public void onItemSelect(int position, UIConversation data) {
        Log.i(TAG, " onItemSelect " + position + " " + data);

        if (data == null)
            return;

        if (data.getExtraFlag()) {
            removeDeleteListItem(data);
        } else {
            addDeleteListItem(data);
        }

        data.setExtraFlag(!data.getExtraFlag());
        data.setUIConversationTitle(data.getUIConversationTitle() + "");
        chatListAdapter.add(data, position);
        chatListAdapter.remove(position);
        chatListAdapter.notifyDataSetChanged();

    }

    private void addDeleteListItem(UIConversation data) {
        if (data == null)
            return;

        if (deleteList != null) {
            deleteList.add(data);
        }
    }

    private void removeDeleteListItem(UIConversation data) {
        if (data == null)
            return;

        if (deleteList != null && deleteList.size() > 0) {
            Iterator iterator = deleteList.iterator();

            while (iterator.hasNext()) {
                UIConversation uiConversation = (UIConversation) iterator.next();
                if (uiConversation != null && uiConversation.getConversationType() == data.getConversationType() && uiConversation.getConversationTargetId().equals(data.getConversationTargetId())) {
                    iterator.remove();
                }
            }
        }
    }

    private void removeChatListItem() {
        if (deleteList != null && deleteList.size() > 0) {
            Iterator iterator = deleteList.iterator();

            while (iterator.hasNext()) {
                UIConversation uiConversation = (UIConversation) iterator.next();
                if (uiConversation != null) {
                    RongIM.getInstance().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), (RongIMClient.ResultCallback) null);
                    iterator.remove();
                }
            }
        }
    }

    public void showDelelteItemView() {
        if (chatListAdapter != null && chatListAdapter.getCount() > 0) {
            chatListAdapter.setAction(ACTION_DEL);
            UIConversation data = chatListAdapter.getItem(0);
            data.setExtra(System.currentTimeMillis() + "");
            chatListAdapter.add(data, 0);
            chatListAdapter.remove(0);
            chatListAdapter.notifyDataSetChanged();
        }
    }

    public void showActionDoneView() {
        if (chatListAdapter != null && chatListAdapter.getCount() > 0) {
            chatListAdapter.setAction("");

//            UIConversation data = chatListAdapter.getItem(0);
//            data.setExtra(System.currentTimeMillis() + "");
//            chatListAdapter.add(data, 0);
//            chatListAdapter.remove(0);

            removeChatListItem();
            chatListAdapter.notifyDataSetChanged();
        }
    }
}
