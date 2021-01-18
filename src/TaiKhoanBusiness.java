
import java.sql.Connection;
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


public class TaiKhoanBusiness {
    /**
     * Method to get list of all accounts
     * @return 
     */
    public List<TaiKhoan> layDanhSach(){
        //Create a list for accounts
        List<TaiKhoan> lstTaiKhoan = new ArrayList();

        //Initialize database connection
        Connection conn = null;
            
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strTaiKhoan = "Select MaTaiKhoan, TaiKhoan from TaiKhoan";
            Statement comm = conn.createStatement();
            
            //Execute statment
            ResultSet rs = comm.executeQuery(strTaiKhoan);
            
            //Create an object, assign values from the statement to object and add object to the list
            TaiKhoan objTaiKhoan;
            
            while(rs.next()){
                objTaiKhoan = new TaiKhoan();
                objTaiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                
                objTaiKhoan.setTaiKhoan(rs.getString("TaiKhoan"));
                
                lstTaiKhoan.add(objTaiKhoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally//Close connection
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstTaiKhoan;
    }
    
    /**
     * Method to get account information based on transmitted keyword
     * @param maTaiKhoan
     * @return object of TaiKhoan class with account information matching keyword
     */
    public TaiKhoan layChiTietTaiKhoan(String maTaiKhoan){
        TaiKhoan objTaiKhoan = null;

        //Initialize database connection
        Connection conn = null;
            
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strTaiKhoan = "Select MaTaiKhoan, TaiKhoan from TaiKhoan where MaTaiKhoan = '" + maTaiKhoan + "'";
            Statement comm = conn.createStatement();
            
            //Execute statement
            ResultSet rs = comm.executeQuery(strTaiKhoan);
            
            //Initialize object and assign values
            while(rs.next()){
                objTaiKhoan = new TaiKhoan();
                objTaiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                
                objTaiKhoan.setTaiKhoan(rs.getString("TaiKhoan"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally//Close connection
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objTaiKhoan;
    }
}
