package com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.mvp.adapter.QsRecycleAdapterItem;
import com.qsmaxmin.qsbase.mvp.fragment.QsRecyclerFragment;
import com.tricolorflower.heartbeat.R;
import com.tricolorflower.heartbeat.appdetail.model.App;
import com.tricolorflower.heartbeat.common.event.RoomRoleOperationEvent;
import com.tricolorflower.heartbeat.common.http.ProductHttp;
import com.tricolorflower.heartbeat.common.listener.ItemListener;
import com.tricolorflower.heartbeat.common.listener.ItemSelectListener;
import com.tricolorflower.heartbeat.common.listener.ItemSingleSelectListener;
import com.tricolorflower.heartbeat.common.model.UserConfig;
import com.tricolorflower.heartbeat.common.widget.itemdecoration.DividerGridItemDecoration;
import com.tricolorflower.heartbeat.home.model.HomeConstants;
import com.tricolorflower.heartbeat.voiceroom.adapter.ProductGridRecyclerAdapterItem;
import com.tricolorflower.heartbeat.voiceroom.model.VoiceRoomConstants;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseProductListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.LabelList;
import com.tricolorflower.heartbeat.voiceroom.presenter.ProductGridPresenter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class ProductGridFragment extends QsRecyclerFragment<ProductGridPresenter, ProductList.Product>  implements ItemSingleSelectListener<ProductList.Product> {

    int pageNo = 1;
    int pageSize = 8;
    String token;

    ProductList.Product data;
    private ItemSelectListener itemListener;
    private List<ProductList.Product> rawProductList;

    public static ProductGridFragment getInstance(Bundle bundle) {
        ProductGridFragment fragment = new ProductGridFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initArgumentData();

        requestData(token, pageNo);

        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 4));
        getRecyclerView().addItemDecoration(new DividerGridItemDecoration(getContext(), R.drawable.divider_grid_5));
        showContentView();
    }

    private void initArgumentData() {
        Bundle arguments = getArguments();
        if (arguments == null) return;

        token = UserConfig.getInstance().getAuthToken();
        pageNo = arguments.getInt(VoiceRoomConstants.BUNDLE_KEY_PRODUCTLIST_REQUEST_PAGE_NO, 1);
        rawProductList = new ArrayList<>();
        L.i(initTag(), " initArgumentData pageNo " + pageNo);
    }

    public void requestData(String token, int page) {
        getPresenter().requestData(token, page);
    }

    @Override
    public QsRecycleAdapterItem getRecycleAdapterItem(LayoutInflater mInflater, ViewGroup parent, int type) {
        return new ProductGridRecyclerAdapterItem(mInflater, parent, this);
    }

    @Override
    protected void setViewState(int showState) {
        super.setViewState(showState);
    }

    public void setSuccessView(List<ProductList.Product> data) {
        rawProductList = new ArrayList<>();
        rawProductList.addAll(data);
    }

    public void refreshListView() {
        setData(rawProductList);
    }

    @Subscribe
    public void onEvent(RoomRoleOperationEvent.OnProductGridEvent event) {
        if (event == null)
            return;

        switch (event.state) {
            case FRESH:
                if (pageNo != event.pageNo) {
                    refreshListView();
                }
                break;
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }


    @Override
    public void onItemClick(ProductList.Product data, int preposition, int position, int totalCount) {
        if (data == null)
            return;

        if (preposition == position) {
            notifyItemChanged(position, !getData().get(position).isSelect);
        } else {
            notifyItemChanged(preposition, false);
            notifyItemChanged(position, true);
        }

        if (getCurrentItem(position).isSelect) {
            if (itemListener != null) {
                itemListener.onItemSelect(getCurrentItem(position));
            }
        } else {
            if (itemListener != null) {
                itemListener.onItemSelect(null);
            }
        }
    }

    public void notifyItemChanged(int pos, boolean isSelect) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            getData().get(pos).isSelect = isSelect;
            getRecyclerView().getAdapter().notifyItemChanged(pos);
        }
    }

    public ProductList.Product getCurrentItem(int pos) {
        if (getRecyclerView() != null && getData() != null && getData().size() > pos) {
            return getData().get(pos);
        }

        return new ProductList.Product();
    }

    public void setItemListener(ItemSelectListener itemListener) {
        this.itemListener = itemListener;
    }


}