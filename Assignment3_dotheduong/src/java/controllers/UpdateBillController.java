/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.CartDTO;
import java.io.IOException;
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
public class UpdateBillController extends HttpServlet {
    private static final String ERROR="viewOrder.jsp";
    private static final String SUCCESS="ViewOrderController";
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
            String carID=request.getParameter("txtCarID");
            String carName=request.getParameter("txtCarName");
            String amountTest=request.getParameter("txtAmount");
            int amount=Integer.parseInt(amountTest);
            String rentDateUpdate=request.getParameter("txtDateRent");
            String returnDateUpdate=request.getParameter("txtDateReturn");
            
            String dateRent=request.getParameter("dateRent");
            String dateReturn=request.getParameter("dateReturn");
            String key=carID+";"+rentDateUpdate+";"+returnDateUpdate;
            

            CarDAO dao=new CarDAO();
            HttpSession session=request.getSession();
            CartDTO cart=(CartDTO) session.getAttribute("CART");
            if(cart.getCart().containsKey(key) && dateRent.equals(rentDateUpdate) && dateReturn.equals(returnDateUpdate)){
                float price=cart.getCart().get(key).getPrice();
                int soluong=cart.getCart().get(key).getQuantity();
                if(dao.checkQuanlity(carID, amount)){
                    price = price / (soluong);
                    price = price * amount;
                    cart.update(key, amount);
                    cart.getCart().get(key).setPrice(price);
                    session.setAttribute("CART", cart);
                }
                else{
                    request.setAttribute("MESSAGE", "The "+carName+" is out of stock");
                }
            }
            else{
                if (dao.checkDate(rentDateUpdate, returnDateUpdate)) {
                    if (dao.checkDetail(carID, amount, rentDateUpdate, returnDateUpdate)) {
                        key = carID + ";" + dateRent + ";" + dateReturn;
                        cart.delete(key);
                        key = carID + ";" + rentDateUpdate + ";" + returnDateUpdate;
                        CarDTO car = new CarDTO(carID, carName, "", amount, 0, "", 0, "", true, "");

                        car.setRentDate(rentDateUpdate);
                        car.setReturnDate(returnDateUpdate);

                        cart.add(car, key);
                        String drent = cart.getCart().get(key).getRentDate();
                        String dreturn = cart.getCart().get(key).getReturnDate();
                        long numDay = dao.getDateBetween(drent, dreturn);
                        int soluong = cart.getCart().get(key).getQuantity();
                        float priceOfdays = dao.getPriceOfCar(carID) * numDay * soluong;
                        cart.getCart().get(key).setPrice(priceOfdays);
                    } else {
                        request.setAttribute("MESSAGE", "The " + carName + " is out of stock");
                    }
                }
                else{
                    request.setAttribute("MESSAGE", "choose the appropriate rental date and returnDate date (future date)");
                }
                
            }
            
            
            session.setAttribute("CART", cart);
            url=SUCCESS;        
        } catch (Exception e) {
            log("Error at UpdateBillController: "+e.toString());
        }
        finally{
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
