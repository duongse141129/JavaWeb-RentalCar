/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CarDAO;
import dtos.OrderDetailDTO;
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
public class SearchHistoryController extends HttpServlet {
    private static final String SUCCESS="viewHistory.jsp";
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
        String url=SUCCESS;
        try {
            String searchCarName=request.getParameter("txtSearchHistory");
            String dateOrder=request.getParameter("dateSearch");
            CarDAO dao=new CarDAO();
            HttpSession session=request.getSession();
            UserDTO user =(UserDTO) session.getAttribute("LOGIN_USER");
            if(searchCarName!=null && dateOrder!=null && !dateOrder.isEmpty()){
                List<OrderDetailDTO> list = dao.getListOrderDetailForSearch(user.getEmail(),searchCarName,dateOrder);
                request.setAttribute("ORDER_DATE", dateOrder);
                if (list != null) {
                    request.setAttribute("LIST_HISTORY", list);
                } else {
                    request.setAttribute("MESSAGE", "You have not booked any products yet in "+dateOrder);
                }
            }
            else{
                request.setAttribute("MESSAGE", "select order date");
            }
            url=SUCCESS;        
        } catch (Exception e) {
            log("Error at ViewHistoryServlet: "+e.toString());
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
