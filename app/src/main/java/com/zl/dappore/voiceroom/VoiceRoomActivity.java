package com.zl.dappore.voiceroom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.QsABActivity;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.fragment.AppDetailFragment;
import com.zl.dappore.voiceroom.fragment.VoiceRoomFragment;

public class VoiceRoomActivity extends QsABActivity {
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    public int actionbarLayoutId() {
        return R.layout.actionbar_title_back;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_voice_room;
    }

    @Override
    public void initData(Bundle bundle) {
//        tv_title.setText("最新推荐");
        tv_title.setText("收藏");

        Bundle extras = getIntent().getExtras();
        commitFragment(VoiceRoomFragment.getInstance(extras == null ? new Bundle() : extras));

        showContentView();
    }

    @OnClick({R.id.ll_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                activityFinish();
                break;
        }
    }

}
