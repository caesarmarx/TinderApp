package jxgi.com.model;

import jxgi.com.consts.CommonConsts;

/**
 * Created by Caesar on 5/21/2017.
 */

public class CategoryItem {
    private String categoryName;
    private String expireDate;
    private int available;
    private boolean selected;

    public CategoryItem(String arg0, String arg1, int arg2, boolean arg3) {
        this.categoryName = arg0;
        this.expireDate = arg1;
        this.available = arg2;
        this.selected = arg3;
    }

    public CategoryItem() {
        categoryName = "";
        expireDate = "";
        selected = false;
        available = CommonConsts.COUNTRY_DEFAULT;
    }

    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setAvailable(int value) {
        this.available = value;
    }

    public int getAvailable() {
        return available;
    }

    public void setSelected(boolean value) {
        this.selected = value;
    }

    public boolean getSelected() {
        return selected;
    }
}
