package fr.eni.ecole.auction.utils;

import fr.eni.ecole.auction.BO.Item;
import fr.eni.ecole.auction.BO.User;
import fr.eni.ecole.auction.DAO.ItemDAO;
import fr.eni.ecole.auction.DAO.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserHandler {
    public static void listUsers(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO, ItemDAO itemDAO)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            Object userToken = session.getAttribute(Constants.USER);
            if(userToken != null){
                List<User> listUser = userDAO.listAllUsers();
                request.setAttribute(Constants.LIST_USER, listUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.LIST_USER_JSP);
                dispatcher.forward(request, response);
                return;
            }
        }
        List<Item> listItem = itemDAO.listAllItems();
        request.setAttribute(Constants.LIST_ITEM, listItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ITEM_LIST_JSP);
        dispatcher.forward(request, response);
    }
    public static void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(Constants.USER);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.HOME_PAGE);
            dispatcher.forward(request, response);
        }
    }
    public static void loginProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.LOGIN_JSP);
        dispatcher.forward(request, response);
    }
    public static void login(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO)
            throws ServletException, IOException{
        String email = request.getParameter(Constants.EMAIL);
        String user_password = request.getParameter(Constants.USER_PASSWORD);
        try {
            User user = userDAO.checkLogin(email, user_password);
            String destPage = Constants.LOGIN_JSP;

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute(Constants.USER, user);
                destPage = Constants.HOME_JSP;
            } else {
                String message = Constants.INVALID_LOGIN_DATA;
                request.setAttribute(Constants.MESSAGE, message);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
    public static void userForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.USER_FORM_JSP);
        dispatcher.forward(request, response);
    }
    public static void showEditFormUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter(Constants.ID_USER));
        if(session != null) {
            Object userToken = session.getAttribute(Constants.USER);
            int id_Confirmation = ((User) userToken).getId_user();
            int admin_confirmation = ((User) userToken).getIs_admin();
            if(admin_confirmation == 1){
                User actualUser = userDAO.getUser(id_user);
                RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.USER_FORM_JSP);
                request.setAttribute(Constants.USER, actualUser);
                dispatcher.forward(request, response);
                return;
            }
            if(id_user == id_Confirmation){
                User actualUser = userDAO.getUser(id_user);
                RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.USER_FORM_JSP);
                request.setAttribute(Constants.USER, actualUser);
                dispatcher.forward(request, response);
                return;
            }
        }
        response.sendRedirect(Constants.LIST_USER);
        return;
    }
    public static void newUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO)
            throws SQLException, IOException, ServletException {
        String nickname = request.getParameter(Constants.NICKNAME);
        String last_name = request.getParameter(Constants.LAST_NAME);
        String first_name = request.getParameter(Constants.FIRST_NAME);
        String email = request.getParameter(Constants.EMAIL);
        String user_password = request.getParameter(Constants.USER_PASSWORD);
        String phone_number = request.getParameter(Constants.PHONE_NUMBER);
        String street = request.getParameter(Constants.STREET);
        String postal_code = request.getParameter(Constants.POSTAL_CODE);
        String city = request.getParameter(Constants.CITY);
        User newUser = new User(nickname, last_name, first_name, email, user_password, phone_number, street, postal_code, city);
        try{
            userDAO.insertUser(newUser);
        }catch (SQLException ex){
            String message = Constants.USED_NICKNAME;
            request.setAttribute(Constants.MESSAGE, message);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.USER_FORM_JSP);
            dispatcher.forward(request, response);
            return;
        }
        response.sendRedirect(Constants.HOME_PAGE);
    }
    public static void updateUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO)
            throws SQLException, IOException {
        int id_user = Integer.parseInt(request.getParameter(Constants.ID_USER));
        String nickname = request.getParameter(Constants.NICKNAME);
        String lastName = request.getParameter(Constants.LAST_NAME);
        String firstName = request.getParameter(Constants.FIRST_NAME);
        String email = request.getParameter(Constants.EMAIL);
        String user_password = request.getParameter(Constants.USER_PASSWORD);
        String phoneNumber = request.getParameter(Constants.PHONE_NUMBER);
        String street = request.getParameter(Constants.STREET);
        String postalCode = request.getParameter(Constants.POSTAL_CODE);
        String city = request.getParameter(Constants.CITY);
        User user = new User(id_user, nickname, lastName, firstName, email, user_password, phoneNumber, street, postalCode, city);
        userDAO.updateUser(user);
        response.sendRedirect(Constants.LIST_USER);
    }
    public static void deleteUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDAO)
            throws SQLException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter(Constants.ID_USER));
        if(session != null) {
            Object userToken = session.getAttribute(Constants.USER);
            int id_Confirmation = ((User) userToken).getId_user();
            int admin_confirmation = ((User) userToken).getIs_admin();
            if(admin_confirmation == 1){
                User user = new User(id_user);
                userDAO.deleteUser(user);
                response.sendRedirect(Constants.LIST_USER);
                return;
            }
            if(id_user == id_Confirmation){
                User user = new User(id_user);
                userDAO.deleteUser(user);
                response.sendRedirect(Constants.LIST_USER);
                return;
            }
        }
        response.sendRedirect(Constants.LIST_USER);
        return;
    }
}