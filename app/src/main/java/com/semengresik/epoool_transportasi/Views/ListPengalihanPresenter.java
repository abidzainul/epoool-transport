package com.semengresik.epoool_transportasi.Views;

import com.semengresik.epoool_transportasi.Models.PengalihanModel;
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
public class ListPengalihanPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewListPengalihan view;

    public interface ViewListPengalihan {
        void showPengalihan(List<PengalihanModel> list, int i, String str);
    }

    public ListPengalihanPresenter(ViewListPengalihan viewListPengalihan) {
        this.view = viewListPengalihan;
    }

    public void loadPengalihan(String str, String str2) {
        this.apiInterface.getPengalihan(Constant.idUsername, Constant.token_fcm, str, str2, Constant.tipe_sub_user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PengalihanModel>() { // from class: com.semengresik.epoool_transportasi.Views.ListPengalihanPresenter.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(PengalihanModel pengalihanModel) {
                System.out.println("masuk dlam");
                ListPengalihanPresenter.this.view.showPengalihan(pengalihanModel.getData(), pengalihanModel.getCode().intValue(), pengalihanModel.getPesan());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+get_list_pengalihan");
                ListPengalihanPresenter.this.view.showPengalihan(new ArrayList(), 0, Constant.warningNoConnection);
            }
        });
    }
}
