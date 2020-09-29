
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
     * Hàm lấy danh sách từ database NguoiDung
     * @return danh sách người dùng
     */
    public List<NguoiDung> layDanhSach(){
        //Khai báo danh sách người dùng
        List<NguoiDung> lstNguoiDung = new ArrayList();

        //Khởi tạo kết nối
        Connection conn = null;
            
        try {
            
            String strNguoiDung = "Select SoTaiKhoan, HoTen, MatKhau, NgaySinh, GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan from NguoiDung";
            
            //Kết nối
            conn = DataProvider.ketNoi();
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strNguoiDung);
            
            //Khai báo đối tượng
            NguoiDung objNguoiDung;
            
            //Khởi tạo đối tượng, gán thuộc tính cho đối tượng và thêm vào danh sách
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
                
                //Thêm vào danh sách
                lstNguoiDung.add(objNguoiDung);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Hàm lấy danh sách người dùng kiêm tìm kiếm theo tên hoặc số tài khoản
     * @param tuKhoa
     * @return toàn bộ danh sách người dùng hoặc danh sách theo từ khóa tìm kiếm
     */
    public List<NguoiDung> timKiem(String tuKhoa){
        //Khai báo danh sách người dùng
        List<NguoiDung> lstNguoiDung = new ArrayList();

        //Khởi tạo kết nối
        Connection conn = null;
            
        try {
            
            String strNguoiDung = "Select SoTaiKhoan, HoTen, MatKhau, NgaySinh, GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan from NguoiDung ";
            
            //Nếu từ khóa được nhập thì thêm String điều kiện để select
            if(!tuKhoa.isEmpty()){
                strNguoiDung += "where SoTaiKhoan = '" + tuKhoa + "' OR HoTen like '%" + tuKhoa + "%'";
            }
            
            //Kết nối
            conn = DataProvider.ketNoi();
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strNguoiDung);
            
            //Khai báo đối tượng
            NguoiDung objNguoiDung;
            
            //Khởi tạo đối tượng, gán thuộc tính cho đối tượng và thêm vào danh sách
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
                
                //Thêm vào danh sách
                lstNguoiDung.add(objNguoiDung);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
     * Hàm lấy thông tin người dùng theo số tài khoàn được nhập
     * @param stk
     * @return đối tượng NguoiDung có số tài khoản trùng với số tài khoản được truyền vào
     */
    public NguoiDung layChiTietNguoiDung(String stk){
        //Khởi tạo kết nối
        Connection conn = null;

        //Khởi tạo đối tượng
        NguoiDung objND = new NguoiDung();
            
        try {
            String strLayChiTiet = "Select SoTaiKhoan, HoTen, MatKhau, NgaySinh, "
                    + "GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan from NguoiDung where SoTaiKhoan = '" + stk + "'";
            
            conn = DataProvider.ketNoi();
            
            //Tạo 1 công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc và trả về kết quả
            ResultSet rs = comm.executeQuery(strLayChiTiet);
            
            //Gán thông tin cho đối tượng 
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
        
        //Đóng kết nối dù thành công hay thất bại
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
     * Hàm thêm người dùng
     * @param objNguoiDung đối tượng được khởi tạo để thêm thông tin vào database
     * @return true nếu thành công, false nếu thất bại
     */
    public boolean themNguoiDung(NguoiDung objNguoiDung)
    {
        //Khởi tạo kết nối
        Connection conn = null;
        
        try {
            //Tạo kết nối
            conn = DataProvider.ketNoi();
            
            //Thêm mới thông tin vào database
            String strThem = "Insert into NguoiDung(SoTaiKhoan, HoTen, MatKhau, NgaySinh, GioiTinh, DienThoai, Email, DiaChi, SoDu, MaTaiKhoan) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            //Tạo công việc
            PreparedStatement comm = conn.prepareStatement(strThem);
            
            //Gán thông tin từ object đã khai báo vào database
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
            
            //Thực thi các công việc như insert, update, delete
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối
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
        //Khởi tạo kết nối
        Connection conn = null;
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            //Cập nhật người dùng
            String strCapNhat = "Update NguoiDung set HoTen = ?, MatKhau = ?, NgaySinh = ?, GioiTinh = ?, "
                    + "DienThoai = ?, Email = ?, DiaChi = ?, SoDu = ?, MaTaiKhoan = ? where SoTaiKhoan = ?";
            
            //Tạo công việc
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
            
            //Thực hiện cập nhật
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối dù thành công hay thất bại
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
     * Hàm xóa ngừi dùng
     * @param stk
     * @return true nếu thành công, false nếu thất bại
     */
    public boolean xoaNguoiDung(String stk)
    {
        //Khởi tạo kết nối
        Connection conn = null;
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String strXoa = "Delete from NguoiDung where SoTaiKhoan = ?";
            
            //Tạo công việc
            PreparedStatement comm = conn.prepareStatement(strXoa);
            
            comm.setString(1, stk);
            
            //Thực hiện xóa người dùng trùng với số tài khoản được truyền vào
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối
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
     * Hàm đổi mật khẩu người người trùng với số tài khoản truyền vào
     * @param stk số tài khoản của người dùng muốn đổi mật khẩu
     * @param matKhau mật khẩu mới cần cập nhật
     * @return true nếu thành công, false nếu thất bại
     */
    public boolean doiMatKhau(String stk, String matKhau){
        //Khởi tạo kết nối
        Connection conn = null;
            
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String doiPIN = "Update NguoiDung set MatKhau = ? where SoTaiKhoan = '" + stk + "'";
            
            //Tạo công việc
            PreparedStatement comm = conn.prepareStatement(doiPIN);
            
            comm.setString(1, matKhau);
            
            //Thực hiện cập nhật mật khẩu
            return comm.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối
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
     * Hàm lấy số danh sách số tài khoản
     * @return danh sách số tài khoản
     */
    public List<NguoiDung> layDanhSachSTK(){
        //Khởi tạo kết nối
        Connection conn = null;
        
        //Khai báo danh sách để chứa thông tin số tài khoản
        List<NguoiDung> soTaiKhoan = new ArrayList();
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            Statement comm = conn.createStatement();
            
            String strSoTaiKhoan = "Select SoTaiKhoan from NguoiDung";
            
            //Tạo công việc
            ResultSet rs = comm.executeQuery(strSoTaiKhoan);
            
            //Khai báo 1 đối tượng
            NguoiDung objND;
            
            //Khi danh sách người dùng vẫn còn thì gán thông tin số tài khoản cho đối tượng người dùng và thêm vào list
            while(rs.next()){
                objND = new NguoiDung();
                objND.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                
                soTaiKhoan.add(objND);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Đóng kết nối
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
