package fr.eni.ecole.auction.utils;

public final class Constants {
    //info jdbc
    public final static String JDBCURL = "jdbcURL";
    public final static String JDBCUSERNAME = "jdbcUsername";
    public final static String JDBCPASSWORD = "jdbcPassword";

   //switch
   public final static String ENDPOINT_LIST_USER = "/listUser";
   public final static String ENDPOINT_INSERT_USER = "/insertUser";
   public final static String ENDPOINT_NEW_USER = "/newUser";
   public final static String ENDPOINT_SUBMIT = "/submit";
   public final static String ENDPOINT_LOGIN_PROCESS = "/loginProcess";
   public final static String ENDPOINT_LOGIN = "/login";
   public final static String ENDPOINT_LOGOUT = "/logout";
   public final static String ENDPOINT_NEW_ITEM = "/newItem";
   public final static String ENDPOINT_INSERT_ITEM = "/insertItem";
   public final static String ENDPOINT_DELETE_USER = "/deleteUser";
   public final static String ENDPOINT_DELETE_ITEM = "/deleteItem";
   public final static String ENDPOINT_EDIT_USER = "/editUser";
   public final static String ENDPOINT_EDIT_ITEM = "/editItem";
   public final static String ENDPOINT_UPDATE_USER = "/updateUser";
   public final static String ENDPOINT_UPDATE_ITEM = "/updateItem";

    //Columns Name
    public final static String ID_CATEGORY = "id_category";
    public final static String EMAIL = "email";
    public final static String USER_PASSWORD = "user_password";
    public final static String ID_USER = "id_user";
    public final static String ID_ITEM = "id_item";
    public final static String ITEM_NAME = "item_name";
    public final static String ITEM_DESCRIPTION = "item_description";
    public final static String BID_START_DATE = "bid_start_date";
    public final static String BID_END_DATE = "bid_end_date";
    public final static String STARTING_PRICE = "starting_price";
    public final static String NICKNAME = "nickname";
    public final static String LAST_NAME = "last_name";
    public final static String FIRST_NAME = "first_name";
    public final static String PHONE_NUMBER = "phone_number";
    public final static String STREET = "street";
    public final static String POSTAL_CODE = "postal_code";
    public final static String CITY = "city";


// JSP
public final static String ITEM_LIST_JSP = "ItemList.jsp";
    public final static String LOGIN_JSP = "Login.jsp";
    public final static String HOME_JSP = "Home.jsp";
    public final static String ITEM_FORM_JSP = "ItemForm.jsp";
    public final static String USER_FORM_JSP = "UserForm.jsp";
    public final static String EDIT_ITEM_JSP = "EditItem.jsp";
    public final static String LIST_USER_JSP = "ListUser.jsp";
    public final static String HOME_PAGE = "/encheres";

    //ATTRIBUTES
    public final static String LIST_CATEGORY = "listCategory";
    public final static String LIST_ITEM = "listItem";
    public final static String LIST_USER = "listUser";

    //error message
    public final static String MESSAGE = "message";
    public final static String INVALID_LOGIN_DATA = "Invalid email/password";
    public final static String USED_NICKNAME = "nickname or email already used";



    //i dont know i was drunk
    public final static String ZERO = "0";
    public final static String USER = "user";
    public final static String ITEM = "item";





}
