package net.codejava.javaee.bookstore;

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
    private BookDAO bookDAO;
    private UserDAO userDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
        userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);

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
                case "/listAuction":
                    listAuction(request, response);
                    break;
                case "/listUser":
                    listUsers(request, response);
                    break;
                case "/insertUser":
                    newUser(request, response);
                    break;
                case "/newUser":
                    userForm(request, response);
                    break;
                case "/submit":
                    login(request, response);
                    break;
                case "/loginProcess":
                    loginProcess(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                default:
                    listAuction(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
        dispatcher.forward(request, response);
    }

    private void listAuction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Item> listItem = bookDAO.listAllItems();
        request.setAttribute("listItem", listItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        dispatcher.forward(request, response);
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
            List<Item> listItem = bookDAO.listAllItems();
            request.setAttribute("listItem", listItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
            dispatcher.forward(request, response);

    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");

            RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
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
        String password = request.getParameter("password");

        try {
            User user = userDAO.checkLogin(email, password);
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

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void userForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);

    }

    private void loginAcces(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String nickname = request.getParameter("email");
        String password = request.getParameter("password");

        User loginUser = new User(nickname, password);
        boolean result = bookDAO.login(loginUser);
        if(result==false){
            RequestDispatcher dispatcher = request.getRequestDispatcher("LoginError.jsp");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("Loged.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book newBook = new Book(title, author, price);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }

    private void newUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nickname = request.getParameter("nickname");
        String lastName = request.getParameter("lastname");
        String firstName = request.getParameter("firstname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phonenumber");
        String street = request.getParameter("street");
        String postalCode = request.getParameter("postalcode");
        String city = request.getParameter("city");
        int credit = Integer.parseInt(request.getParameter("credit"));
        int isAdmin = Integer.parseInt(request.getParameter("isadmin"));

        User newUser = new User(nickname, lastName, firstName, email, password, phoneNumber, street, postalCode, city, credit, isAdmin);
        bookDAO.insertUser(newUser);
        response.sendRedirect("Loged.jsp");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book book = new Book(id, title, author, price);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Book book = new Book(id);
        bookDAO.deleteBook(book);
        response.sendRedirect("list");

    }
}