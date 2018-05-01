package com.example.ravit.shoppinglist;

/**
 * Created by ravit on 4/26/2018.
 */

class TaskInfo {

    public String name,price,quantity,favorite,id;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

}
