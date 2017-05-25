package jxgi.com.model;

import jxgi.com.consts.CommonConsts;

/**
 * Created by Caesar on 5/21/2017.
 */

public class CountryItem {
    private String countryName;
    private String expireDate;
    private int available;
    private boolean selected;

    public CountryItem(String arg0, String arg1, int arg2, boolean arg3) {
        this.countryName = arg0;
        this.expireDate = arg1;
        this.available = arg2;
        this.selected = arg3;
    }

    public CountryItem() {
        countryName = "";
        expireDate = "";
        selected = false;
        available = CommonConsts.COUNTRY_DEFAULT;
    }

    public void setCountryName(String value) {
        this.countryName = value;
    }

    public String getCountryName() {
        return countryName;
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
