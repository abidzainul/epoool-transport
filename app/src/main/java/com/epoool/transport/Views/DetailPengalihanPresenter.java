package com.epoool.transport.Views;

import com.epoool.transport.Models.InsertUpdateModel;
import com.epoool.transport.REST.ApiClient;
import com.epoool.transport.REST.ApiInterface;
import com.epoool.transport.Utils.Constant;
import com.epoool.transport.Utils.ErrorLogAPI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPengalihanPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewDetailPengalihan view;

    public interface ViewDetailPengalihan {
        void afterApproved(int i, String str);
    }

    public DetailPengalihanPresenter(ViewDetailPengalihan viewDetailPengalihan) {
        this.view = viewDetailPengalihan;
    }

    public void updateStatus(String str, String str2) {
        this.apiInterface.updateStatusPengalihan(Constant.idUsername, Constant.token_fcm, str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<InsertUpdateModel>() { 
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(InsertUpdateModel insertUpdateModel) {
                view.afterApproved(insertUpdateModel.getCode().intValue(), insertUpdateModel.getPesan());
            }

            @Override 
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+update_pengalihan");
                view.afterApproved(0, Constant.warningNoConnection);
            }
        });
    }
}
