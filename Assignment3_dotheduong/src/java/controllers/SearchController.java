/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CarDAO;
import dtos.CarDTO;
import dtos.TypeDTO;
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
public class SearchController extends HttpServlet {
    private static final String ERROR="shopping.jsp";
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
            CarDAO dao=new CarDAO();
            List<TypeDTO> listType=dao.getListType();
            HttpSession session=request.getSession();
            session.setAttribute("LIST_TYPE", listType);
            String radio=request.getParameter("rd"); 
            String search = "";
            if(radio.equals("name")){
                search=request.getParameter("txtSearch");
            }else if(radio.equals("type")){
                search=request.getParameter("cmbType");
                request.setAttribute("rdType", "type");
            }
            String num=request.getParameter("txtAmountSearch"); 
            String rentDate=request.getParameter("txtRentDate");
            String returnDate=request.getParameter("txtReturnDate");      
            
            if(rentDate.isEmpty() || returnDate.isEmpty()){
                request.setAttribute("MESSAGE", "choose rentDate and returnDate");
            }
            if(dao.checkDate(rentDate, returnDate)){
                request.setAttribute("DATE_RENT", rentDate);
                request.setAttribute("DATE_RETURN", returnDate);
                int count = dao.getCountCarForUser(search, Integer.parseInt(num), rentDate, returnDate, radio);
                int totalPage = 0;
                if (count % 20 != 0) {
                    totalPage = (count / 20) + 1;
                } else {
                    totalPage = (count / 20);
                }
                request.setAttribute("TOTAL_PAGE", totalPage);
                
                String pageS = request.getParameter("txtCurrentPage");
                int page = 1;
                if (pageS != null) {
                    page = Integer.parseInt(pageS);
                }
                List<CarDTO> list = dao.getListCar(search, Integer.parseInt(num), rentDate, returnDate, radio,page);
                if (list != null) {
                    url = SUCCESS;
                    request.setAttribute("LIST", list);
                } else {
                    request.setAttribute("MESSAGE", "Not found");
                }
            }
            else{
                request.setAttribute("MESSAGE", "choose the appropriate rental date and returnDate date (future date)");
            }

            
        } catch (Exception e) {
            log("Error at SearchUserServlet: "+e.toString());
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
