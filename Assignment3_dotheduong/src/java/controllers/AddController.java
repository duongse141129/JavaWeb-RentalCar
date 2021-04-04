/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.CartDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AddController extends HttpServlet {
    private final static String ERROR="shopping.jsp";
    private final static String SUCCESS="shopping.jsp";
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
        String url=ERROR;
        
        try {
            String quantityText=request.getParameter("txtQuantity");
            int quantity=Integer.parseInt(quantityText);
            
            String carID=request.getParameter("txtCarID");
            String carName=request.getParameter("txtCarName");
            String color=request.getParameter("txtColor");
            String price=request.getParameter("txtPrice");            
            String amountText=request.getParameter("txtAmount");
            int amount=Integer.parseInt(amountText);
            String nsx=request.getParameter("txtNsx");
            String rating=request.getParameter("txtRating");
            String type=request.getParameter("txtTypeID");
            String img=request.getParameter("img"); 
            String dateRent=request.getParameter("txtRentDate");
            String dateReturn=request.getParameter("txtReturnDate");
            request.setAttribute("DATE_RENT", dateRent);
            request.setAttribute("DATE_RETURN", dateReturn);
            if(amount>quantity){
                request.setAttribute("MESSAGE", "The "+carName+" is out of stock");
            }
            else{        
                CarDTO car = new CarDTO(carID, carName, color, amount, Float.parseFloat(price), nsx, Float.parseFloat(rating), type, true, img);
                car.setRentDate(dateRent);
                car.setReturnDate(dateReturn);
                HttpSession session = request.getSession();
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                String userEmail = user.getEmail();
                String key = carID + ";" + dateRent + ";" + dateReturn;
                if (cart == null) {
                    cart = new CartDTO(userEmail, null);
                }
                if (cart.getCart() != null && cart.getCart().containsKey(key)) {
                    int check = cart.getCart().get(key).getQuantity() + amount;
                    if (check > quantity) {
                        request.setAttribute("MESSAGE", "The " + carName + " is out of stock");
                    } else {
                        cart.add(car, key);
                        CarDAO dao = new CarDAO();

                        int soLuong = cart.getCart().get(key).getQuantity();
                        long numDay = dao.getDateBetween(dateRent, dateReturn);
                        float priceOfdays = cart.getCart().get(key).getPrice() * numDay * soLuong;
                        cart.getCart().get(key).setPrice(priceOfdays);

                        session.setAttribute("CART", cart);
                        session.setAttribute("CART_DETAIL", cart.getCart().values());
                        request.setAttribute("MESSAGE", "you have bought :" + carName + " successful. You cart have " + cart.getCart().values().size() + " cars");
                        url = SUCCESS;
                    }
                } else {
                    cart.add(car, key);
                    CarDAO dao = new CarDAO();

                    int soLuong = cart.getCart().get(key).getQuantity();
                    long numDay = dao.getDateBetween(dateRent, dateReturn);
                    float priceOfdays = cart.getCart().get(key).getPrice() * numDay * soLuong;
                    cart.getCart().get(key).setPrice(priceOfdays);

                    session.setAttribute("CART", cart);
                    session.setAttribute("CART_DETAIL", cart.getCart().values());
                    request.setAttribute("MESSAGE", "you have bought :" + carName + " successful. You cart have " + cart.getCart().values().size() + " cars");
                    url = SUCCESS;
                }
            }
            
        } catch (Exception e) {
            log("Error at AddServlet "+e.toString());
        }finally{
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
