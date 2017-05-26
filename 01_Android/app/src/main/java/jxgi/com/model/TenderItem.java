package jxgi.com.model;

/**
 * Created by Caesar on 5/25/2017.
 */

public class TenderItem {
    private String tender;
    private String category;
    private String country;
    private String clientName;
    private String photoUrl;

    public TenderItem(String arg0, String arg1, String arg2, String arg3, String arg4) {
        this.tender = arg0;
        this.category = arg1;
        this.country = arg2;
        this.clientName = arg3;
        this.photoUrl = arg4;
    }

    public void setTender(String value) {
        this.tender = value;
    }

    public String getTender() {
        return tender;
    }

    public void setCategory(String value) {
        this.category = value;
    }

    public String getCategory() {
        return  category;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public String getCountry() {
        return country;
    }

    public void setClientName(String value) {
        this.clientName = value;
    }

    public String getClientName() {
        return clientName;
    }

    public void setPhotoUrl(String value) {
        this.photoUrl = value;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
