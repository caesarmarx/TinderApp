package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.adapter.ChooseCountriesAdapter;
import jxgi.com.model.CountryItem;

public class ChooseCountriesActivity extends AppCompatActivity {

    @Bind(R.id.country_list)
    RecyclerView countryList;
    @Bind(R.id.tv_total_cost)
    TextView tvTotalCost;

    private ChooseCountriesAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_countries);
        ButterKnife.bind(this);

        countryList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(ChooseCountriesActivity.this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        countryList.setLayoutManager(mLinearLayoutManager);

        adapter = new ChooseCountriesAdapter(ChooseCountriesActivity.this);
        countryList.setAdapter(adapter);
        adapter.addItem(new CountryItem("Argentina", "", 0, false));
        adapter.addItem(new CountryItem("Brazil", "", 0, false));
        adapter.addItem(new CountryItem("Canada", "", 0, false));
        adapter.addItem(new CountryItem("Denmark", "", 0, false));
        adapter.addItem(new CountryItem("England", "", 0, false));
        adapter.addItem(new CountryItem("France", "", 0, false));
        adapter.addItem(new CountryItem("Germany", "", 0, false));
        adapter.addItem(new CountryItem("Holland", "", 0, false));
        adapter.addItem(new CountryItem("Italy", "", 0, false));
        adapter.addItem(new CountryItem("Japan", "", 0, false));
        adapter.addItem(new CountryItem("Kuwait", "", 0, false));
        adapter.addItem(new CountryItem("Luxemburg", "", 0, false));
        adapter.addItem(new CountryItem("Mexico", "", 0, false));
        adapter.addItem(new CountryItem("Norway", "", 0, false));
        adapter.addItem(new CountryItem("Poland", "", 0, false));
        adapter.addItem(new CountryItem("Qatar", "", 0, false));
        adapter.notifyDataSetChanged();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.back);

        refreshCosts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_next)
    void onClickBtnNext() {
        Intent intent = new Intent(ChooseCountriesActivity.this, ChooseCategoriesActivity.class);
        startActivity(intent);
    }

    public void refreshCosts() {
        ArrayList<CountryItem> selectedItems = adapter.getSelectedItems();

        int totalCost = selectedItems.size() * 120;
        String strTotalCost = String.format(getResources().getString(R.string.subscription_format), totalCost);
        tvTotalCost.setText(strTotalCost);
    }
}