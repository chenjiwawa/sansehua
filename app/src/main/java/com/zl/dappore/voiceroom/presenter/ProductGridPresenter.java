package com.zl.dappore.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.zl.dappore.common.http.AppListHttp;
import com.zl.dappore.common.http.ProductHttp;
import com.zl.dappore.common.presenter.DapporePresenter;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.voiceroom.fragment.roomrole.product.ProductGridFragment;
import com.zl.dappore.voiceroom.model.voicerole.BaseProductListRequestBody;
import com.zl.dappore.voiceroom.model.voicerole.ProductList;


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