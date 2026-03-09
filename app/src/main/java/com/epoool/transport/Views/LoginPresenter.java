package com.epoool.transport.Views;

import com.epoool.transport.Models.UserLoginModel;
import com.epoool.transport.REST.ApiClient;
import com.epoool.transport.REST.ApiInterface;
import com.epoool.transport.Utils.Constant;
import com.epoool.transport.Utils.ErrorLogAPI;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        this.apiInterface.login(str, str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserLoginModel>() { 
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(UserLoginModel userLoginModel) {
                view.afterLogin(userLoginModel.getData(), userLoginModel.getCode().intValue(), userLoginModel.getPesan(), userLoginModel.getToken());
            }

            @Override 
            public void onError(Throwable th) {
                ErrorLogAPI.errorThrowing(th.getMessage(), "mobile+pengalihan+api_pengalihan+login_originator_as_transportation");
                view.afterLogin(new UserLoginModel(), 0, Constant.warningNoConnection, "0");
            }
        });
    }
}
