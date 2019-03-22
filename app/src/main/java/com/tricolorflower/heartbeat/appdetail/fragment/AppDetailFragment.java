package com.tricolorflower.heartbeat.appdetail.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.common.viewbind.annotation.OnClick;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.common.widget.viewpager.QsViewPager;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsPullRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.AppDetailActivity;
import com.tricolorflower.heartbeat.appdetail.AppdetailI;
import com.tricolorflower.heartbeat.appdetail.adapter.AppImagePageAdapter;
import com.tricolorflower.heartbeat.appdetail.adapter.CommentRecyclerAdapterItem;
import com.tricolorflower.heartbeat.appdetail.adapter.ContractRecyclerAdapter;
import com.tricolorflower.heartbeat.appdetail.adapter.SimilarAppAdapter;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.appdetail.model.AppDetailConstants;
import com.tricolorflower.heartbeat.appdetail.presenter.AppDetailPresenter;
import com.tricolorflower.heartbeat.comment.CommentListActivity;
import com.tricolorflower.heartbeat.comment.model.Comment;
import com.tricolorflower.heartbeat.comment.model.CommentConstants;
import com.tricolorflower.heartbeat.comment.model.CommentRequstBody;
import com.tricolorflower.heartbeat.common.event.AppDetailEvent;
import com.tricolorflower.heartbeat.common.event.LoginEvent;
import com.tricolorflower.heartbeat.common.utils.CommonUtils;
import com.tricolorflower.heartbeat.common.utils.share.ShareContent;
import com.tricolorflower.heartbeat.common.widget.BeautyRatingBar;
import com.tricolorflower.heartbeat.common.widget.shadow.ShadowDrawable;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.recommendlist.RecommendListActivity;
import com.tricolorflower.heartbeat.recommendlist.model.RecommendListConstants;
import com.tricolorflower.heartbeat.web.WebViewActivity;
import com.tricolorflower.heartbeat.web.model.WebConstants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.FOCUS_BEFORE_DESCENDANTS;

public class AppDetailFragment extends QsPullRecyclerFragment<AppDetailPresenter, Comment> implements  AppCommentI {

    @Bind(R.id.tv_rank_appdetail)
    TextView tvRankAppdetail;
    @Bind(R.id.tv_dau_appdetail)
    TextView tvDauAppdetail;
    @Bind(R.id.tv_chain_appdetail)
    TextView tvChainAppdetail;

    @Bind(R.id.ll_basedata_appdetail)
    LinearLayout llBasedataAppdetail;
    @Bind(R.id.tv_dev_appdetail)
    TextView tvDevAppdetail;
    @Bind(R.id.tv_category_app_detail)
    TextView tvCategoryAppDetail;
    @Bind(R.id.tv_language_app_detail)
    TextView tvLanguageAppDetail;
    @Bind(R.id.tv_despart_app_detail)
    TextView tvDespartAppDetail;
    @Bind(R.id.tv_des_app_detail)
    TextView tvDesAppDetail;
    @Bind(R.id.ll_des_appdetail)
    LinearLayout llDesAppdetail;
    @Bind(R.id.vp_imgs_appdetail)
    QsViewPager vpImgsAppdetail;
    @Bind(R.id.ll_imgs_appdetail)
    LinearLayout llImgsAppdetail;
    @Bind(R.id.ll_data_appdetail)
    LinearLayout llDataAppdetail;
    @Bind(R.id.tv_comments_appdetail)
    TextView tvCommentsAppdetail;
    @Bind(R.id.rl_comments_appdetail)
    RelativeLayout rlCommentsAppdetail;

    @Bind(R.id.iv_img_appdetail)
    ImageView ivImgAppdetail;
    @Bind(R.id.iv_comments_appdetail)
    ImageView iv_comments_appdetail;
    @Bind(R.id.tv_title_appdetail)
    TextView tvTitleAppdetail;
    @Bind(R.id.rb_score_appdetail)
    BeautyRatingBar rbScoreAppdetail;
    @Bind(R.id.tv_score_appdetail)
    TextView tvScoreAppdetail;
    @Bind(R.id.iv_download_appdetail)
    Button ivDownloadAppdetail;
    @Bind(R.id.tv_award_appdetail)
    TextView tvAwardAppdetail;
    @Bind(R.id.rl_item_appdetail)
    RelativeLayout rlItemAppdetail;

