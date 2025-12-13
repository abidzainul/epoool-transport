package com.semengresik.epoool_transportasi.Views;

import com.semengresik.epoool_transportasi.Models.UserLoginModel;
import com.semengresik.epoool_transportasi.REST.ApiClient;
import com.semengresik.epoool_transportasi.REST.ApiInterface;
import com.semengresik.epoool_transportasi.Utils.Constant;
import com.semengresik.epoool_transportasi.Utils.ErrorLogAPI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/* loaded from: classes.dex */
public class LoginPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewLogin view;

    public interface ViewLogin {
        void afterLogin(UserLoginModel userLoginModel, int i, String str, String str2);
    }

    public LoginPresenter(ViewLogin viewLogin) {
        this.view = viewLogin;
    }

    public void doLogin(String str, String str2) {
        this.apiInterface.login(str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserLoginModel>() { // from class: com.semengresik.epoool_transportasi.Views.LoginPresenter.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(UserLoginModel userLoginModel) {
                LoginPresenter.this.view.afterLogin(userLoginModel.getData(), userLoginModel.getCode().intValue(), userLoginModel.getPesan(), userLoginModel.getToken());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+login_originator_as_transportation");
                LoginPresenter.this.view.afterLogin(new UserLoginModel(), 0, Constant.warningNoConnection, "0");
            }
        });
    }
}
