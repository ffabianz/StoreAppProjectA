package fr.eni.ecole.auction.BLL;

import fr.eni.ecole.auction.BO.Category;
import fr.eni.ecole.auction.BO.Item;
import fr.eni.ecole.auction.BO.User;
import fr.eni.ecole.auction.DAO.CategoryDAO;
import fr.eni.ecole.auction.DAO.ItemDAO;
import fr.eni.ecole.auction.DAO.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/index.jsp")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private ItemDAO itemDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
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
                case "/listUser":                               //working
                    listUsers(request, response);
                    break;
                case "/insertUser":                             //working
                    newUser(request, response);
                    break;
                case "/newUser":                                //working
                    userForm(request, response);
                    break;
                case "/submit":                                 //working
                    login(request, response);
                    break;
                case "/loginProcess":                            //working
                    loginProcess(request, response);
                    break;
                case "/login":                                     //working
                    login(request, response);
                    break;
                case "/logout":                                 //working
                    logout(request, response);
                    break;
                case "/newItem":                                //working
                    itemForm(request, response);
                    break;
                case "/insertItem":                             //working
                    insertItem(request, response);
                    break;
                case "/deleteUser":                             //working
                    deleteUser(request, response);
                    break;
                case "/editUser":                           //working
                    showEditFormUser(request, response);
                    break;

                case "/updateUser":                             //working
                    updateUser(request, response);
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
        List<Category> listCategory = categoryDAO.listCategories();
        String id_category = request.getParameter("id_category");
        if(id_category == null || id_category.equals("0")){
            List<Item> listItem = itemDAO.listAllItems();
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listItem", listItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if(id_category != null){
            int selected_id = Integer.parseInt(id_category);
            List<Item> listItem = itemDAO.listtemsBy(selected_id);
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listItem", listItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
            dispatcher.forward(request, response);
            return;
        }

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
           Object userToken = session.getAttribute("user");
           if(userToken != null){
               List<User> listUser = userDAO.listAllUsers();
               request.setAttribute("listUser", listUser);
               RequestDispatcher dispatcher = request.getRequestDispatcher("ListUser.jsp");
               dispatcher.forward(request, response);
               return;
           }
        }
            List<Item> listItem = itemDAO.listAllItems();
            request.setAttribute("listItem", listItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
            dispatcher.forward(request, response);

    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/encheres");
            dispatcher.forward(request, response);
        }
    }

    private void loginProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String email = request.getParameter("email");
        String user_password = request.getParameter("user_password");

        try {
            User user = userDAO.checkLogin(email, user_password);
            String destPage = "Login.jsp";

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                destPage = "Home.jsp";
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void itemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemForm.jsp");
        dispatcher.forward(request, response);
    }


    private void userForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        if(session != null) {
            Object userToken = session.getAttribute("user");
            int id_Confirmation = ((User) userToken).getId_user();
            int admin_confirmation = ((User) userToken).getIs_admin();
            if(admin_confirmation == 1){
                User actualUser = userDAO.getUser(id_user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
                request.setAttribute("user", actualUser);
                dispatcher.forward(request, response);
                return;
            }
            if(id_user == id_Confirmation){
                User actualUser = userDAO.getUser(id_user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
                request.setAttribute("user", actualUser);
                dispatcher.forward(request, response);
                return;
            }
        }
        response.sendRedirect("listUser");
        return;

    }
    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter("id_user")); // asking
        if(session != null) {
            Object userToken = session.getAttribute("user");
            int id_Confirmation = ((User) userToken).getId_user(); // logged
            if(id_Confirmation != 0 && id_Confirmation == id_user){           // if is valid and 2nd if who is logged is the same who ask
                String street = ((User) userToken).getStreet();
                String postal_code = ((User) userToken).getPostal_code();
                String city = ((User) userToken).getCity();
                String item_name = request.getParameter("item_name");
                String item_description = request.getParameter("item_description");
                String bid_start_date = request.getParameter("bid_start_date");
                String bid_end_date= request.getParameter("bid_end_date");
                float starting_price = Float.parseFloat(request.getParameter("starting_price"));
                int id_category = Integer.parseInt(request.getParameter("id_category"));
                Item newItem = new Item(item_name, item_description, bid_start_date, bid_end_date, starting_price, id_user, id_category);
                itemDAO.insertItem(newItem,street,postal_code,city);
                response.sendRedirect("/encheres");
            }
            else{
                return;
            }
        }
        return;
    }

    private void newUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nickname = request.getParameter("nickname");
        String last_name = request.getParameter("last_name");
        String first_name = request.getParameter("first_name");
        String email = request.getParameter("email");
        String user_password = request.getParameter("user_password");
        String phone_number = request.getParameter("phone_number");
        String street = request.getParameter("street");
        String postal_code = request.getParameter("postal_code");
        String city = request.getParameter("city");

        User newUser = new User(nickname, last_name, first_name, email, user_password, phone_number, street, postal_code, city);
        userDAO.insertUser(newUser);
        response.sendRedirect("/encheres");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        String nickname = request.getParameter("nickname");
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String email = request.getParameter("email");
        String user_password = request.getParameter("user_password");
        String phoneNumber = request.getParameter("phone_number");
        String street = request.getParameter("street");
        String postalCode = request.getParameter("postal_code");
        String city = request.getParameter("city");

        User user = new User(id_user, nickname, lastName, firstName, email, user_password, phoneNumber, street, postalCode, city);
        userDAO.updateUser(user);
        response.sendRedirect("listUser");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession(false);
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        if(session != null) {
            Object userToken = session.getAttribute("user");
            int id_Confirmation = ((User) userToken).getId_user();
            int admin_confirmation = ((User) userToken).getIs_admin();
            if(admin_confirmation == 1){
                User user = new User(id_user);
                userDAO.deleteUser(user);
                response.sendRedirect("ListUser");
                return;
            }
            if(id_user == id_Confirmation){
                User user = new User(id_user);
                userDAO.deleteUser(user);
                response.sendRedirect("ListUser");
                return;
            }
    }
        response.sendRedirect("listUser");
        return;
    }

}