    @Bind(R.id.rv_similar_app)
    RecyclerView rvSimilarApp;
    @Bind(R.id.prl_similar)
    LinearLayout prlSimilar;
    @Bind(R.id.tv_similar)
    TextView tv_similar;
    @Bind(R.id.tv_contract_appdetail)
    TextView tvContractAppdetail;
    @Bind(R.id.rv_contract_appdetail)
    RecyclerView rvContractAppdetail;
    @Bind(R.id.iv_contract_appdetail)
    ImageView ivContractAppdetail;
    @Bind(R.id.ll_contract_appdetail)
    LinearLayout llContractAppdetail;
    @Bind(R.id.rl_input_appdetail)
    RelativeLayout rlInputAppdetail;
    @Bind(R.id.tv_show_des_app_detail)
    TextView tvShowDesAppDetail;
    @Bind(R.id.ll_show_des_app_detail)
    LinearLayout llShowDesAppDetail;

    App data;
    String id;
    private int lastPosition = 0;
    private int lastOffset = 0;

    public static AppDetailFragment getInstance(Bundle extras) {
        AppDetailFragment appDetailFragment = new AppDetailFragment();
        appDetailFragment.setArguments(extras);
        return appDetailFragment;
    }

    AppdetailI appDetailI;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            appDetailI = (AppdetailI) context;
        }
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_appdetail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments == null) return;
        id = arguments.getString(AppDetailConstants.BUNDLE_KEY_APPDETAIL_REQUEST_ID);
        L.i(initTag(), " id " + id);
