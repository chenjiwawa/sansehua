//package com.zl.dappore.voiceroom.fragment.roomrole.emoji;
//
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import com.qsmaxmin.qsbase.common.log.L;
//import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
//import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
//import com.zl.dappore.R;
//import com.zl.dappore.common.model.UserConfig;
//import com.zl.dappore.common.widget.itemdecoration.DividerGridItemDecoration;
//import com.zl.dappore.voiceroom.adapter.EmojiGridRecyclerAdapterItem;
//import com.zl.dappore.voiceroom.adapter.ProductGridRecyclerAdapterItem;
//import com.zl.dappore.voiceroom.model.VoiceRoomConstants;
//import com.zl.dappore.voiceroom.model.voicerole.ProductList;
//import com.zl.dappore.voiceroom.presenter.ProductGridPresenter;
//
//
//public class EmojiGridFragment extends QsRecyclerFragment<ProductGridPresenter, ProductList.Product> {
//
//    int pageNo = 1;
//    int pageSize = 8;
//
//    public static EmojiGridFragment getInstance(Bundle bundle) {
//        EmojiGridFragment fragment = new EmojiGridFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public void initData(Bundle savedInstanceState) {
//        String token = UserConfig.getInstance().getAuthToken();
//        requestData(token, pageNo);
//
//        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
//        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));
//        showContentView();
//    }
//
//    private void initArgumentData() {
//        Bundle arguments = getArguments();
//        if (arguments == null) return;
//
//        pageNo = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_PRODUCTLIST_REQUEST_PAGE_NO, 1);
//        L.i(initTag(), " initArgumentData pageNo " + pageNo);
//    }
//
//
//    public void requestData(String token, int page) {
//        getPresenter().requestData(token, page);
//    }
//
//    @Override
//    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
//        return new EmojiGridRecyclerAdapterItem(mInflater, parent);
//    }
//
//    @Override
//    protected void setViewState(int showState) {
//        super.setViewState(showState);
//    }
//
//}