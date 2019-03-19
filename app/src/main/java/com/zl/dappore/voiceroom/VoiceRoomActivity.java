package com.zl.dappore.voiceroom;

import android.os.Bundle;

import com.qsmaxmin.qsbase.mvp.QsActivity;
import com.zl.dappore.R;
import com.zl.dappore.voiceroom.fragment.VoiceRoomFragment;

/*
我的房间 是否需要创建（user信息）
*/

/*
别人的房间
*/

public class VoiceRoomActivity extends QsActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_voice_room;
    }


    @Override
    public void initData(Bundle bundle) {

        Bundle extras = getIntent().getExtras();
        commitFragment(VoiceRoomFragment.getInstance(extras == null ? new Bundle() : extras));

        showContentView();
    }

}
