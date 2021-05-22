package net.codejava.javaee.bookstore;

public class User {
    protected int id_user;
    protected String nickname;
    protected String last_name;
    protected String first_name;
    protected String email;
    protected String user_password;
    protected String phone_number;
    protected String street;
    protected String postal_code;
    protected String city;
    protected int credit;
    protected int is_admin;

    public User() {
    }

    public User(int id_user) {
        this.id_user = id_user;
    }

    public User(String nickname, String user_password) {
        this.nickname = nickname;
        this.user_password = user_password;
    }

    public User(String nickname, String last_name, String first_name, String email, String user_password, String phone_number, String street, String postal_code, String city, int credit, int is_admin) {
        this.nickname = nickname;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.user_password = user_password;
        this.phone_number = phone_number;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
        this.credit = credit;
        this.is_admin = is_admin;
    }

    public User(String nickname, String last_name, String first_name, String email, String phone_number, String street, String postal_code, String city) {
        this.nickname = nickname;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.phone_number = phone_number;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
    }

    public User(int id_user, String nickname, String last_name, String first_name, String email, String user_password, String phone_number, String street, String postal_code, String city) {
        this.id_user = id_user;
        this.nickname = nickname;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.user_password = user_password;
        this.phone_number = phone_number;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
    }

    public User(int id_user, String nickname, String last_name, String first_name, String email, String phone_number, String street, String postal_code, String city) {
        this.id_user = id_user;
        this.nickname = nickname;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.phone_number = phone_number;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
    }

    public int getId_user() { return id_user; }

    public void setId_user(int id_user) { this.id_user = id_user; }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String password) {
        this.user_password = user_password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }
}
