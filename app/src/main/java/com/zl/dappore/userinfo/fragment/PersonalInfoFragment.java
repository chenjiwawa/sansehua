package com.zl.dappore.userinfo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.fragment.QsViewPagerFragment;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.zl.dappore.R;
import com.zl.dappore.common.event.UserInfoEvent;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.home.model.HomeConstants;
import com.zl.dappore.search.presenter.SearchResultPresenter;
import com.zl.dappore.userinfo.model.PersonalConstants;
import com.zl.dappore.userinfo.presenter.PersonalInfoPresenter;
import com.zl.dappore.videodetail.model.Author;
import com.zl.dappore.videodetail.model.Video;
import com.zl.dappore.videodetail.model.VideoDetailConstants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

public class PersonalInfoFragment extends QsViewPagerFragment<PersonalInfoPresenter> {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.btn_watch_personal_info)
    Button btnWatchPersonalInfo;
    @Bind(R.id.iv_logo_personal_info)
    ImageView ivLogoPersonalInfo;
    @Bind(R.id.tv_name_personal_info)
    TextView tvNamePersonalInfo;
    @Bind(R.id.tv_like_personal_info)
    TextView tvLikePersonalInfo;
    @Bind(R.id.tv_fans_personal_info)
    TextView tvFansPersonalInfo;
    @Bind(R.id.rl_fans_personal_info)
    RelativeLayout rlFansPersonalInfo;

    //    private String id = "";
    private Video video;
    private boolean isUser = false;

    @Override
    public int layoutId() {
        return R.layout.fragment_personal_info;
    }

    public static Fragment getInstance(Bundle bundle) {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        Bundle bundle = getArguments();
//        id = bundle.getString(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_ID);
        video = (Video) bundle.getSerializable(VideoDetailConstants.BUNDLE_KEY_VIDEODETAIL_REQUEST_VIDEO);
        isUser = bundle.getBoolean(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_IS_USER, false);
        if (video == null) {
            video = new Video();
        }
        if (video.author == null) {
            video.author = new Author();
        }

        L.i(initTag(), " initData video " + video);
        setHeadView(video.author);

        initViewPager(getModelPagers(video.id), 1);
        showContentView();
        getPresenter().requestPersonalInfo(video.author.id);
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setHeadView(Author author) {
        L.i(initTag(), " setHeadView author " + author);
        if (author == null)
            return;

        QsHelper.getInstance().getImageHelper().createRequest().load(author.avatarUrl).circleCrop().error(R.mipmap.icon_my_font).into(ivLogoPersonalInfo);
        tvNamePersonalInfo.setText(author.name);
//        tvLikePersonalInfo.setText("喜欢" + "0");
//        tvFansPersonalInfo.setText("粉丝" + "0");
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setLikeAndFansView(int like, int fans) {
        L.i(initTag(), " setLikeAndFansView like  " + like + " fans " + fans);
        tvLikePersonalInfo.setText("喜欢" + like);
        tvFansPersonalInfo.setText("粉丝" + fans);
    }

    @Override
    public void initViewPager(QsModelPager[] modelPagers, int offScreenPageLimit) {
        super.initViewPager(modelPagers, offScreenPageLimit);
    }

    @Override
    public QsModelPager[] getModelPagers() {
        return new QsModelPager[0];
    }

    @ThreadPoint(ThreadType.MAIN)
    public QsModelPager[] getModelPagers(String id) {
        QsModelPager videoModel = createModelPager(PersonalConstants.INDEX_MEDIA_PERSONAL);
        Bundle bundle = new Bundle();
        bundle.putString(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_ID, id);
        bundle.putBoolean(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_IS_USER, isUser);
        videoModel.fragment = PersonalVideoFragment.getInstance(bundle);

        QsModelPager topicModel = createModelPager(PersonalConstants.INDEX_TOPIC_PERSONAL);
        bundle = new Bundle();
        bundle.putString(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_ID, id);
        bundle.putBoolean(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_IS_USER, isUser);
        topicModel.fragment = PersonalTopicFragment.getInstance(bundle);

        return new QsModelPager[]{videoModel, topicModel};
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateViewPager(String id) {
        QsModelPager videoModel = createModelPager(PersonalConstants.INDEX_MEDIA_PERSONAL);
        Bundle bundle = new Bundle();
        bundle.putString(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_ID, id);
        videoModel.fragment = PersonalVideoFragment.getInstance(bundle);

        QsModelPager topicModel = createModelPager(PersonalConstants.INDEX_TOPIC_PERSONAL);
        bundle = new Bundle();
        bundle.putString(PersonalConstants.BUNDLE_KEY_PERSONAL_REQUEST_ID, id);
        topicModel.fragment = PersonalTopicFragment.getInstance(bundle);

        initViewPager(new QsModelPager[]{videoModel, topicModel}, 1);
    }

    @ThreadPoint(ThreadType.MAIN)
    private QsModelPager createModelPager(int index) {
        QsModelPager modelPager = new QsModelPager();
        modelPager.position = index;
        modelPager.title = getString(PersonalConstants.NAME_TABS_PERSONAL[index]);
        return modelPager;
    }


    @Override
    public int getTabsIndicatorColor() {
        return getResources().getColor(R.color.transparent);
    }

    @Override
    public int getTabsTitleColor() {
        return getResources().getColor(R.color.color_title_dark_gray);
    }

    @Override
    public int getTabsIndicatorMargin() {
        return CommonUtils.dp2px(39);
    }

    @Override
    protected float getTabsIndicatorWidth() {
        return CommonUtils.dp2px(39);
    }

    @Override
    protected int getTabsBackgroundResource() {
        return android.R.color.transparent;
    }

    @Override
    protected int getTabsSelectedTitleColor() {
        return getResources().getColor(R.color.color_white);
    }

    @Override
    protected float getTabsUnderlineHeight() {
        return 0;
    }

    @Override
    public void initTab(View view, QsModelPager modelPager) {
        TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
        if (TextUtils.isEmpty(modelPager.title)) {
            tv_tab.setVisibility(View.GONE);
        } else {
            tv_tab.setText(modelPager.title);
        }

        if (modelPager.position == HomeConstants.INDEX_HOME) {
            setTabsIcon(view, true);
        } else {
            setTabsIcon(view, false);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setTabsIcon(final View view, boolean isSelected) {
        if (view != null) {
            final TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);

            if (tv_tab != null) {
                if (isSelected) {
                    tv_tab.setTextSize(14);
                    tv_tab.setTextColor(QsHelper.getInstance().getColor(R.color.color_white));
                    TextPaint tp = tv_tab.getPaint();
                    tp.setFakeBoldText(true);
                } else {
                    tv_tab.setTextSize(13);
                    tv_tab.setTextColor(QsHelper.getInstance().getColor(R.color.color_title_dark_gray));
                    TextPaint tp = tv_tab.getPaint();
                    tp.setFakeBoldText(false);
                }
            }
        }
    }

    /**
     * viewPager滑动事件
     */
    @Override
    public void onPageSelected(View current, View old, int currentPosition, int oldPosition) {
        super.onPageSelected(current, old, currentPosition, oldPosition);
        if (old != null) {
            setTabsIcon(old, false);
        }
        if (current != null) {
            setTabsIcon(current, true);
        }
    }

    @Override
    public int getTabItemLayout() {
        return R.layout.item_personal_tab;
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserInfoEvent.OnPersonalInfoEvent event) {
        L.i(initTag(), " onEvent UserInfoEvent.OnPersonalInfoEvent " + event);
        if (event == null)
            return;

        if (event.state == UserInfoEvent.OnPersonalInfoEvent.STATE_REFRESH) {
            setHeadView(event.author);
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_watch_personal_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.btn_watch_personal_info:
                break;
        }
    }
}

