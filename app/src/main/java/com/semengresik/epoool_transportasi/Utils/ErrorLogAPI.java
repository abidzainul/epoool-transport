package com.semengresik.epoool_transportasi.Utils;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.semengresik.epoool_transportasi.REST.ApiClient;
import java.util.Date;

/* loaded from: classes.dex */
public class ErrorLogAPI {
    public static void errorThrowing(String str, String str2) {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        if (!ApiClient.DEV) {
//            reference.child("Transportasi").child("APP").child(str2).child("error-throwing").child(String.valueOf(new Date())).child(String.valueOf(System.currentTimeMillis())).child("pesan").setValue(str);
//        } else {
//            reference.child("Transportasi").child("DEV").child(str2).child("error-throwing").child(String.valueOf(new Date())).child(String.valueOf(System.currentTimeMillis())).child("pesan").setValue(str);
//        }
    }

    public static void errorApiMessage(Object obj, String str) {
        try {
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//            if (!ApiClient.DEV) {
//                reference.child("Transportasi").child("APP").child(str).child("error-api-message").child(String.valueOf(new Date())).child(String.valueOf(System.currentTimeMillis())).child("pesan").setValue(obj);
//            } else {
//                reference.child("Transportasi").child("DEV").child(str).child("error-api-message").child(String.valueOf(new Date())).child(String.valueOf(System.currentTimeMillis())).child("pesan").setValue(obj);
//            }
        } catch (Exception unused) {
        }
    }
}
