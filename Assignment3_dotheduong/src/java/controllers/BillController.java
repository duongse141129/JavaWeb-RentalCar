/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.CartDTO;
import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class BillController extends HttpServlet {
    private static final String ERROR="viewOrder.jsp";
    private static final String SUCCESS="shopping.jsp";
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
            HttpSession session=request.getSession();
            CartDTO cart=(CartDTO) session.getAttribute("CART");
            String orderDate=""+session.getAttribute("CURRENT_DATE");
            String totalPriceText=""+session.getAttribute("TOTAL_MONEY");
            String codeDiscount=""+session.getAttribute("CODE_DISCOUNT");
            CarDAO dao=new CarDAO();
            if(cart!=null && cart.getCart()!=null && cart.getCart().values().size()>0){
                int numOrderDetail = dao.getCountOrderDetail();
                String orderID = "O" + dao.getCountOrder();
                UserDTO user =(UserDTO) session.getAttribute("LOGIN_USER");
                OrderDTO order=new OrderDTO(orderID, user.getEmail(), Float.parseFloat(totalPriceText), orderDate, true);
                dao.insertOrder(order);

                Set<String> keys = cart.getCart().keySet();
                boolean check=true;
                for (String key : keys) {
                    String detailID = "OD" + numOrderDetail;
                    CarDTO dto = cart.getCart().get(key);
                    OrderDetailDTO ord = new OrderDetailDTO(detailID, orderID, dto.getCarID(), dto.getQuantity(), dto.getRentDate(), dto.getReturnDate(), dto.getRating(), dto.getPrice(), true);
                    if(!dao.checkDetail(dto.getCarID(), dto.getQuantity(), dto.getRentDate(), dto.getReturnDate())){
                        check=false;
                    }                   
                    numOrderDetail += 1;                 
                }
                if(check){
                    numOrderDetail = dao.getCountOrderDetail();
                    for (String key : keys) {
                        String detailID = "OD" + numOrderDetail;
                        CarDTO dto = cart.getCart().get(key);
                        OrderDetailDTO ord = new OrderDetailDTO(detailID, orderID, dto.getCarID(), dto.getQuantity(), dto.getRentDate(), dto.getReturnDate(), dto.getRating(), dto.getPrice(), true);
                        dao.insertOrderDetail(ord);
                        numOrderDetail += 1;
                    }
                    cart.setCart(null);
                    if (codeDiscount != null) {
                        dao.removeDiscount(codeDiscount);
                    }
                    session.setAttribute("CART", cart);
                    request.setAttribute("MESSAGE", "Bill successful");
                    url = SUCCESS;
                }
                else{
                    request.setAttribute("MESSAGE", "Bill fail");
                    url = ERROR;
                }
            }
            else{
                request.setAttribute("MESSAGE", "there are no product in your cart");
            }
        } catch (Exception e) {
            log("error at BillController: "+e.toString());
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
