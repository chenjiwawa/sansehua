package com.tricolorflower.heartbeat.search.presenter;

import android.text.TextUtils;

import com.tricolorflower.heartbeat.common.presenter.DapporePresenter;
import com.tricolorflower.heartbeat.search.SearcherActivity;
import com.tricolorflower.heartbeat.search.model.ModelSearchHistory;
import com.tricolorflower.heartbeat.search.model.SearchConstants;
import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;


/**
 * @CreateBy qsmaxmin
 * @Date 16/8/3
 * @Description
 */
public class SearcherPresenter extends DapporePresenter<SearcherActivity> {

    @ThreadPoint(ThreadType.WORK)
    public void saveToSearchHistory(String str) {
        long start = System.currentTimeMillis();
        if (TextUtils.isEmpty(str) || str.length() > 15) {
            return;
        }
        ModelSearchHistory model = getSearchHistoryFromFile();
        if (model != null && model.searchHistory != null) {
            if (model.searchHistory.contains(str)) {
                model.searchHistory.remove(str);
            }
            model.searchHistory.add(0, str);
        } else {
            model = new ModelSearchHistory();
            model.searchHistory.add(0, str);
        }
        QsHelper.getInstance().getCacheHelper().saveObject2File(model, SearchConstants.CACHE_SEARCH_HISTORY);
        L.i(initTag(),"saveToSearchHistory use time:" + (System.currentTimeMillis() - start));
    }


    @ThreadPoint(ThreadType.WORK)
   public void requestSearchHistory() {
        ModelSearchHistory model = getSearchHistoryFromFile();

        L.i(initTag(),"requestSearchHistory model "+model.toString());
        if (model != null) {
            getView().updateEditText(model.searchHistory);
        }else{
            getView().updateEditText(null);
        }
    }

    @ThreadPoint(ThreadType.WORK)
     public void cleanSearchHistory() {
        QsHelper.getInstance().getCacheHelper().saveObject2File(new ModelSearchHistory(), SearchConstants.CACHE_SEARCH_HISTORY);
        getView().updateEditText(null);
    }

    private ModelSearchHistory getSearchHistoryFromFile() {
        return QsHelper.getInstance().getCacheHelper().getObjectFromFile(SearchConstants.CACHE_SEARCH_HISTORY, ModelSearchHistory.class);
    }
}
