package com.semengresik.epoool_transportasi.Views;

import com.semengresik.epoool_transportasi.Models.SalesOrder;
import com.semengresik.epoool_transportasi.Models.SalesOrderRes;
import com.semengresik.epoool_transportasi.REST.ApiClient;
import com.semengresik.epoool_transportasi.REST.ApiInterface;
import com.semengresik.epoool_transportasi.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListSalesOrderPresenter {
    private ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
    private ViewListSalesOrder view;

    public interface ViewListSalesOrder {
        void showSalesOrder(List<SalesOrder> list, int i, String str);
    }

    public ListSalesOrderPresenter(ViewListSalesOrder viewListSalesOrder) {
        this.view = viewListSalesOrder;
    }

    public void loadData(String kdPlant, String dateFrom, String dateTo) {

        System.out.println("token: " + Constant.token_fcm);
        this.apiInterface.getSalesOrder(Constant.token_fcm, kdPlant, dateFrom, dateTo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SalesOrderRes>() {
            @Override 
            public void onComplete() {
            }

            @Override 
            public void onSubscribe(Disposable disposable) {
            }

            @Override 
            public void onNext(SalesOrderRes res) {
                System.out.println("onNext");
                view.showSalesOrder(res.getData(), res.getCode(), res.getPesan());
            }

            @Override 
            public void onError(Throwable th) {
                System.out.println("onError");
                view.showSalesOrder(new ArrayList<>(), 0, Constant.warningNoConnection);
            }
        });
    }
}
