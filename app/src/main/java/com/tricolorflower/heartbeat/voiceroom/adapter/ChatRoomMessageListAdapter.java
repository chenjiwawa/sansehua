package com.tricolorflower.heartbeat.voiceroom.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.BaseMessageExtra;

import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.adapter.MessageListAdapter;
import io.rong.imlib.model.UserInfo;

public class ChatRoomMessageListAdapter extends MessageListAdapter {

    public final static String TAG = ChatRoomMessageListAdapter.class.getSimpleName();

    public ChatRoomMessageListAdapter(Context context) {
        super(context);
    }

//    @Override
//    protected void bindView(View v, int position, UIMessage data) {
//        if (data != null && data.mMessage != null) {
//            data.mMessage.setMessageDirection(Message.MessageDirection.RECEIVE);
//        }
//        super.bindView(v, position, data);
//        final MessageListAdapter.ViewHolder holder = (MessageListAdapter.ViewHolder) v.getTag();
//        if (holder != null) {
//            holder.leftIconView.setVisibility(View.GONE);
//        }
//    }

    @Override
    protected void bindView(View v, int position, UIMessage data) {
        if (data == null)
            return;

//        if (data != null && data.mMessage != null) {
//            data.mMessage.setMessageDirection(Message.MessageDirection.RECEIVE);
//        }

        super.bindView(v, position, data);

        final ChatRoomMessageListAdapter.ViewHolder holder = (ChatRoomMessageListAdapter.ViewHolder) v.getTag();
        if (holder != null) {
            holder.layoutItem.setVisibility(View.GONE);
            holder.itemLayoutChatRoom.setVisibility(View.VISIBLE);
            StringBuffer stringBuffer = new StringBuffer();

//            if (holder.nameView.getText() != null && !TextUtils.isEmpty(holder.nameView.getText().toString())) {
//                stringBuffer.append(holder.nameView.getText().toString()).append(":");
//            } else {
//                if (data != null && data.mMessage != null) {
//                    if (data.mMessage.getMessageDirection() == Message.MessageDirection.SEND)
//                    {
//                        UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(data.mMessage.getSenderUserId());
//                        if (userInfo != null) {
//                            stringBuffer.append(userInfo.getName()).append(":");
//                        }
//                    }
//                }
//            }

            String name = "";
            if (data.getUserInfo() != null) {
                name = data.getUserInfo().getName();
            } else {
                if (data.mMessage != null) {
                    UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(data.mMessage.getSenderUserId());
                    Log.i(TAG, " contentView userInfo " + userInfo);
                    if (userInfo != null) {
                        name = userInfo.getName();
                    } else {
                        name = data.mMessage.getSenderUserId();
                    }
                } else {
                    name = "guest";
                }
            }
            stringBuffer.append(name).append(":");

            for (int i = 0; i < holder.contentView.getChildCount(); i++) {
                View child = holder.contentView.getChildAt(i);
                if (child instanceof TextView) {
                    Log.i(TAG, " contentView child " + position + " " + ((TextView) child).getText().toString());

                    stringBuffer.append(((TextView) child).getText().toString());
                }
                if (child instanceof ViewGroup) {
                    Log.i(TAG, " contentView child ViewGroup " + position + " ");
                }
            }

//            if (data != null && data.getUserInfo() != null) {
//                stringBuffer.append(data.getUserInfo().getName()).append(":");
//            }
//            if (data != null && data.getMessage() != null) {
//                stringBuffer.append(data.getMessage().getContent());
//            }

            holder.contentItemChatRoom.setText(stringBuffer.toString());

            if (data.getMessage() != null && !TextUtils.isEmpty(data.getMessage().getExtra())) {
                BaseMessageExtra baseMessageExtra = new BaseMessageExtra();
                baseMessageExtra = baseMessageExtra.parse(data.getMessage().getExtra());

                if (baseMessageExtra != null && baseMessageExtra.isDisplayInClient()) {
                    String messageExtraContentStr = baseMessageExtra.messageExtraContentStr;

                }
            }

        }


        if (data != null) {
            Log.i(TAG, " UIMessage " + position + " " + data.toString());

            if (data.getMessage() != null) {
                Log.i(TAG, " UIMessage getMessage " + position + " " + data.getMessage().toString());
            }

            if (data.getUserInfo() != null) {
                Log.i(TAG, " UIMessage getUserInfo " + position + " " + data.getUserInfo().toString());
            }

        }
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        View result = super.newView(context, position, group);
        ChatRoomMessageListAdapter.ViewHolder holder = new ChatRoomMessageListAdapter.ViewHolder();
        holder.initViewHolder((MessageListAdapter.ViewHolder) result.getTag());
        holder.info1ItemChatRoom = this.findViewById(result, R.id.tv_info1_item_chatroom);
        holder.info2ItemChatRoom = this.findViewById(result, R.id.tv_info2_item_chatroom);
        holder.extraItemChatRoom = this.findViewById(result, R.id.tv_extra_item_chatroom);
        holder.contentItemChatRoom = this.findViewById(result, R.id.tv_content_item_chatroom);
        holder.itemLayoutChatRoom = this.findViewById(result, R.id.rl_layout_item_chatroom);
        result.setTag(holder);
        return result;
    }

    protected class ViewHolder extends MessageListAdapter.ViewHolder {

        public TextView info1ItemChatRoom;
        public TextView info2ItemChatRoom;
        public TextView extraItemChatRoom;
        public TextView contentItemChatRoom;
        public RelativeLayout itemLayoutChatRoom;

        protected ViewHolder() {
            super();
        }

        public void initViewHolder(MessageListAdapter.ViewHolder holder) {
            layout = holder.layout;

            leftIconView = holder.leftIconView;
            rightIconView = holder.rightIconView;
            nameView = holder.nameView;
            contentView = holder.contentView;
            progressBar = holder.progressBar;
            warning = holder.warning;
            readReceipt = holder.readReceipt;
            readReceiptRequest = holder.readReceiptRequest;
            readReceiptStatus = holder.readReceiptStatus;
            layout = holder.layout;
            time = holder.time;
            sentStatus = holder.sentStatus;
            layoutItem = holder.layoutItem;
            message_check = holder.message_check;
            checkboxLayout = holder.checkboxLayout;
            sendTimeView = holder.sendTimeView;
            receiveTimeView = holder.receiveTimeView;
        }
    }


}
