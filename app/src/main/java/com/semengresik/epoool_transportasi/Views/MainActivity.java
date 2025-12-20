package com.semengresik.epoool_transportasi.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.media.ExifInterface;

import com.semengresik.epoool_transportasi.R;
import com.semengresik.epoool_transportasi.Utils.Constant;
import com.semengresik.epoool_transportasi.Utils.DialogHelper;
import com.semengresik.epoool_transportasi.Utils.Function;
import com.semengresik.epoool_transportasi.Utils.OkHttpHelper;
import com.semengresik.epoool_transportasi.Utils.drawer.AdapterListViewDrawer;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    public static DrawerLayout mDrawerLayout;
    private ListView lvDrawer;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    @Override 
    protected void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        CharSequence title = getTitle();
        this.mDrawerTitle = title;
        this.mTitle = title;
        this.lvDrawer = (ListView) findViewById(R.id.rv_drawer);
        isiDrawer(toolbar);
        context = this;
        Function.getFragment(this, new ListPengalihanFragment(), Constant.holderFragment);
    }

    private void isiDrawer(Toolbar toolbar) throws Resources.NotFoundException {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("0");
        arrayList.add("6");
        arrayList.add(ExifInterface.GPS_MEASUREMENT_3D);
        Constant.adapterListViewDrawer = new AdapterListViewDrawer(this, arrayList);
        this.lvDrawer.setAdapter((ListAdapter) Constant.adapterListViewDrawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) { 
            @Override 
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override 
            public void onDrawerOpened(View view) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        this.mDrawerToggle = actionBarDrawerToggle;
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        this.mDrawerToggle.setDrawerIndicatorEnabled(false);
        this.mDrawerToggle.setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(), R.drawable.menu_putih, getTheme()));
        this.mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (MainActivity.mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    MainActivity.mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    MainActivity.mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }

    public static void logOut(final SharedPreferences sharedPreferences) {
        DialogHelper.showWithDoubleButton(context, "Log Out", "Apakah Anda yakin ingin log out?", "Ya", "Tidak", new Runnable() { 
            @Override 
            public void run() {
                MainActivity.requestLogOut(sharedPreferences);
            }
        });
    }

    public static void requestLogOut(final SharedPreferences sharedPreferences) {
        OkHttpHelper okHttpHelper = new OkHttpHelper();
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(R.layout.dialog_progress_bar).create();
        alertDialogCreate.show();
        Runnable runnable = new Runnable() { 
            @Override 
            public void run() {
                alertDialogCreate.dismiss();
                sharedPreferences.edit().clear().apply();
                if (Constant.intentQueue != null) {
                    MainActivity.context.stopService(Constant.intentQueue);
                }
                if (Constant.intentService != null) {
                    MainActivity.context.stopService(Constant.intentService);
                }
                MainActivity.context.startActivity(new Intent(MainActivity.context, (Class<?>) LoginActivity.class));
                Function.openAct(MainActivity.context);
                ((Activity) MainActivity.context).finish();
            }
        };
        okHttpHelper.posting(context, Constant.url + "logout", Function.paramAdderPost(new HashMap()), runnable, false, (RelativeLayout) null, (View) null);
    }

    @Override 
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override 
    public void onBackPressed() {
        DialogHelper.showWithDoubleButton(context, "Keluar", "Apakah anda yakin ingin keluar?", "Ya", "Tidak", new Runnable() { 
            @Override 
            public void run() {
                finish();
                Function.closeAct(MainActivity.context);
            }
        });
    }

    @Override 
    protected void onDestroy() {
        super.onDestroy();
        if (Constant.intentQueue != null) {
            stopService(Constant.intentQueue);
        }
    }

    @Override 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override 
    public void setTitle(int i) {
        setTitle(getString(i));
    }

    @Override 
    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        getSupportActionBar().setTitle(this.mTitle);
    }

    @Override 
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mDrawerToggle.syncState();
    }

    @Override 
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDrawerToggle.onConfigurationChanged(configuration);
    }

    public static void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}
