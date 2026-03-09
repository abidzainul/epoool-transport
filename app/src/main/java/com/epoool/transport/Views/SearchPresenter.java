package com.epoool.transport.Views;

import com.epoool.transport.Models.SearchModel;
import com.epoool.transport.REST.ApiClient;
import com.epoool.transport.REST.ApiInterface;
import com.epoool.transport.Utils.Constant;
import com.epoool.transport.Utils.ErrorLogAPI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class SearchPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewListSearch view;

    public interface ViewListSearch {
        void showSearch(List<SearchModel> list, int i, String str);
    }

    public SearchPresenter(ViewListSearch viewListSearch) {
        this.view = viewListSearch;
    }

    public void loadSearchReceiver(String str) {
        this.apiInterface.getSearchReceiver(Constant.token_fcm, Constant.idReference, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchModel>() { 
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(SearchModel searchModel) {
                view.showSearch(searchModel.getData(), searchModel.getCode().intValue(), searchModel.getPesan());
            }

            @Override 
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+cek_receiver");
                view.showSearch(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }

    public void loadSearchSPJ(String str) {
        this.apiInterface.getSearchSPJ(Constant.token_fcm, Constant.idReference, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchModel>() { 
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(SearchModel searchModel) {
                view.showSearch(searchModel.getData(), searchModel.getCode().intValue(), searchModel.getPesan());
            }

            @Override 
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+cek_spj");
                view.showSearch(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }
}
