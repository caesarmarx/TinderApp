package jxgi.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jxgi.com.R;
import jxgi.com.model.TenderItem;

public class TenderAdapter extends RecyclerView.Adapter<TenderAdapter.TenderViewHolder> {

    private ArrayList<TenderItem> items = new ArrayList<>();
    private Context parent;

    public TenderAdapter(Context ctx) {
        this.parent = ctx;
    }

    @Override
    public TenderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tender, parent, false);
        return new TenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TenderViewHolder holder, int position) {
        final TenderItem item = items.get(position);

        holder.tvCountry.setText(item.getCountry());
        holder.tvCategory.setText(item.getCategory());
        holder.tvTender.setText(item.getTender());
        holder.tvClientName.setText(item.getClientName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(ArrayList<TenderItem> items) {
        this.items.clear();
        this.items = items;
    }

    public class TenderViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @Bind(R.id.tv_country)
        TextView tvCountry;
        @Bind(R.id.tv_category)
        TextView tvCategory;
        @Bind(R.id.tv_tender)
        TextView tvTender;
        @Bind(R.id.tv_client_name)
        TextView tvClientName;
        @Bind(R.id.iv_photo)
        ImageView ivPhoto;

        public TenderViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
