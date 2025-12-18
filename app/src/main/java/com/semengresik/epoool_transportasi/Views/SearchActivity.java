package com.semengresik.epoool_transportasi.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.semengresik.epoool_transportasi.Adapters.AdapterSearchList;
import com.semengresik.epoool_transportasi.Models.SearchModel;
import com.semengresik.epoool_transportasi.R;

import java.util.List;

/* loaded from: classes.dex */
public class SearchActivity extends AppCompatActivity implements SearchPresenter.ViewListSearch {
    Context context;
    EditText etSearch;
    SearchPresenter presenter;
    RecyclerView rcList;
    TextView tvKosong;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search);
        this.etSearch = (EditText) findViewById(R.id.et_search);
        this.rcList = (RecyclerView) findViewById(R.id.rc_list);
        this.tvKosong = (TextView) findViewById(R.id.tv_kosong);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        if (getIntent().getIntExtra("tipe", 0) == 1) {
            getSupportActionBar().setTitle("No. Receiver");
        } else if (getIntent().getIntExtra("tipe", 0) == 2) {
            getSupportActionBar().setTitle("No. SPJ");
        }
        this.context = this;
        this.etSearch.requestFocus();
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.etSearch, 1);
        this.presenter = new SearchPresenter(this);
        if (getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH) != null && !getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH).equals("")) {
            this.etSearch.setText(getIntent().getStringExtra(FirebaseAnalytics.Event.SEARCH));
            if (getIntent().getIntExtra("tipe", 0) == 1) {
                this.presenter.loadSearchReceiver(this.etSearch.getText().toString());
            } else if (getIntent().getIntExtra("tipe", 0) == 2) {
                this.presenter.loadSearchSPJ(this.etSearch.getText().toString());
            }
        }
        this.etSearch.addTextChangedListener(new TextWatcher() { // from class: com.semengresik.epoool_transportasi.Views.SearchActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() >= 3) {
                    if (SearchActivity.this.getIntent().getIntExtra("tipe", 0) == 1) {
                        SearchActivity.this.presenter.loadSearchReceiver(editable.toString());
                        return;
                    } else {
                        if (SearchActivity.this.getIntent().getIntExtra("tipe", 0) == 2) {
                            SearchActivity.this.presenter.loadSearchSPJ(editable.toString());
                            return;
                        }
                        return;
                    }
                }
                if (editable.toString().length() == 0) {
                    SearchActivity.this.tvKosong.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override // com.semengresik.epoool_transportasi.Views.SearchPresenter.ViewListSearch
    public void showSearch(List<SearchModel> list, int i, String str) {
        if (i == 1) {
            this.tvKosong.setVisibility(View.GONE);
            AdapterSearchList adapterSearchList = new AdapterSearchList(list, this.context, getIntent().getIntExtra("tipe", 0));
            this.rcList.setHasFixedSize(true);
            this.rcList.setNestedScrollingEnabled(false);
            this.rcList.setLayoutManager(new LinearLayoutManager(this.context, 1, false));
            this.rcList.setAdapter(adapterSearchList);
            adapterSearchList.setOnItemClickListener(new AdapterSearchList.OnListClickListener() { // from class: com.semengresik.epoool_transportasi.Views.SearchActivity.2
                @Override // com.semengresik.epoool_transportasi.Adapters.AdapterSearchList.OnListClickListener
                public void onClicked(String str2, int i2) {
                    Intent intent = new Intent();
                    intent.putExtra(FirebaseAnalytics.Event.SEARCH, str2);
                    SearchActivity.this.setResult(-1, intent);
                    SearchActivity.this.finish();
                }
            });
            return;
        }
        this.tvKosong.setVisibility(View.VISIBLE);
    }
}
