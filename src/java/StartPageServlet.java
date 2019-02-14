/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phuphanmbp
 * @author Chris
 */
@WebServlet(urlPatterns = {"/StartPageServlet"})
public class StartPageServlet extends HttpServlet {
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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Favorites type of music</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Vote your favorite type of music is: </br>");
            Connection connection = datasource.getConnection();
            String sql= "select * from VOTES";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            //first form
            out.println("<form name =\"voteForm\" action=\"UpdateVote\" method=\"GET\" > ");
                while (resultSet.next()){
                    out.println("<input type=\"checkbox\" name = \"musictype\"  value=\""+resultSet.getString("musictype")+"\"/> " + resultSet.getString("musictype")   +"</br>");  
                }
                out.println("<input type=\"submit\" value=\"Submit Vote\" >");
            out.println("</form>");
            //end of first form
            
            out.println("or add a new one <br/>");
            
            // second form
            out.println("<form name =\"testForm\" action=\"AddNewType\" method=\"GET\" > ");
            out.println("New music type: <input type = \" textbox \" name = \"newtype\" /> <br/>  ");
            out.println("<input type=\"submit\" value=\"Add type and vote\" >");
            out.println("</form>");
            //end of second form

            out.println("</body>");
            out.println("</html>");


        } catch (SQLException ex) {
            Logger.getLogger(StartPageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
