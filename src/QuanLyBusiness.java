
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
     * Hàm lấy danh sách quản lý
     * @return 
     */
    public List<QuanLy> layDanhSachQuanLy(){
        //Khai báo danh sách quản lý
        List<QuanLy> lstQuanLy = new ArrayList();
        
        //Khởi tạo kết nối
        Connection conn = null;
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String strQuanLy = "Select Username, HoTen, MatKhau from QuanLy";
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strQuanLy);
        
            //Khai báo đối tượng quản lý
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
        
        //Đóng kết nối
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
     * Hàm lấy chi tiết quản lý theo username
     * @param username
     * @return 
     */
    public QuanLy layChiTietQuanLy(String username){
        //Khởi tạo kết nối
        Connection conn = null;

        //Khởi tạo đối tượng
        QuanLy objQuanLy = new QuanLy();
            
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String strLayChiTiet = "Select Username, HoTen, MatKhau from QuanLy where Username = '" + username + "'";
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc và trả về kết quả
            ResultSet rs = comm.executeQuery(strLayChiTiet);
            
            //Gán thông tin cho đối tượng 
            while(rs.next()){
                objQuanLy = new QuanLy();
                objQuanLy.setUsername(rs.getString("Username"));
                objQuanLy.setHoTen(rs.getString("HoTen"));
                objQuanLy.setMatKhau(rs.getString("MatKhau"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối
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
     * Hàm đổi mật khẩu người người trùng với số tài khoản truyền vào
     * @param stk số tài khoản của người dùng muốn đổi mật khẩu
     * @param matKhau mật khẩu mới cần cập nhật
     * @return true nếu thành công, false nếu thất bại
     */
    public boolean doiMatKhau(String username, String matKhau){
        //Khởi tạo kết nối
        Connection conn = null;
            
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String doiMatKhau = "Update QuanLy set MatKhau = ? where Username = '" + username + "'";
            
            //Tạo công việc
            PreparedStatement comm = conn.prepareStatement(doiMatKhau);
            
            comm.setString(1, matKhau);
            
            //Thực hiện công việc
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối
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
