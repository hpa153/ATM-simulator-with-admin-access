
import java.sql.Connection;
import java.sql.Date;
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
public class NguoiDungBusiness {
    private List<NguoiDung> lstNguoiDung = new ArrayList();
    
    /**
     * Method to get list from NguoiDung table
     * @return user list
     */
    public List<NguoiDung> layDanhSach(){
        //Create user list
        List<NguoiDung> lstNguoiDung = new ArrayList();

        //Initialize database connection
        Connection conn = null;
            
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strNguoiDung = "Select SoTaiKhoan, HoTen, MatKhau, NgaySinh, GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan from NguoiDung";
            Statement comm = conn.createStatement();
            
            //Execute statement
            ResultSet rs = comm.executeQuery(strNguoiDung);
            
            //Create object of NguoiDung class and assign values from statement
            NguoiDung objNguoiDung;
            
            while(rs.next()){
                objNguoiDung = new NguoiDung();
                objNguoiDung.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                objNguoiDung.setHoTen(rs.getString("HoTen"));
                objNguoiDung.setMatKhau(rs.getString("MatKhau"));
                objNguoiDung.setNgaySinh(rs.getDate("NgaySinh"));
                objNguoiDung.setGioiTinh(rs.getString("GioiTinh"));
                objNguoiDung.setDienThoai(rs.getString("DienThoai"));
                objNguoiDung.setEmail(rs.getString("Email"));
                objNguoiDung.setDiaChi(rs.getString("DiaChi"));
                objNguoiDung.setSoDu(rs.getDouble("SoDu"));
                objNguoiDung.setTaiKhoan(rs.getString("MaTaiKhoan"));
                
                //Add object to user list
                lstNguoiDung.add(objNguoiDung);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstNguoiDung;
    }
    
    /**
     * Method to get user list and search for name or account number
     * @param tuKhoa
     * @return list of users or list based on the keyword
     */
    public List<NguoiDung> timKiem(String tuKhoa){
        //Create user list
        List<NguoiDung> lstNguoiDung = new ArrayList();

        //Initialize database connection
        Connection conn = null;
            
        try {
            
            String strNguoiDung = "Select SoTaiKhoan, HoTen, MatKhau, NgaySinh, GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan from NguoiDung ";
            
            //If keyword is entered, add the where phrase to the search string for selection
            if(!tuKhoa.isEmpty()){
                strNguoiDung += "where SoTaiKhoan = '" + tuKhoa + "' OR HoTen like '%" + tuKhoa + "%'";
            }
            
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            Statement comm = conn.createStatement();
            
            //Execute select statement
            ResultSet rs = comm.executeQuery(strNguoiDung);
            
            //Create object of NguoiDung class, assign values to this object and add object to the user list
            NguoiDung objNguoiDung;
            
            while(rs.next()){
                objNguoiDung = new NguoiDung();
                objNguoiDung.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                objNguoiDung.setHoTen(rs.getString("HoTen"));
                objNguoiDung.setMatKhau(rs.getString("MatKhau"));
                objNguoiDung.setNgaySinh(rs.getDate("NgaySinh"));
                objNguoiDung.setGioiTinh(rs.getString("GioiTinh"));
                objNguoiDung.setDienThoai(rs.getString("DienThoai"));
                objNguoiDung.setEmail(rs.getString("Email"));
                objNguoiDung.setDiaChi(rs.getString("DiaChi"));
                objNguoiDung.setSoDu(rs.getDouble("SoDu"));
                objNguoiDung.setTaiKhoan(rs.getString("MaTaiKhoan"));
                
                lstNguoiDung.add(objNguoiDung);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstNguoiDung;
    }
    
    /**
     * Method to get user info based on the entered account number
     * @param stk
     * @return object of NguoiDung class with matching account number
     */
    public NguoiDung layChiTietNguoiDung(String stk){
        //Initialize database connection
        Connection conn = null;

        NguoiDung objND = new NguoiDung();
            
        try {
            String strLayChiTiet = "Select SoTaiKhoan, HoTen, MatKhau, NgaySinh, "
                    + "GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan from NguoiDung where SoTaiKhoan = '" + stk + "'";
            
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            Statement comm = conn.createStatement();
            
            //Execute statement and return result
            ResultSet rs = comm.executeQuery(strLayChiTiet);
            
            //Assign values to the object
            while(rs.next()){
                objND = new NguoiDung();
                objND.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                objND.setHoTen(rs.getString("HoTen"));
                objND.setMatKhau(rs.getString("MatKhau"));
                objND.setNgaySinh(rs.getDate("NgaySinh"));
                objND.setGioiTinh(rs.getString("GioiTinh"));
                objND.setDienThoai(rs.getString("DienThoai"));
                objND.setEmail(rs.getString("Email"));
                objND.setDiaChi(rs.getString("DiaChi"));
                objND.setSoDu(rs.getDouble("SoDu"));
                objND.setTaiKhoan(rs.getString("MaTaiKhoan"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objND;
    }
    
    /**
     * Method to add an user
     * @param objNguoiDung
     * @return true if added successfully, false if failed
     */
    public boolean themNguoiDung(NguoiDung objNguoiDung)
    {
        //Initialize database connection
        Connection conn = null;
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strThem = "Insert into NguoiDung(SoTaiKhoan, HoTen, MatKhau, NgaySinh, GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement comm = conn.prepareStatement(strThem);
            
            //Add values assigned to the object to the database
            comm.setString(1, objNguoiDung.getSoTaiKhoan());
            comm.setString(2, objNguoiDung.getHoTen());
            comm.setString(3, objNguoiDung.getMatKhau());
            comm.setDate(4, new Date(objNguoiDung.getNgaySinh().getTime()));
            comm.setString(5, objNguoiDung.getGioiTinh());
            comm.setString(6, objNguoiDung.getDienThoai());
            comm.setString(7, objNguoiDung.getEmail());
            comm.setString(8, objNguoiDung.getDiaChi());
            comm.setDouble(9, objNguoiDung.getSoDu());
            comm.setString(10, objNguoiDung.getTaiKhoan());
            
            //Execute statement
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * Hàm cập nhật thông tin người dùng từ đối tượng được khởi tạo
     * @param objNguoiDung
     * @return true nếu thành công, false nếu thất bại
     */
    public boolean suaNguoiDung(NguoiDung objNguoiDung)
    {
        //Initialize database connection
        Connection conn = null;
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strCapNhat = "Update NguoiDung set HoTen = ?, MatKhau = ?, NgaySinh = ?, GioiTinh = ?, "
                    + "DienThoai = ?, Email = ?, DiaChi = ?, SoDu = ?, MaTaiKhoan = ? where SoTaiKhoan = ?";
            
            PreparedStatement comm = conn.prepareStatement(strCapNhat);
            
            comm.setString(1, objNguoiDung.getHoTen());
            comm.setString(2, objNguoiDung.getMatKhau());
            comm.setDate(3, new Date(objNguoiDung.getNgaySinh().getTime()));
            comm.setString(4, objNguoiDung.getGioiTinh());
            comm.setString(5, objNguoiDung.getDienThoai());
            comm.setString(6, objNguoiDung.getEmail());
            comm.setString(7, objNguoiDung.getDiaChi());
            comm.setDouble(8, objNguoiDung.getSoDu());
            comm.setString(9, objNguoiDung.getTaiKhoan());
            comm.setString(10, objNguoiDung.getSoTaiKhoan());
            
            //Execute statement
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * Method to delete an user
     * @param stk
     * @return true if removed successfully, false if failed
     */
    public boolean xoaNguoiDung(String stk)
    {
        //Initialize database connection
        Connection conn = null;
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strXoa = "Delete from NguoiDung where SoTaiKhoan = ?";
            
            PreparedStatement comm = conn.prepareStatement(strXoa);
            
            comm.setString(1, stk);
            
            //Execute statement
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;        
    }
    
    /**
     * Method to change password of the user matching the entered account number
     * @param stk account number of user to change account number
     * @param matKhau new password
     * @return true if changed successfully, false f failed
     */
    public boolean doiMatKhau(String stk, String matKhau){
        //Initialize database connection
        Connection conn = null;
            
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String doiPIN = "Update NguoiDung set MatKhau = ? where SoTaiKhoan = '" + stk + "'";
            
            PreparedStatement comm = conn.prepareStatement(doiPIN);
            
            comm.setString(1, matKhau);
            
            //Execute statement
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    /**
     * Method to get list of account numbers
     * @return list of all account numbers
     */
    public List<NguoiDung> layDanhSachSTK(){
        //Initialize database connection
        Connection conn = null;
        
        //Create a list to add all account numbers
        List<NguoiDung> soTaiKhoan = new ArrayList();
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            Statement comm = conn.createStatement();
            
            //Create statement
            String strSoTaiKhoan = "Select SoTaiKhoan from NguoiDung";
            
            ResultSet rs = comm.executeQuery(strSoTaiKhoan);
            
            //Create an object to assign account number values and add them to the list
            NguoiDung objND;
            
            while(rs.next()){
                objND = new NguoiDung();
                objND.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                
                soTaiKhoan.add(objND);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return soTaiKhoan;
    }
}
