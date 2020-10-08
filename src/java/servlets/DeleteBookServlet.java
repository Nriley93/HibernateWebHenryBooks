
package servlets;

import business.Inventory;
import business.InventoryDB;
import business.Store;
import business.StoreDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n.riley
 */
public class DeleteBookServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL="/ViewInventory.jsp", msg="", bookcd="";
        Store s;

        try {
            bookcd = request.getParameter("bookcd");
            if(bookcd.isEmpty()) {
                msg += "Missing book code<br>";
            }
            if(msg.isEmpty()) {
                s = (Store) request.getSession().getAttribute("store");
                boolean bookok = false;
                for(Inventory inv : s.getBookinv()) {
                    if(inv.getBookID().equalsIgnoreCase(bookcd)) {
                        request.getSession().setAttribute("inv", inv);
                        bookok = true;
                    }  
                }
                if(!bookok) {
                    msg += "Book code not found in inventory<br>";
                } 
            }
        } catch(Exception e) {
            msg = "Servlet error: " + e.getMessage() + "<br>";
        }
        try {
            if(msg.isEmpty()) {
                boolean bookdeleted = false;
                    List<Store> stores = 
                    (List<Store>) request.getSession().getAttribute("stores");
                    if(stores == null) {
                        throw new IOException("stores list not on session<br>");
                    }
                    List<Inventory> inv;
                    for(Store store : stores) {
                        int i = 0;
                        inv = store.getBookinv();
                        if(inv.get(i).getBookID().equalsIgnoreCase(bookcd)){
                            bookdeleted = InventoryDB.delete(inv.get(i));
                        }
                        i++;
                    } // End of for
                    request.getSession().removeAttribute("inv");
                if(bookdeleted) {
                    stores = StoreDB.getStores();
                    request.getSession().setAttribute("stores", stores);
                    URL="/StoreSelection.jsp";
                    msg += "Book Deleted!<br>";
                } else {
                        msg += "Sorry that didnt work.<br>";                 
                }
            }
        } catch(IOException e) {
            msg = "Delete servlet error: " + e.getMessage();
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
