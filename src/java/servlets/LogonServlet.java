
package servlets;

import business.Store;
import business.StoreDB;
import business.User;
import business.UserDB;
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
public class LogonServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL="/Logon.jsp";
        String msg="";
        int pwdattempt=0, userid=0;
        User u = null;
        
        try {
            userid = Integer.parseInt(
                    request.getParameter("userid").trim());
            u = UserDB.getUser(userid);
            if(u == null) {
                msg = "User " + userid + " not found in DB.<br>";
            } else {    
                pwdattempt = Integer.parseInt(
                    request.getParameter("password"));
                u.setPwdAttempt(pwdattempt);
                if(u.isAuthenticated()) {
                    msg = "User " + u.getUserName() + " authenticated<br>";
                    request.getSession().setAttribute("user", u);
                } else {
                    msg = "User " + u.getUserName() + " not authenticated<br>";
                }
            } 
        } catch(Exception e) {
            msg = "Logon servlet error: " + e.getMessage() + "<br>";
        }
        
        if(u != null && u.isAuthenticated()) {
            List<Store> stores;
            try {
                stores = StoreDB.getStores();
                if(stores != null && stores.size() > 0) {
                    URL="/StoreSelection.jsp";
                    request.getSession().setAttribute("stores", stores);
                } else {
                    msg = "No stores read from file<br>";
                }
            } catch(Exception e) {
                msg = "Logon servlet error: " + e.getMessage() + "<br>";            }
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
