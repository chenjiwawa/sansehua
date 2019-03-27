package com.tricolorflower.heartbeat.blacklist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.voicerolelist.adapter.VoiceRoleRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

/**
 * Created by zhang on 2017/3/17.
 */
public class BlackRecyclerAdapterItem extends VoiceRoleRecyclerAdapterItem {

    @Bind(R.id.iv_img_voice_role)
    protected ImageView ivImgVoiceRole;
    @Bind(R.id.tv_title_voice_role)
    protected TextView tvTitleVoiceRole;
    @Bind(R.id.tv_id_voice_role)
    protected TextView tvIdVoiceRole;
    @Bind(R.id.tv_age_voice_role)
    protected TextView tvAgeVoiceRole;
    @Bind(R.id.tv_horoscope_voice_role)
    protected TextView tvHoroscopeVoiceRole;
    @Bind(R.id.tv_rank_voice_role)
    protected TextView tvRankVoiceRole;
    @Bind(R.id.tv_followers_voice_role)
    protected TextView tvFollowersVoiceRole;
    @Bind(R.id.tv_followings_voice_role)
    protected TextView tvFollowingsVoiceRole;
    @Bind(R.id.rl_item_voice_role)
    protected RelativeLayout rlItemVoiceRole;
    @Bind(R.id.ll_age_horoscope_voice_role)
    protected LinearLayout llAgeHoroscopeVoiceRole;
    @Bind(R.id.ll_base_voice_role)
    protected LinearLayout llBaseVoiceRole;
    @Bind(R.id.ll_data_voice_role)
    protected LinearLayout llDataVoiceRole;
    @Bind(R.id.btn_action_voice_role)
    protected Button btnActionVoiceRole;
    @Bind(R.id.tv_action_voice_role)
    protected TextView tvActionVoiceRole;
    @Bind(R.id.ll_action_voice_role)
    protected LinearLayout llActionVoiceRole;


    public BlackRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected void onBindItemData(VoiceRole data, int position, int totalCount) {
        super.onBindItemData(data, position, totalCount);

        QsHelper.getInstance().getImageHelper().createRequest().load(data.logo).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgVoiceRole);
        tvTitleVoiceRole.setText(data.name);

        llActionVoiceRole.setVisibility(View.VISIBLE);
        tvActionVoiceRole.setVisibility(View.GONE);
        btnActionVoiceRole.setText("移除");
    }

    @OnClick({R.id.iv_img_voice_role, R.id.tv_title_voice_role, R.id.tv_id_voice_role, R.id.tv_rank_voice_role, R.id.tv_followers_voice_role, R.id.tv_followings_voice_role, R.id.rl_item_voice_role, R.id.ll_age_horoscope_voice_role, R.id.ll_base_voice_role, R.id.ll_data_voice_role, R.id.btn_action_voice_role, R.id.tv_action_voice_role, R.id.ll_action_voice_role})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.iv_img_voice_role:
                break;
            case R.id.tv_title_voice_role:
                break;
            case R.id.tv_id_voice_role:
                break;
            case R.id.tv_rank_voice_role:
                break;
            case R.id.tv_followers_voice_role:
                break;
            case R.id.tv_followings_voice_role:
                break;
            case R.id.rl_item_voice_role:
                break;
            case R.id.ll_age_horoscope_voice_role:
                break;
            case R.id.ll_base_voice_role:
                break;
            case R.id.ll_data_voice_role:
                break;
            case R.id.btn_action_voice_role:
                break;
            case R.id.tv_action_voice_role:
                break;
            case R.id.ll_action_voice_role:
                break;
        }
    }
}