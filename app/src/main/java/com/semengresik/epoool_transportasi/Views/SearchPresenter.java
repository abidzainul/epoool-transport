package com.semengresik.epoool_transportasi.Views;

import com.semengresik.epoool_transportasi.Models.SearchModel;
import com.semengresik.epoool_transportasi.REST.ApiClient;
import com.semengresik.epoool_transportasi.REST.ApiInterface;
import com.semengresik.epoool_transportasi.Utils.Constant;
import com.semengresik.epoool_transportasi.Utils.ErrorLogAPI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
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
        this.apiInterface.getSearchReceiver(Constant.token_fcm, Constant.idReference, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchModel>() { // from class: com.semengresik.epoool_transportasi.Views.SearchPresenter.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(SearchModel searchModel) {
                SearchPresenter.this.view.showSearch(searchModel.getData(), searchModel.getCode().intValue(), searchModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+cek_receiver");
                SearchPresenter.this.view.showSearch(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }

    public void loadSearchSPJ(String str) {
        this.apiInterface.getSearchSPJ(Constant.token_fcm, Constant.idReference, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchModel>() { // from class: com.semengresik.epoool_transportasi.Views.SearchPresenter.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(SearchModel searchModel) {
                SearchPresenter.this.view.showSearch(searchModel.getData(), searchModel.getCode().intValue(), searchModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+cek_spj");
                SearchPresenter.this.view.showSearch(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }
}
