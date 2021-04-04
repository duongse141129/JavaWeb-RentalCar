/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CarDAO;
import daos.UserDAO;
import daos.VerifyUtils;
import dtos.TypeDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {
    private static final String ADMIN = "management.jsp";   
    private static final String USER = "SearchController";   
    private static final String ERROR = "login.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.checkLogin(email, password);
            HttpSession session=request.getSession();
            
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyUtils.verify(gRecaptchaResponse);
            if (verify) {
                if (user == null) {
                    session.setAttribute("MESSAGE", " Email or Password is incorrect");
                } else {
                    if (user.getRoleID().equals("AD")) {
                        url = ADMIN;
                    } else if (user.getRoleID().equals("US")) {
                        url = USER;
                        CarDAO dao=new CarDAO();
                        List<TypeDTO> listType=dao.getListType();
                        session.setAttribute("LIST_TYPE", listType);
                    }
                    session.setAttribute("LOGIN_USER", user);
                }
            }
        } catch (Exception e) {
            log("Error at LoginServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
