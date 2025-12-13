package com.semengresik.epoool_transportasi.Views;

import com.semengresik.epoool_transportasi.Models.InsertUpdateModel;
import com.semengresik.epoool_transportasi.REST.ApiClient;
import com.semengresik.epoool_transportasi.REST.ApiInterface;
import com.semengresik.epoool_transportasi.Utils.Constant;
import com.semengresik.epoool_transportasi.Utils.ErrorLogAPI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/* loaded from: classes.dex */
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
        this.apiInterface.updateStatusPengalihan(Constant.idUsername, Constant.token_fcm, str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<InsertUpdateModel>() { // from class: com.semengresik.epoool_transportasi.Views.DetailPengalihanPresenter.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(InsertUpdateModel insertUpdateModel) {
                DetailPengalihanPresenter.this.view.afterApproved(insertUpdateModel.getCode().intValue(), insertUpdateModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+update_pengalihan");
                DetailPengalihanPresenter.this.view.afterApproved(0, Constant.warningNoConnection);
            }
        });
    }
}
