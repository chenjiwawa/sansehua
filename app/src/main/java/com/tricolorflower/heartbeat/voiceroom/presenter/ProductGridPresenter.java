package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.http.AppListHttp;
import com.tricolorflower.heartbeat.common.http.ProductHttp;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.AppList;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.ProductGridFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.BaseProductListRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;


public class ProductGridPresenter extends DapporePresenter<ProductGridFragment> {


    private int pageSize = 8;

    @ThreadPoint(ThreadType.HTTP)
    public void requestData(String token, int page) {
        ProductHttp http = createHttpRequest(ProductHttp.class);
        ProductList reponse = http.requestProductList(new BaseProductListRequestBody(token, page));
        showFailMsg(reponse);
        if (isSuccess(reponse) && reponse.data != null) {
            getView().setData(reponse.data);
        } else {
            getView().showErrorView();
        }
    }


}