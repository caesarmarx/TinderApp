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
import jxgi.com.model.CategoryItem;
import jxgi.com.model.CountryItem;
import jxgi.com.ui.ChooseCategoriesActivity;
import jxgi.com.ui.ChooseCountriesActivity;

public class ChooseCategoriesAdapter extends RecyclerView.Adapter<ChooseCategoriesAdapter.CategoryViewHolder> {

    private ChooseCategoriesActivity parent;
    private List<CategoryItem> items = new ArrayList<>();

    public ChooseCategoriesAdapter(ChooseCategoriesActivity parent) {
        this.parent = parent;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_choose_categories, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final CategoryItem item = items.get(position);

        holder.tvCategory.setText(item.getCategoryName());
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

        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
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

    public CategoryItem getItem(int pos) {
        return items.get(pos);
    }

    public void clearItems() {
        items.clear();
    }

    public void addItem(CategoryItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<CategoryItem> items) {
        this.items.clear();
        this.items = items;
    }

    public ArrayList<CategoryItem> getSelectedItems() {
        ArrayList<CategoryItem> selectedItems = new ArrayList<>();
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

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @Bind(R.id.category_layout)
        RelativeLayout categoryLayout;
        @Bind(R.id.tv_category)
        TextView tvCategory;
        @Bind(R.id.btn_check)
        CheckBox chkSelect;

        public CategoryViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
