package com.tricolorflower.heartbeat.voiceroom.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.tricolorflower.heartbeat.common.http.CategoryHttp;
import com.tricolorflower.heartbeat.common.http.ProductHttp;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.home.model.AppTaxonsList;
import com.tricolorflower.heartbeat.voiceroom.fragment.roomrole.product.ProductPagerFragment;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductPageList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.SendProductRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/2
 * @Description
 */
public class ProductPagerPresenter extends DapporePresenter<ProductPagerFragment> {

    @ThreadPoint(ThreadType.HTTP)
    public void requstData(BaseRequstBody requestBody) {
        ProductHttp http = createHttpRequest(ProductHttp.class);
        ProductPageList response = http.requestProductPageList(requestBody);
        showFailMsg(response);
        if (isSuccess(response) && response.data != null) {
            getView().updateViewPager(response.data);
        } else {
            getView().showErrorView();
        }
    }


}
