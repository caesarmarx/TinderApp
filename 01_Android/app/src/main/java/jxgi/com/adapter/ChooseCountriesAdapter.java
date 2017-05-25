package jxgi.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jxgi.com.R;
import jxgi.com.model.CountryItem;
import jxgi.com.ui.ChooseCountriesActivity;

public class ChooseCountriesAdapter extends RecyclerView.Adapter<ChooseCountriesAdapter.CountryViewHolder> {

    private ChooseCountriesActivity parent;
    private List<CountryItem> items = new ArrayList<>();

    public ChooseCountriesAdapter(ChooseCountriesActivity parent) {
        this.parent = parent;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_choose_countries, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, int position) {
        final CountryItem item = items.get(position);

        holder.tvCountry.setText(item.getCountryName());
        if(item.getSelected()) {
            holder.chkSelect.setChecked(true);
        } else {
            holder.chkSelect.setChecked(false);
        }

        holder.view.setTag(position);

        holder.chkSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                if(checkBox.isChecked()) {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }
                parent.refreshCosts();
            }
        });

        holder.countryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.chkSelect.isChecked()) {
                    holder.chkSelect.setChecked(false);
                    item.setSelected(false);
                } else if(!holder.chkSelect.isChecked()) {
                    holder.chkSelect.setChecked(true);
                    item.setSelected(true);
                }
                parent.refreshCosts();
            }
        });
    }

    public CountryItem getItem(int pos) {
        return items.get(pos);
    }

    public void clearItems() {
        items.clear();
    }

    public void addItem(CountryItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<CountryItem> items) {
        this.items.clear();
        this.items = items;
    }

    public ArrayList<CountryItem> getSelectedItems() {
        ArrayList<CountryItem> selectedItems = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getSelected())
                selectedItems.add(items.get(i));
        }

        return selectedItems;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @Bind(R.id.country_layout)
        RelativeLayout countryLayout;
        @Bind(R.id.tv_country)
        TextView tvCountry;
        @Bind(R.id.btn_check)
        CheckBox chkSelect;

        public CountryViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
