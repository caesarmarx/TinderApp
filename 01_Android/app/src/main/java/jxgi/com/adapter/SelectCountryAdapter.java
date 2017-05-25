package jxgi.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jxgi.com.R;

public class SelectCountryAdapter extends RecyclerView.Adapter<SelectCountryAdapter.CountryViewHolder> {

    private String[] items;
    private int lastCheckedPosition = 11;

    public SelectCountryAdapter(String[] countries, int selected) {
        this.items = countries;
        lastCheckedPosition = selected;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_profile_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, int position) {
        final String item = items[position];

        holder.tvCountry.setText(item);
        holder.btnRadio.setChecked(position == lastCheckedPosition);
        holder.btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckedPosition = holder.getAdapterPosition();
                notifyItemRangeChanged(0, items.length);
            }
        });
        holder.countryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckedPosition = holder.getAdapterPosition();
                notifyItemRangeChanged(0, items.length);
            }
        });

    }

    public int getSelectedPos() {
        return lastCheckedPosition;
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @Bind(R.id.country_layout)
        RelativeLayout countryLayout;
        @Bind(R.id.btn_radio)
        RadioButton btnRadio;
        @Bind(R.id.tv_country)
        TextView tvCountry;

        public CountryViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
