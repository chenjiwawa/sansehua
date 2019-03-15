package com.zl.dappore.home.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsFragment;
import com.zl.dappore.R;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;


public class MessageFragment extends QsFragment {

    @Bind(R.id.content)
    protected RelativeLayout content;
    @Bind(R.id.fragmentlayout)
    protected RelativeLayout fragmentlayout;

    @Override
    public int layoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void onActionBar() {
        setActivityTitle("mine");
    }

    public static Fragment getInstance() {
        return new MessageFragment();
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

        ConversationListFragment fragment = new ConversationListFragment();

        Uri uri = Uri.parse("rong://" + getContext().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();

        fragment.setUri(uri);  //设置 ConverssationListFragment 的显示属性

        QsHelper.getInstance().commitFragment(getChildFragmentManager(), R.id.fragmentlayout, fragment, ConversationListFragment.class.getSimpleName());
    }

}
