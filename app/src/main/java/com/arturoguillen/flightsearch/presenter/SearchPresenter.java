package com.arturoguillen.flightsearch.presenter;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.model.SearchModel;
import com.arturoguillen.flightsearch.utils.LogUtils;
import com.arturoguillen.flightsearch.view.search.SearchView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by arturo.guillen on 29/08/2017.
 */

public class SearchPresenter implements PresenterInterface<SearchView> {

    private static final String TAG = SearchPresenter.class.getSimpleName();
    private SearchView view;
    private Disposable searchDisposable;
    private SearchModel searchModel;

    @Inject
    public SearchPresenter(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    @Override
    public void attachView(SearchView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (searchDisposable != null)
            searchDisposable.dispose();
    }

    public void search(Search search) {
        view.showProgress();
        searchDisposable = searchModel.getFlightsInfo(search, new DisposableObserver<FlightsResult>() {
            @Override
            public void onNext(FlightsResult flightsResult) {
                view.hideProgress();
                view.hideMessage();
                view.goToResultActivity(flightsResult);
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
                view.showMessage(R.string.error);
                LogUtils.DEBUG(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
