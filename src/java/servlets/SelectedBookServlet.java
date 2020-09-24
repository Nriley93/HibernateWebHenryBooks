
package servlets;

import business.Inventory;
import business.Store;
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
public class SelectedBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL="/ViewInventory.jsp", msg="";
        Store s;
        try {
            String bookcd = request.getParameter("bookcd");
            if(bookcd.isEmpty()) {
                msg += "Missing book code<br>";
            }
//           is book code valid?? find out!!! lecture 5, 15mins
            if(msg.isEmpty()) {
                s = (Store) request.getSession().getAttribute("store");
                boolean bookok = false;
                for(Inventory inv : s.getBookinv()) {
                    if(inv.getBookID().equalsIgnoreCase(bookcd)) {
                        request.getSession().setAttribute("inv", inv);
                        bookok = true;
                        URL = "/UpdateInventory.jsp";
                    }
                }// end of for
                if(!bookok) {
                        msg += "Book code not found in inventory<br>";
                }
            }
        } catch(Exception e) {
            msg = "Servlet error: " + e.getMessage() + "<br>";
        }
         request.setAttribute("msg", msg);
        RequestDispatcher disp = 
                getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
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
