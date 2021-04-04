/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.UserDTO;
import dtos.UserErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CreateUserController extends HttpServlet {
    private final static String ERROR = "registration.jsp";
    private final static String SUCCESS = "SendMailController";
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
        UserErrorDTO errorObject = new UserErrorDTO("", "", "", "", "","");
        try {
            String email = request.getParameter("txtEmail");
            String fullName = request.getParameter("txtFullName");
            String roleID = request.getParameter("txtRoleID");
            String phone = request.getParameter("txtPhone");
            String address = request.getParameter("txtAddress");
            String createDate = request.getParameter("txtCreateDate");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            request.setAttribute("CREATE_DATE", createDate);
            UserDAO dao = new UserDAO();
            boolean check = true;
            if (email.isEmpty()) {
                check = false;
                errorObject.setEmailError("Email is not empty");
            }
            if (fullName.isEmpty()) {
                check = false;
                errorObject.setFullNameError("FullName is not empty ");
            }
            if (phone.isEmpty()) {
                check = false;
                errorObject.setPhoneError("phone is not empty ");
            }
            if(phone.length()!=10 || dao.checkNumber(phone)!=true){
                check = false;
                errorObject.setPhoneError("wrong format phone must 10 number ");
            }
            if (address.isEmpty()) {
                check = false;
                errorObject.setAddressError("address is not empty ");
            }
            if (password.isEmpty()) {
                check = false;
                errorObject.setPasswordError("Password is not empty ");
            }
            if (!password.equals(confirm)) {
                check = false;
                errorObject.setConfirmError("Confirm password not same");
            }
            if (check == true) {
                UserDTO user = new UserDTO(email, fullName, password, phone, address, createDate, roleID, false);
                dao.createNew(user);
                url = SUCCESS;
                HttpSession session=request.getSession();
                session.setAttribute("EMAIL_REGISTER", email);
            } else {
                request.setAttribute("USER_ERROR", errorObject);
            }
        } catch (Exception e) {
            if(e.toString().contains("duplicate")){
                errorObject.setEmailError("email is duplicate ");
                request.setAttribute("USER_ERROR", errorObject);
            }else{
                e.toString();
                log("Error at CreateUserController: " + e.toString());
            }
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
