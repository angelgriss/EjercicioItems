package model;

import java.lang.reflect.Field;

public class Item {

    private String id;

    private String available_quantity;

    private String currency_id;

    private String title;

    private String price;

    private String condition;

    private String description;

    private String listing_type_id;

    private Pictures[] pictures;

    private String buying_mode;

    private String category_id;

    private String video_id;

    private String warranty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailable_quantity ()
    {
        return available_quantity;
    }

    public void setAvailable_quantity (String available_quantity)
    {
        this.available_quantity = available_quantity;
    }

    public String getCurrency_id ()
    {
        return currency_id;
    }

    public void setCurrency_id (String currency_id)
    {
        this.currency_id = currency_id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getCondition ()
    {
        return condition;
    }

    public void setCondition (String condition)
    {
        this.condition = condition;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getListing_type_id ()
    {
        return listing_type_id;
    }

    public void setListing_type_id (String listing_type_id)
    {
        this.listing_type_id = listing_type_id;
    }

    public Pictures[] getPictures ()
    {
        return pictures;
    }

    public void setPictures (Pictures[] pictures)
    {
        this.pictures = pictures;
    }

    public String getBuying_mode ()
    {
        return buying_mode;
    }

    public void setBuying_mode (String buying_mode)
    {
        this.buying_mode = buying_mode;
    }

    public String getCategory_id ()
    {
        return category_id;
    }

    public void setCategory_id (String category_id)
    {
        this.category_id = category_id;
    }

    public String getVideo_id ()
    {
        return video_id;
    }

    public void setVideo_id (String video_id)
    {
        this.video_id = video_id;
    }

    public String getWarranty ()
    {
        return warranty;
    }

    public void setWarranty (String warranty)
    {
        this.warranty = warranty;
    }

    @Override
    public String toString()
    {
        return "available_quantity = "+available_quantity+", currency_id = "+currency_id+", title = "+title+", price = "+price+", condition = "+condition+", description = "+description+", listing_type_id = "+listing_type_id+", pictures = "+pictures+", buying_mode = "+buying_mode+", category_id = "+category_id+", video_id = "+video_id+", warranty = "+warranty+"]";
    }

    public boolean checkNull()  {
        if(this.available_quantity == null || this.available_quantity.trim().isEmpty()) return true;
        if(this.currency_id == null || this.currency_id.trim().isEmpty()) return true;
        if(this.title == null || this.title.trim().isEmpty()) return true;
        if(this.price == null || this.price.trim().isEmpty()) return true;
        if(this.condition == null || this.condition.trim().isEmpty()) return true;
        if(this.description == null || this.description.trim().isEmpty()) return true;
        if(this.listing_type_id == null || this.listing_type_id.trim().isEmpty()) return true;
        if(this.buying_mode == null || this.buying_mode.trim().isEmpty()) return true;
        if(this.category_id == null || this.category_id.trim().isEmpty()) return true;
        if(this.video_id == null || this.video_id.trim().isEmpty()) return true;
        if(this.warranty == null || this.warranty.trim().isEmpty()) return true;
        if(this.pictures.length==0) return true;
        return false;
    }
}
