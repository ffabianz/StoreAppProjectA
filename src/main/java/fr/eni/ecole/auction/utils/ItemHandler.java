package fr.eni.ecole.auction.utils;

import fr.eni.ecole.auction.BO.Category;
import fr.eni.ecole.auction.BO.Item;
import fr.eni.ecole.auction.BO.User;
import fr.eni.ecole.auction.DAO.CategoryDAO;
import fr.eni.ecole.auction.DAO.ItemDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class ItemHandler {

    public static void listAuction(HttpServletRequest request, HttpServletResponse response, CategoryDAO categoryDAO, ItemDAO itemDAO)
            throws SQLException, IOException, ServletException {
        List<Category> listCategory = categoryDAO.listCategories();
        String id_category = request.getParameter(Constants.ID_CATEGORY);
        if(id_category == null || id_category.equals(Constants.ZERO)){
            List<Item> listItem = itemDAO.listAllItems();
            request.setAttribute(Constants.LIST_CATEGORY, listCategory);
            request.setAttribute(Constants.LIST_ITEM, listItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ITEM_LIST_JSP);
            dispatcher.forward(request, response);
            return;
        }
        if(id_category != null){
            int selected_id = Integer.parseInt(id_category);
            List<Item> listItem = itemDAO.listtemsBy(selected_id);
            request.setAttribute(Constants.LIST_CATEGORY, listCategory);
            request.setAttribute(Constants.LIST_ITEM, listItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ITEM_LIST_JSP);
            dispatcher.forward(request, response);
            return;
        }
    }
    public static void itemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ITEM_FORM_JSP);
        dispatcher.forward(request, response);
    }
    public static void showEditFormItem(HttpServletRequest request, HttpServletResponse response, ItemDAO itemDAO)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter(Constants.ID_USER));
        int id_item = Integer.parseInt(request.getParameter(Constants.ID_ITEM)); // id user how create the item
        if(session != null) {
            Object userToken = session.getAttribute(Constants.USER);
            if(userToken == null) {
                response.sendRedirect(Constants.HOME_PAGE);
                return;
            }
            int id_Confirmation = ((User) userToken).getId_user();      // id user how try to edit
            if(id_user == id_Confirmation){
                Item actualItem = itemDAO.getItem(id_item);
                RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.EDIT_ITEM_JSP);
                request.setAttribute(Constants.ITEM, actualItem);
                dispatcher.forward(request, response);
                return;
            }
        }
        response.sendRedirect(Constants.HOME_PAGE);
        return;
    }
    public static void insertItem(HttpServletRequest request, HttpServletResponse response, ItemDAO itemDAO)
            throws SQLException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter(Constants.ID_USER)); // asking
        if(session != null) {
            Object userToken = session.getAttribute(Constants.USER);
            int id_Confirmation = ((User) userToken).getId_user(); // logged
            if(id_Confirmation != 0 && id_Confirmation == id_user){           // if is valid and 2nd if who is logged is the same who ask
                String street = ((User) userToken).getStreet();
                String postal_code = ((User) userToken).getPostal_code();
                String city = ((User) userToken).getCity();
                String item_name = request.getParameter(Constants.ITEM_NAME);
                String item_description = request.getParameter(Constants.ITEM_DESCRIPTION);
                String bid_start_date = request.getParameter(Constants.BID_START_DATE);
                String bid_end_date= request.getParameter(Constants.BID_END_DATE);
                float starting_price = Float.parseFloat(request.getParameter(Constants.STARTING_PRICE));
                int id_category = Integer.parseInt(request.getParameter(Constants.ID_CATEGORY));
                Item newItem = new Item(item_name, item_description, bid_start_date, bid_end_date, starting_price, id_user, id_category);
                itemDAO.insertItem(newItem,street,postal_code,city);
                response.sendRedirect(Constants.HOME_PAGE);
            }
            else{
                return;
            }
        }
        return;
    }
    public static void updateItem(HttpServletRequest request, HttpServletResponse response, ItemDAO itemDAO)
            throws SQLException, IOException {
        int id_item = Integer.parseInt(request.getParameter(Constants.ID_ITEM));
        String item_name = request.getParameter(Constants.ITEM_NAME);
        String item_description = request.getParameter(Constants.ITEM_DESCRIPTION);
        String bid_end_date = request.getParameter(Constants.BID_END_DATE);
        int id_category = Integer.parseInt(request.getParameter(Constants.ID_CATEGORY));
        Item item = new Item(id_item,item_name, item_description, bid_end_date, id_category);
        itemDAO.updateItem(item);
        response.sendRedirect(Constants.HOME_PAGE);
    }
    public static void deleteItem(HttpServletRequest request, HttpServletResponse response, ItemDAO itemDAO)
            throws SQLException, IOException {
        HttpSession session = request.getSession(false);
        int id_item = Integer.parseInt(request.getParameter(Constants.ID_ITEM));
        int id_user = Integer.parseInt(request.getParameter(Constants.ID_USER)); // how made it
        if(session != null) {
            Object userToken = session.getAttribute(Constants.USER);
            if(userToken == null) {
                response.sendRedirect(Constants.HOME_PAGE);
                return;
            }
            int id_Confirmation = ((User) userToken).getId_user();
            if(id_user == id_Confirmation){
                Item item = new Item(id_item);
                itemDAO.deleteItem(item);
                response.sendRedirect(Constants.HOME_PAGE);
                return;
            }
        }
        response.sendRedirect(Constants.HOME_PAGE);
        return;
    }
}