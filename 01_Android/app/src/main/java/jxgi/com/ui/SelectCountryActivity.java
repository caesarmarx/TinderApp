package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.adapter.SelectCountryAdapter;

public class SelectCountryActivity extends AppCompatActivity {

    @Bind(R.id.country_list)
    RecyclerView countryList;

    private SelectCountryAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    private int pos = 0;
    private String selectedCountry = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        ButterKnife.bind(this);

        countryList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(SelectCountryActivity.this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        countryList.setLayoutManager(mLinearLayoutManager);

        String[] countries = getResources().getStringArray(R.array.test_countries);
        selectedCountry = getIntent().getStringExtra("country");

        pos = Arrays.asList(countries).indexOf(selectedCountry);

        adapter = new SelectCountryAdapter(countries, pos);

        countryList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_submit)
    void onClickBtnSubmit() {
        int selectedPos = adapter.getSelectedPos();
        String selectedCountry = getResources().getStringArray(R.array.test_countries)[selectedPos];

        Intent intent = new Intent();
        intent.putExtra("country", selectedCountry);
        setResult(RESULT_OK, intent);

        finish();
    }
}