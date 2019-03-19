package com.zl.dappore.voicerolelist.adapter;


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
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;

/**
 * Created by zhang on 2017/3/17.
 */
public class VoiceRoleRecyclerAdapterItem extends QsRecycleAdapterItem<VoiceRoleList.VoiceRole> {

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

    protected VoiceRoleList.VoiceRole data;

    public VoiceRoleRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
    }

    @Override
    protected int itemViewLayoutId() {
        return R.layout.item_voice_role;
    }

    @Override
    protected void onBindItemData(VoiceRoleList.VoiceRole data, int position, int totalCount) {
        this.data = data;

        QsHelper.getInstance().getImageHelper().createRequest().load(data.pic).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgVoiceRole);
        tvTitleVoiceRole.setText(data.nickname);

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