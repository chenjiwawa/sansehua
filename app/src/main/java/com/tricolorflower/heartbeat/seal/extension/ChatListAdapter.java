package com.tricolorflower.heartbeat.seal.extension;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tricolorflower.heartbeat.R;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;

public class ChatListAdapter extends ConversationListAdapter {
    public final static String TAG = ChatListAdapter.class.getSimpleName();
    public final static String ACTION_DEL = "del";

    Context mContext;
    ItemListener itemListener;
    private String action = "";

    public ChatListAdapter(Context context, ItemListener itemListener) {
        super(context);
        this.mContext = context;
        this.itemListener = itemListener;
    }

    @Override
    protected void bindView(View v, final int position, final UIConversation data) {

        data.setUIConversationTitle(data.getUIConversationTitle() + "-");

        super.bindView(v, position, data);
        ChatListAdapter.ViewHolder holder = (ChatListAdapter.ViewHolder) v.getTag();

        if (!TextUtils.isEmpty(action) && action.equals(ACTION_DEL)) {
            holder.selectItemLayout.setVisibility(View.VISIBLE);
            holder.selectItemPlaceHolderView.setVisibility(View.INVISIBLE);
            if (data.getExtraFlag()) {
                holder.selectItemView.setBackground(mContext.getResources().getDrawable(R.drawable.rc_ic_star_hover));
            } else {
                holder.selectItemView.setBackground(mContext.getResources().getDrawable(R.drawable.rc_ic_star));
            }
        } else {
            holder.selectItemLayout.setVisibility(View.GONE);
            holder.selectItemPlaceHolderView.setVisibility(View.GONE);
        }

//        holder.selectItemLayout.setVisibility(View.VISIBLE);
//        holder.selectItemPlaceHolderView.setVisibility(View.INVISIBLE);

        holder.selectItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    itemListener.onItemSelect(position, data);
                }
                Log.i(TAG, " bindView onClick " + position + " " + data);
            }
        });
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        View result = super.newView(context, position, group);
        ChatListAdapter.ViewHolder holder = new ViewHolder();
        holder.initViewHolder((ConversationListAdapter.ViewHolder) result.getTag());
        holder.selectItemPlaceHolderView = this.findViewById(result, R.id.btn_placeholder_select_item);
        holder.selectItemView = this.findViewById(result, R.id.btn_select_item_conversation);
        holder.selectItemLayout = this.findViewById(result, R.id.rl_select_item_conversation);
        result.setTag(holder);
        return result;
    }

    protected class ViewHolder extends ConversationListAdapter.ViewHolder {

        public Button selectItemPlaceHolderView;
        public Button selectItemView;
        public RelativeLayout selectItemLayout;

        protected ViewHolder() {
            super();
        }

        public void initViewHolder(ConversationListAdapter.ViewHolder holder) {
            layout = holder.layout;
            leftImageLayout = holder.leftImageLayout;
            rightImageLayout = holder.rightImageLayout;
            leftUnReadView = holder.leftUnReadView;
            rightUnReadView = holder.rightUnReadView;
            leftImageView = holder.leftImageView;
            rightImageView = holder.rightImageView;
            contentView = holder.contentView;
            unReadMsgCount = holder.unReadMsgCount;
            unReadMsgCountRight = holder.unReadMsgCountRight;
            unReadMsgCountIcon = holder.unReadMsgCountIcon;
            unReadMsgCountRightIcon = holder.unReadMsgCountRightIcon;
        }

        private void initViewHolder2(ConversationListAdapter.ViewHolder holder) {
            holder.layout = holder.layout;
            holder.leftImageLayout = holder.leftImageLayout;
            holder.rightImageLayout = holder.rightImageLayout;
            holder.leftUnReadView = holder.leftUnReadView;
            holder.rightUnReadView = holder.rightUnReadView;
            holder.leftImageView = holder.leftImageView;
            holder.rightImageView = holder.rightImageView;
            holder.contentView = holder.contentView;
            holder.unReadMsgCount = holder.unReadMsgCount;
            holder.unReadMsgCountRight = holder.unReadMsgCountRight;
            holder.unReadMsgCountIcon = holder.unReadMsgCountIcon;
            holder.unReadMsgCountRightIcon = holder.unReadMsgCountRightIcon;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    interface ItemListener {
        public void onItemSelect(int position, UIConversation data);
    }
}
