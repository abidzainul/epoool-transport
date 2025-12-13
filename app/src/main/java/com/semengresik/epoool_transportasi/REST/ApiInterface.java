package com.semengresik.epoool_transportasi.REST;

import androidx.core.app.NotificationCompat;
import com.semengresik.epoool_transportasi.Models.AlasanModel;
import com.semengresik.epoool_transportasi.Models.DeliveryOrderModel;
import com.semengresik.epoool_transportasi.Models.InsertUpdateModel;
import com.semengresik.epoool_transportasi.Models.PengalihanModel;
import com.semengresik.epoool_transportasi.Models.ReceiverModel;
import com.semengresik.epoool_transportasi.Models.SearchModel;
import com.semengresik.epoool_transportasi.Models.UserLoginModel;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/* loaded from: classes.dex */
public interface ApiInterface {
    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_alasan")
    Observable<AlasanModel> getAlasan(@Field("token_fcm") String str, @Field("id_originator") String str2, @Field("tipe_user") String str3);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_do_main")
    Observable<DeliveryOrderModel> getDObyResi(@Field("token_fcm") String str, @Field("no_spj") String str2);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_list_pengalihan_v2")
    Observable<PengalihanModel> getPengalihan(@Field("id_username") String str, @Field("token_fcm") String str2, @Field(NotificationCompat.CATEGORY_STATUS) String str3, @Field("no_spj") String str4, @Field("tipe_sub_user") String str5);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/get_receiver")
    Observable<ReceiverModel> getReceiver(@Field("token_fcm") String str, @Field("kode_receiver") String str2);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/cek_receiver")
    Observable<SearchModel> getSearchReceiver(@Field("token_fcm") String str, @Field("id_originator") String str2, @Field("no_referensi") String str3);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/cek_spj")
    Observable<SearchModel> getSearchSPJ(@Field("token_fcm") String str, @Field("id_originator") String str2, @Field("no_spj") String str3);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/insert_pengalihan")
    Observable<InsertUpdateModel> insertPengalihan(@Field("token_fcm") String str, @Field("no_spj") String str2, @Field("receiver") String str3, @Field("alasan") String str4);

    @FormUrlEncoded
    @POST("mobile/pengalihan/Api_pengalihan/login_originator_as_transportation")
    Observable<UserLoginModel> login(@Field("username") String str, @Field("password") String str2);

    @FormUrlEncoded
    @POST("mobile/pengalihan/api_pengalihan/update_pengalihan_v2")
    Observable<InsertUpdateModel> updateStatusPengalihan(@Field("id_username") String str, @Field("token_fcm") String str2, @Field("id_pengalihan") String str3, @Field(NotificationCompat.CATEGORY_STATUS) String str4);
}
