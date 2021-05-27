package fr.eni.ecole.auction.BLL;

import fr.eni.ecole.auction.DAO.CategoryDAO;
import fr.eni.ecole.auction.DAO.ItemDAO;
import fr.eni.ecole.auction.DAO.UserDAO;
import fr.eni.ecole.auction.utils.Constants;
import fr.eni.ecole.auction.utils.ItemHandler;
import fr.eni.ecole.auction.utils.UserHandler;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/index.jsp")
public class ControllerServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private ItemDAO itemDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter(Constants.JDBCURL);
        String jdbcUsername = getServletContext().getInitParameter(Constants.JDBCUSERNAME);
        String jdbcPassword = getServletContext().getInitParameter(Constants.JDBCPASSWORD);
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
        itemDAO = new ItemDAO(jdbcURL, jdbcUsername, jdbcPassword);
        categoryDAO = new CategoryDAO(jdbcURL,jdbcUsername,jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case Constants.ENDPOINT_LIST_USER:
                    listUsers(request, response);
                    break;
                case Constants.ENDPOINT_INSERT_USER:
                    newUser(request, response);
                    break;
                case Constants.ENDPOINT_NEW_USER:
                    userForm(request, response);
                    break;
                case Constants.ENDPOINT_SUBMIT:
                    login(request, response);
                    break;
                case Constants.ENDPOINT_LOGIN_PROCESS:
                    loginProcess(request, response);
                    break;
                case Constants.ENDPOINT_LOGIN:
                    login(request, response);
                    break;
                case Constants.ENDPOINT_LOGOUT:
                    logout(request, response);
                    break;
                case Constants.ENDPOINT_NEW_ITEM:
                    itemForm(request, response);
                    break;
                case Constants.ENDPOINT_INSERT_ITEM:
                    insertItem(request, response);
                    break;
                case Constants.ENDPOINT_DELETE_USER:
                    deleteUser(request, response);
                    break;
                case Constants.ENDPOINT_DELETE_ITEM:
                    deleteItem(request, response);
                    break;
                case Constants.ENDPOINT_EDIT_USER:
                    showEditFormUser(request, response);
                    break;
                case Constants.ENDPOINT_EDIT_ITEM:
                    showEditFormItem(request, response);
                    break;
                case Constants.ENDPOINT_UPDATE_USER:
                    updateUser(request, response);
                    break;
                case Constants.ENDPOINT_UPDATE_ITEM:
                    updateItem(request, response);
                    break;
                default:
                    listAuction(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listAuction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ItemHandler.listAuction(request, response, categoryDAO, itemDAO);
    }
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        UserHandler.listUsers(request,response, userDAO, itemDAO);

    }
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserHandler.logout(request,response);
    }
    private void loginProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        UserHandler.loginProcess(request,response);
    }
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        UserHandler.login(request,response,userDAO);
    }
    private void itemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemHandler.itemForm(request,response);
    }
    private void userForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserHandler.userForm(request,response);
    }
    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        UserHandler.showEditFormUser(request,response,userDAO);
    }
    private void showEditFormItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        ItemHandler.showEditFormItem(request,response,itemDAO);
    }
    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        ItemHandler.insertItem(request,response,itemDAO);
    }
    private void newUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
      UserHandler.newUser(request,response,userDAO);
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        UserHandler.updateUser(request,response,userDAO);
    }
    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        ItemHandler.updateItem(request,response,itemDAO);
    }
    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
       ItemHandler.deleteItem(request,response,itemDAO);
    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        UserHandler.deleteUser(request,response,userDAO);
    }
}