package fr.eni.ecole.auction.BO;

public class Item {
    protected int id_item;
    protected String item_name;
    protected String item_description;
    protected String bid_start_date;
    protected String bid_end_date;
    protected float starting_price;
    protected float selling_price;
    protected int id_user;
    protected int id_category;

    public Item(String item_name, String item_description, String bid_start_date, String bid_end_date, float starting_price,int id_user, int id_category) {
        this.item_name = item_name;
        this.item_description = item_description;
        this.bid_start_date = bid_start_date;
        this.bid_end_date = bid_end_date;
        this.starting_price = starting_price;
        this.id_user = id_user;
        this.id_category = id_category;
    }

    public Item(int id_item, String item_name, String item_description, String bid_start_date, String bid_end_date, float starting_price, float selling_price, int id_user, int id_category) {
        this.id_item = id_item;
        this.item_name = item_name;
        this.item_description = item_description;
        this.bid_start_date = bid_start_date;
        this.bid_end_date = bid_end_date;
        this.starting_price = starting_price;
        this.selling_price = selling_price;
        this.id_user = id_user;
        this.id_category = id_category;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getBid_start_date() {
        return bid_start_date;
    }

    public void setBid_start_date(String bid_start_date) {
        this.bid_start_date = bid_start_date;
    }

    public String getBid_end_date() {
        return bid_end_date;
    }

    public void setBid_end_date(String bid_end_date) {
        this.bid_end_date = bid_end_date;
    }

    public float getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(float starting_price) {
        this.starting_price = starting_price;
    }

    public float getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}