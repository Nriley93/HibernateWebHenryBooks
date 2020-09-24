
package servlets;

import business.Inventory;
import business.Store;
import business.StoreDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */
public class UpdateInventoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL="/ViewInventory.jsp", msg="";
        int onhand = 0;
        Store s;
        Inventory invitem;
        try {
            onhand = Integer.parseInt(request.getParameter("onhand"));
            if(onhand < 0) {
                msg += "On hand entry must be >= 0<br>";
            }
        } catch(Exception e) {
            msg += "Onhand entry was illegal or missing<br>";
        }
        if(msg.isEmpty()) {
            try{
                s = (Store) request.getSession().getAttribute("store");
                invitem = (Inventory) request.getSession().getAttribute("inv");
                invitem.setOnHand(onhand);
                msg = StoreDB.persistStore(s) + "<br>";
                request.getSession().removeAttribute("inv");
            } catch(Exception e) {
                msg += "Servlet error: " + e.getMessage() + "<br>";
            } 
        }// end of if
         request.setAttribute("msg", msg);
        RequestDispatcher disp = 
                getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
    }// end of method

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
