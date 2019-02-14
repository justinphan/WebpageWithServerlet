/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phuphanmbp
 * @author Chris
 */
@WebServlet(urlPatterns = {"/AddNewType"})
public class AddNewType extends HttpServlet {
     @Resource(name = "jdbc/HW2DB")
    private javax.sql.DataSource datasource;

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
        try (PrintWriter out = response.getWriter()) {
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
        try {
           
            HttpSession session = request.getSession();
            Integer sessionCount = (Integer)session.getAttribute("sessionCount");
            if (sessionCount == null){
                sessionCount = new Integer(0);
                session.setAttribute("sessionCount", sessionCount);
            }
            session.setAttribute("sessionCount", sessionCount + 1);
                        
            ServletContext context = request.getServletContext();
            Integer contextCount = (Integer)context.getAttribute("contextCount");
            if (contextCount == null){
                contextCount = new Integer(0);
                context.setAttribute("contextCount", contextCount);
            }
            context.setAttribute("contextCount", contextCount + 1);
         
            String name;
            name = request.getParameter("newtype");   
            Connection connection = datasource.getConnection();
            String sql2 = "insert into VOTES (musictype, numvotes) values (?,1)";
            PreparedStatement insertType = connection.prepareStatement(sql2);
            insertType.setString(1, name);
            insertType.executeUpdate();
            insertType.close();
            connection.close(); 
            //request.getRequestDispatcher("getInfoBeforeForwardingtoJSP").forward(request, response);
            response.sendRedirect("getInfoBeforeForwardingtoJSP");

        }   catch (SQLException ex) {
            Logger.getLogger(AddNewType.class.getName()).log(Level.SEVERE, null, ex);
        }
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
