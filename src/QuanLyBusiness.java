
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phuong Anh Hoang
 */
public class QuanLyBusiness {
    //Khai báo danh sách quản lý
    private List<QuanLy> lstQuanLy = new ArrayList();
    
    /**
     * Method to get administrator list
     * @return 
     */
    public List<QuanLy> layDanhSachQuanLy(){
        //Create new administrator list
        List<QuanLy> lstQuanLy = new ArrayList();
        
        //Initialize database connection
        Connection conn = null;
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strQuanLy = "Select Username, HoTen, MatKhau from QuanLy";
            Statement comm = conn.createStatement();
            
            //Execute statement
            ResultSet rs = comm.executeQuery(strQuanLy);
        
            //Create object, assign values while the table has not ended and add them to the list
            QuanLy objQuanLy;
            
            while(rs.next()){
                objQuanLy = new QuanLy();
                objQuanLy.setUsername(rs.getString("Username"));
                objQuanLy.setHoTen(rs.getString("HoTen"));
                objQuanLy.setMatKhau(rs.getString("MatKhau"));
                
                lstQuanLy.add(objQuanLy);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstQuanLy;
    }
    
    /**
     * Method to get administrator info based on username
     * @param username
     * @return 
     */
    public QuanLy layChiTietQuanLy(String username){
        //Initialize database connection
        Connection conn = null;

        //Initiale object of class QuanLy
        QuanLy objQuanLy = new QuanLy();
            
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strLayChiTiet = "Select Username, HoTen, MatKhau from QuanLy where Username = '" + username + "'";
            
            Statement comm = conn.createStatement();
            
            //Execute statement and assign values to object
            ResultSet rs = comm.executeQuery(strLayChiTiet);
            
            while(rs.next()){
                objQuanLy = new QuanLy();
                objQuanLy.setUsername(rs.getString("Username"));
                objQuanLy.setHoTen(rs.getString("HoTen"));
                objQuanLy.setMatKhau(rs.getString("MatKhau"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objQuanLy;
    }
    
    /**
     * Method to change password of the user matching the entered username
     * @param username
     * @param matKhau new password
     * @return true if changed successfully, false if failed
     */
    public boolean doiMatKhau(String username, String matKhau){
        //Initialize database connection
        Connection conn = null;
            
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String doiMatKhau = "Update QuanLy set MatKhau = ? where Username = '" + username + "'";
            
            PreparedStatement comm = conn.prepareStatement(doiMatKhau);
            
            comm.setString(1, matKhau);
            
            //Execute statement
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