//        id = "3245";
//        id = "4025";

        showLoadingView();
        getPresenter().requestAppDetail(id);
        getPresenter().requestAppListBySimilar();

        getRecyclerView().setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS);
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.getLayoutManager() != null) {
                    getPositionAndOffset();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private void getPositionAndOffset() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getRecyclerView().getLayoutManager();
        View topView = layoutManager.getChildAt(0);
        if (topView != null) {
            lastOffset = topView.getTop();
            lastPosition = layoutManager.getPosition(topView);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void scrollToPosition() {
        L.i(initTag(), " scrollToPosition " + " lastPosition " + lastPosition + " lastOffset " + lastOffset);

        if (getRecyclerView() != null && getRecyclerView().getLayoutManager() != null && lastPosition >= 0) {
            ((LinearLayoutManager) getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(lastPosition, lastOffset);
        }
    }

    @Override
    public int getHeaderLayout() {
        return R.layout.header_appdetail;
    }

    @Override
    public int getFooterLayout() {
        return R.layout.footer_appdetail;
    }

    @Override
    public boolean isOpenViewState() {
        return false;
    }

    @ThreadPoint(ThreadType.MAIN)
    @Override
    public void onRefresh() {
        getPresenter().requestAppDetail(id);
        getPresenter().requestAppListBySimilar();
    }

    @ThreadPoint(ThreadType.MAIN)
    @Override
    public void onLoad() {
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setHeader(App data) {
        showContentView();
        if (data == null)
            return;
        this.data = data;

        L.i(initTag(), " setHeader data " + data.toString());

        setCollectionView(data.starred);

        QsHelper.getInstance().getImageHelper().createRequest().load(data.logoUrl).roundedCorners(CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9), CommonUtils.dp2px(9)).into(ivImgAppdetail);
        tvTitleAppdetail.setText(data.name);
        if(!TextUtils.isEmpty(data.starCount)){
            rbScoreAppdetail.setRating(Float.parseFloat(data.starCount));
        }
        tvScoreAppdetail.setText(data.score + "分");
        ShadowDrawable.setShadowDrawable(rlItemAppdetail, Color.parseColor("#ffffff"), 0,
                R.color.color_shadow_gray,
                CommonUtils.dp2px(5), new int[]{CommonUtils.dp2px(5), CommonUtils.dp2px(0), CommonUtils.dp2px(5), CommonUtils.dp2px(5)}, 0, 0);

        tvRankAppdetail.setText("");
        tvDauAppdetail.setText("");
        tvChainAppdetail.setText(data.chain);

        tvDevAppdetail.setText("开发者：");
        if (data.appTaxon != null) {
            tvCategoryAppDetail.setText("类型：" + data.appTaxon.name);
        }

        tvLanguageAppDetail.setText("");

        initContractViews(data);

        setDesPart();

        AppImagePageAdapter pageAdapter = new AppImagePageAdapter(data.imageUrls);
        vpImgsAppdetail.setAdapter(pageAdapter);
        vpImgsAppdetail.setPageMargin(getResources().getDimensionPixelSize(R.dimen.imgs_appdetail_margin));

        getRecyclerView().setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                AgoraLog.i(initTag(), " onScrollStateChanged " + " recyclerView " + recyclerView + " newState " + newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                AgoraLog.i(initTag(), " onScrolled " + " dx " + dx + " dy " + dy);

                Activity activity = getActivity();
                if (activity instanceof AppDetailActivity) {
                    int[] position = new int[2];
                    ivImgAppdetail.getLocationInWindow(position);
                    if (appDetailI != null && data != null && ((AppDetailActivity) activity).getTitleBarHeight() > (position[1] * CommonUtils.getDensity())) {
                        appDetailI.onLogoShow(data.logoUrl, true);
                    } else {
                        appDetailI.onLogoShow(data.logoUrl, false);
                    }
                }
            }
        });

        if (data.commentable) {
            rlInputAppdetail.setVisibility(View.VISIBLE);
        } else {
            rlInputAppdetail.setVisibility(View.GONE);
        }

        if (data.comments != null && data.comments.size() > 0) {
            iv_comments_appdetail.setVisibility(View.VISIBLE);
        } else {
            iv_comments_appdetail.setVisibility(View.GONE);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    private void initContractViews(App data) {
        if (data == null)
            return;

        if (data != null && data.contracts.size() > 1) {
            ivContractAppdetail.setVisibility(View.VISIBLE);
        } else {
            ivContractAppdetail.setVisibility(View.INVISIBLE);
        }

        L.i(initTag(), " ivContractAppdetail initContractViews ");
        List<App.Contract> contracts = new ArrayList<>();
        if (data.contracts != null && data.contracts.size() > 0) {
            contracts.add(data.contracts.get(0));
        }
        setContractViews(contracts);
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setContractViews(List<App.Contract> contracts) {
        if (contracts == null)
            return;

        if (data != null && data.contracts.size() > 1) {
            ivContractAppdetail.setVisibility(View.VISIBLE);
        } else {
            ivContractAppdetail.setVisibility(View.INVISIBLE);
        }

        L.i(initTag(), " ivContractAppdetail setContractViews ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContractAppdetail.setLayoutManager(layoutManager);
        rvContractAppdetail.setAdapter(new ContractRecyclerAdapter(contracts, getContext(), data));

    }

    @ThreadPoint(ThreadType.MAIN)
    private void showDes(Context context, String data, boolean isExpand, TextView shortView, String endText, int maxLines, int endColorID, TextEllpipI ellpipI) {
        if (TextUtils.isEmpty(data) || context == null) {
            return;
        }
        shortView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                shortView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (isExpand) {
                    shortView.setText(data);
                } else {
                    int paddingLeft = shortView.getPaddingLeft();
                    int paddingRight = shortView.getPaddingRight();
                    TextPaint paint = shortView.getPaint();
                    float moreTextLen = paint.measureText(endText) + shortView.getTextSize();
                    final float dataLen = paint.measureText(data);
                    float availableTextWidth = (shortView.getWidth() - paddingLeft - paddingRight) * maxLines;
                    if (dataLen <= availableTextWidth) {
                        return;
                    }
                    CharSequence ellipsizeStr = TextUtils.ellipsize(data, paint,
                            availableTextWidth - moreTextLen, TextUtils.TruncateAt.END);
                    if (ellipsizeStr.length() < data.length()) {
                        class MyClick extends ClickableSpan {
                            @Override
                            public void onClick(View widget) {
                                if (ellpipI != null) {
                                    ellpipI.onShow();
                                }
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                ds.setColor(context.getResources().getColor(endColorID));
                            }
                        }
                        CharSequence temp = ellipsizeStr + endText;
                        SpannableStringBuilder ssb = new SpannableStringBuilder(temp);
                        shortView.setMovementMethod(LinkMovementMethod.getInstance());
                        ssb.setSpan(new MyClick(), temp.length() - endText.length(), temp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        shortView.setText(ssb);
                    } else {
                        shortView.setText(data);
                    }
                }
            }
        });
    }


    interface TextEllpipI {
        void onShow();
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new CommentRecyclerAdapterItem(layoutInflater, viewGroup, this);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setSimilarList(List<App> apps) {
        if (apps == null)
            return;

        prlSimilar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSimilarApp.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_list_10));
        rvSimilarApp.addItemDecoration(dividerItemDecoration);
        rvSimilarApp.setAdapter(new SimilarAppAdapter(apps));
    }

    @ThreadPoint(ThreadType.MAIN)
    public void hideSimilarList() {
        prlSimilar.setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_download_appdetail, R.id.iv_contract_appdetail, R.id.rl_comments_appdetail, R.id.rl_input_appdetail, R.id.tv_similar, R.id.tv_show_des_app_detail})
    public void onViewClick(View view) {
        super.onViewClick(view);
        switch (view.getId()) {
            case R.id.iv_contract_appdetail:
                if (data != null && data.contracts.size() > 1) {
                    ivContractAppdetail.setVisibility(View.VISIBLE);
                    if (rvContractAppdetail.getChildCount() == data.contracts.size()) {
                        ivContractAppdetail.setImageResource(R.mipmap.ic_detail_up);
                        initContractViews(data);
                    } else {
                        ivContractAppdetail.setImageResource(R.mipmap.ic_detail_down);
                        setContractViews(data.contracts);
                    }
                } else {
                    ivContractAppdetail.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_comments_appdetail:
                if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_COMMENT)) {
                    if (data == null)
                        return;

                    if (data.comments != null && data.comments.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString(CommentConstants.BUNDLE_KEY_COMMENT_REQUEST_ID, id);
                        bundle.putBoolean(CommentConstants.BUNDLE_KEY_COMMENT_REQUEST_COMMENTABLE, data.commentable);
                        QsHelper.getInstance().intent2Activity(CommentListActivity.class, bundle);
                    }
                }
                break;
            case R.id.rl_input_appdetail:
                if (appDetailI != null) {
                    appDetailI.showComment();
                }
                break;
            case R.id.tv_similar:
                Bundle bundle = new Bundle();
                bundle.putString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_NAME, "同类推荐");
                bundle.putInt(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_TYPE, RecommendListConstants.TYPE_SIMILAR);
                if (data != null && data.appTaxon != null) {
                    bundle.putString(RecommendListConstants.BUNDLE_KEY_RECOMMENDLIST_REQUEST_APP_TAXON_ID, data.appTaxon.id);
                }
                QsHelper.getInstance().intent2Activity(RecommendListActivity.class, bundle);
                break;
            case R.id.tv_show_des_app_detail:
                setDesPart();
                break;
            case R.id.iv_download_appdetail:
                if (data != null) {
                    bundle = new Bundle();
                    bundle.putString(WebConstants.BUNDLE_TITLE_KEY, data.name);
                    bundle.putString(WebConstants.BUNDLE_URL_KEY, data.website);
                    QsHelper.getInstance().intent2Activity(WebViewActivity.class, bundle);
                }
                break;
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setDesPart() {
        if (data == null)
            return;

        tvDespartAppDetail.setVisibility(View.VISIBLE);
        llShowDesAppDetail.setVisibility(View.GONE);
        tvDesAppDetail.setText(data.description);
        showDes(getContext(), data.description, false, tvDespartAppDetail, "展开", 3, R.color.color_green_title, new TextEllpipI() {
            @Override
            public void onShow() {
                tvDespartAppDetail.setVisibility(View.GONE);
                llShowDesAppDetail.setVisibility(View.VISIBLE);
            }
        });
    }

    @ThreadPoint(ThreadType.MAIN)
    private void setDesAll() {
        if (data == null)
            return;

//        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(data.description);
//        spannableStringBuilder.append("收起");
//        spannableStringBuilder.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                AgoraLog.i(initTag(), " tvDesAppDetail ClickableSpan " + widget);
//                tvDespartAppDetail.setVisibility(View.VISIBLE);
//                tvDesAppDetail.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                ds.setColor(getResources().getColor(R.color.color_green_title));
//            }
//        }, spannableStringBuilder.length() - 2, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvDesAppDetail.setText(spannableStringBuilder);
//        tvDesAppDetail.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Subscribe
    public void onEvent(AppDetailEvent event) {
        L.i(initTag(), " onEvent AppDetailEvent " + event);
        if (event == null)
            return;

        if (event.state == AppDetailEvent.AppDetailState.STATE_REFRESH) {
            onRefresh();
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        hideComment();
//        return super.dispatchTouchEvent(ev);
//    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginEvent.onLogin event) {
        L.i(initTag(), " onEvent LoginEvent.onLogin " + event);
        if (event == null)
            return;

        if (event.mLoginState == LoginEvent.LoginState.STATE_SUCCESS || event.mLoginState == LoginEvent.LoginState.STATE_LOGOUT) {
            onRefresh();
        }
    }

//    @Subscribe
//    public void onEvent(LoginEvent.onLogin event) {
//        AgoraLog.i(initTag()," onEvent LoginEvent.onLogin "+event);
//        if (event == null)
//            return;
//
//        if (event.mLoginState == LoginEvent.LoginState.STATE_SUCCESS || event.mLoginState == LoginEvent.LoginState.STATE_LOGOUT) {
//            onRefresh();
//        }
//    }



    @Override
    public void onPraise(Comment data) {
        if (data == null || data.commenter == null)
            return;
        L.i(initTag(), " onPraise " + data);

        if (getPresenter().checkLogin(HomeConstants.REQUESTCODE_LOGIN_FAVORITE)) {
            if (!enablePraise) {
                QsToast.show("请稍候...");
                return;
            }

            L.i(initTag(), " requestPraise " + data);

            enablePraise = false;
            getPresenter().requestPraise(data.id);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void requestCollections() {
        if (data == null)
            return;

        if (data.starred) {
            getPresenter().requestCancelCollections(id);
        } else {
            getPresenter().requestComfirmCollections(id);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void enableCollection(boolean enable) {
        Activity activity = getActivity();
        if (activity instanceof AppDetailActivity) {
            ((AppDetailActivity) activity).enableCollection(enable);
        }
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCollectionView(boolean isCollection) {
        Activity activity = getActivity();
        if (activity instanceof AppDetailActivity) {
            ((AppDetailActivity) activity).setCollectionView(isCollection);
        }
    }

    private boolean enablePraise = true;

    public void enablePraise(boolean enable) {
        enablePraise = enable;
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }

    @ThreadPoint(ThreadType.MAIN)
    public void requestCommentList(String content, String star_count) {
        L.i(initTag(), " requestCommentList " + content + " " + star_count);
        CommentRequstBody body = new CommentRequstBody(content, star_count);
        L.i(initTag(), " requestCommentList " + body + " ");
        getPresenter().requestCommentList(id, body);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void setCommentSuccess() {
        if (appDetailI != null) {
            appDetailI.hideComment();
        }
    }

    public void setShareContent() {
        if (data == null) {
            QsToast.show("请刷新页面！");
            return;
        }
        ShareContent.title = data.name;
        ShareContent.text = data.description;
        ShareContent.imageurl = data.logoUrl;
//        ShareContent.url = "";
    }
}

