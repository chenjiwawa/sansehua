package com.zl.dappore.voicerolelist.adapter;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.zl.dappore.R;
import com.zl.dappore.appdetail.AppDetailActivity;
import com.zl.dappore.appdetail.model.AppDetailConstants;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.web.WebViewActivity;
import com.zl.dappore.web.model.WebConstants;



/**
 * Created by zhang on 2017/3/17.
 */
public class VoiceRoleRecyclerAdapterItem extends QsRecycleAdapterItem<VoiceRoleList.VoiceRole> {

    @Bind(R.id.iv_img_voice_role)
    ImageView ivImgVoiceRole;
    @Bind(R.id.tv_title_voice_role)
    TextView tvTitleVoiceRole;
    @Bind(R.id.tv_id_voice_role)
    TextView tvIdVoiceRole;
    @Bind(R.id.tv_age_voice_role)
    TextView tvAgeVoiceRole;
    @Bind(R.id.tv_horoscope_voice_role)
    TextView tvHoroscopeVoiceRole;
    @Bind(R.id.tv_rank_voice_role)
    TextView tvRankVoiceRole;
    @Bind(R.id.tv_followers_voice_role)
    TextView tvFollowersVoiceRole;
    @Bind(R.id.tv_followings_voice_role)
    TextView tvFollowingsVoiceRole;
    @Bind(R.id.rl_item_voice_role)
    RelativeLayout rlItemVoiceRole;

    protected VoiceRoleList.VoiceRole data;
    protected boolean isItemPos = false;

    public VoiceRoleRecyclerAdapterItem(LayoutInflater inflater, ViewGroup parent, boolean isItemPos) {
        super(inflater, parent);
        this.isItemPos = isItemPos;
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

    @butterknife.OnClick({R.id.iv_img_voice_role, R.id.tv_title_voice_role, R.id.tv_id_voice_role, R.id.tv_rank_voice_role, R.id.tv_followers_voice_role, R.id.tv_followings_voice_role, R.id.rl_item_voice_role})
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
        }
    }
}