
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
    public List<TaiKhoan> layDanhSach(){
        //Khai báo danh sách tài khoản
        List<TaiKhoan> lstTaiKhoan = new ArrayList();

        //Khởi tạo kết nối
        Connection conn = null;
            
        try {
            
            String strTaiKhoan = "Select MaTaiKhoan, TaiKhoan from TaiKhoan";
            
            //Kết nối
            conn = DataProvider.ketNoi();
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strTaiKhoan);
            
            //Khai báo đối tượng
            TaiKhoan objTaiKhoan;
            
            //Khởi tạo đối tượng, gán thuộc tính cho đối tượng và chuyển về danh sách
            while(rs.next()){
                objTaiKhoan = new TaiKhoan();
                objTaiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                
                objTaiKhoan.setTaiKhoan(rs.getString("TaiKhoan"));
                
                //Thêm vào danh sách
                lstTaiKhoan.add(objTaiKhoan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally//dù thành công hay thất bại luôn thực hiện code block, ở đây là đóng kết nối
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstTaiKhoan;
    }
    
    public TaiKhoan layChiTietTaiKhoan(String maTaiKhoan){
        //Khai báo đối tượng tài khoản
        TaiKhoan objTaiKhoan = null;

        //Khởi tạo kết nối
        Connection conn = null;
            
        try {
            
            String strTaiKhoan = "Select MaTaiKhoan, TaiKhoan from TaiKhoan where MaTaiKhoan = '" + maTaiKhoan + "'";
            
            //Kết nối
            conn = DataProvider.ketNoi();
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strTaiKhoan);
            
            //Khởi tạo đối tượng, gán thuộc tính cho đối tượng và chuyển về danh sách
            while(rs.next()){
                objTaiKhoan = new TaiKhoan();
                objTaiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                
                objTaiKhoan.setTaiKhoan(rs.getString("TaiKhoan"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally//dù thành công hay thất bại luôn thực hiện code block, ở đây là đóng kết nối
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
