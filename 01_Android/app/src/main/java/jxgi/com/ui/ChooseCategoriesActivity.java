package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.adapter.ChooseCategoriesAdapter;
import jxgi.com.adapter.ChooseCountriesAdapter;
import jxgi.com.model.CategoryItem;
import jxgi.com.model.CountryItem;

public class ChooseCategoriesActivity extends AppCompatActivity {

    @Bind(R.id.category_list)
    RecyclerView categoryList;
    @Bind(R.id.tv_total_cost)
    TextView tvTotalCost;

    private ChooseCategoriesAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_categories);
        ButterKnife.bind(this);

        categoryList.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(ChooseCategoriesActivity.this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryList.setLayoutManager(mLinearLayoutManager);

        adapter = new ChooseCategoriesAdapter(ChooseCategoriesActivity.this);
        categoryList.setAdapter(adapter);
        adapter.addItem(new CategoryItem("Accounting, Banking, Legal", "", 0, false));
        adapter.addItem(new CategoryItem("Building and Trades", "", 0, false));
        adapter.addItem(new CategoryItem("Civil", "", 0, false));
        adapter.addItem(new CategoryItem("Cleaning & Facility Management", "", 0, false));
        adapter.addItem(new CategoryItem("Consultants", "", 0, false));
        adapter.addItem(new CategoryItem("Electrical & Automation", "", 0, false));
        adapter.addItem(new CategoryItem("Engineering Consultants", "", 0, false));
        adapter.addItem(new CategoryItem("General, Property, Auctions", "", 0, false));
        adapter.addItem(new CategoryItem("HR & Training", "", 0, false));
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
        ArrayList<CategoryItem> selectedItems = adapter.getSelectedItems();
        if(selectedItems.size() == 0) {
            Toast.makeText(ChooseCategoriesActivity.this, R.string.select_categories, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ChooseCategoriesActivity.this, RulesAndRegulationsActivity.class);
            startActivity(intent);
        }
    }

    public void refreshCosts() {
        ArrayList<CategoryItem> selectedItems = adapter.getSelectedItems();

        int totalCost = selectedItems.size() * 120;
        String strTotalCost = String.format(getResources().getString(R.string.subscription_format), totalCost);
        tvTotalCost.setText(strTotalCost);
    }
}