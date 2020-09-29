
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
public class GiaoDich {
    /**
     * Hàm lấy danh sách các giao dịch kiêm tìm kiếm giao dịch theo số tài khoản
     * @param stk 
     * @return toàn bộ danh sách các giao dịch hoặc các giao dịch của số tài khoản được truyền vào
     */
    public static List<SaoKe> docGiaoDich(String stk) {
        //Khỏi tạo kết nối
        Connection conn = null;
        
        //Khai báo danh sách
        List<SaoKe> lstGiaoDich = new ArrayList();
        
        try {
            
            String strGiaoDich = "Select MaGiaoDich, ThoiGianGiaoDich, GiaoDich, "
                    + "SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung from LichSuGiaoDich where 1=1 ";
            
            //Nếu từ khóa được nhập thì lấy danh sách theo từ khóa
            if(!stk.isEmpty()){
                strGiaoDich += "AND SoTaiKhoan = '" + stk + "'";
            }
            
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strGiaoDich);
            
            //Khai báo đối tượng
            SaoKe objSaoKe;
            
            //Khi danh sách chưa hết, gán thông tin vào đối tượng và thêm đối tượng vào list
            while(rs.next()){
                objSaoKe = new SaoKe();
                objSaoKe.setMaGiaoDich(rs.getInt("MaGiaoDich"));
                objSaoKe.setThoiGianGiaoDich(rs.getTimestamp("ThoiGianGiaoDich"));
                objSaoKe.setGiaoDich(rs.getString("GiaoDich"));
                objSaoKe.setSoTien(rs.getDouble("SoTien"));
                objSaoKe.setSoDu(rs.getDouble("SoDu"));
                objSaoKe.setNguoiThuHuong(rs.getString("NguoiThuHuong"));
                objSaoKe.setSoTaiKhoanThuHuong(rs.getString("SoTaiKhoanThuHuong"));
                objSaoKe.setNoiDung(rs.getString("NoiDung"));
                
                lstGiaoDich.add(objSaoKe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaoKe.class.getName()).log(Level.SEVERE, null, ex);
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
        return lstGiaoDich;
    } 
    
    /**
     * Hàm thực hiện giao dịch chuyển tiền, cập nhật số dư của người dùng
     * @param objSaoKe
     * @param stk
     * @return true nếu thêm giao dịch thành công, false nếu thất bại
     */
    public boolean chuyenTien(SaoKe objSaoKe, String stk){
        //Khởi tạo kết nối
        Connection conn = null;
        
        //Lấy thông tin người dùng cần cập nhật theo số tài khoản
        NguoiDung objNguoiDung = DataProvider.getNguoiDungBus().layChiTietNguoiDung(stk);
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String strThem = "Insert into LichSuGiaoDich(MaGiaoDich, SoTaiKhoan, "
            + "ThoiGianGiaoDich, GiaoDich, SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //Tạo công việc
            PreparedStatement comm1 = conn.prepareStatement(strThem);

            comm1.setInt(1, layMaGiaoDich());
            comm1.setString(2, stk);
            comm1.setObject(3, thoiGianGiaoDich());
            comm1.setString(4, "-");
            comm1.setDouble(5, objSaoKe.getSoTien());
            comm1.setDouble(6, (objNguoiDung.getSoDu() - objSaoKe.getSoTien()));
            comm1.setString(7, objSaoKe.getNguoiThuHuong());
            comm1.setString(8, objSaoKe.getSoTaiKhoanThuHuong());
            comm1.setString(9, objSaoKe.getNoiDung());

            String strCapNhatSoDu = "Update NguoiDung set SoDu = ? where SoTaiKhoan = '" + stk + "'";

            //Tạo công việc cập nhật số dư người dùng
            PreparedStatement comm2 = conn.prepareStatement(strCapNhatSoDu);

            comm2.setDouble(1, objNguoiDung.getSoDu() - objSaoKe.getSoTien()); 
            
            //Thực hiện cập nhật số dư người dùng
            comm2.executeUpdate();
            
            //Thực hiện công việc thêm giao dịch vào bảng LichSuGiaoDich
            return comm1.executeUpdate() > 0;
            
            
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
     * Hàm thực hiện giao dịch rút tiền, cập nhật số dư người dùng
     * @param objSaoKe
     * @param stk
     * @return true nếu thêm giao dịch thành công, false nếu thất bại
     */
    public boolean rutTien(SaoKe objSaoKe, String stk){
        //Khởi tạo kết nối
        Connection conn = null;
        
        //Lấy thông tin người dùng theo số tài khoản truyền vào
        NguoiDung objNguoiDung = DataProvider.getNguoiDungBus().layChiTietNguoiDung(stk);
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();

            String strRutTien = "Insert into LichSuGiaoDich(MaGiaoDich, SoTaiKhoan, "
            + "ThoiGianGiaoDich, GiaoDich, SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            //Tạo công việc thêm giao dịch rút tiền vào bảng LichSuGiaoDich
            PreparedStatement comm1 = conn.prepareStatement(strRutTien);

            comm1.setInt(1, layMaGiaoDich());
            comm1.setString(2, stk);
            comm1.setObject(3, thoiGianGiaoDich());
            comm1.setString(4, "-");
            comm1.setDouble(5, objSaoKe.getSoTien());
            comm1.setDouble(6, (objNguoiDung.getSoDu() - objSaoKe.getSoTien()));
            comm1.setString(7, null);
            comm1.setString(8, null);
            comm1.setString(9, objSaoKe.getNoiDung());

            String strSoDu = "Update NguoiDung set SoDu = ? where SoTaiKhoan = '" + stk + "'";

            //Tạo công việc cập nhật số dư người dùng
            PreparedStatement comm2 = conn.prepareStatement(strSoDu);

            comm2.setDouble(1, objNguoiDung.getSoDu() - objSaoKe.getSoTien());

            //Thực hiện công việc cập nhật số dư người dùng
            comm2.executeUpdate();
            
            //Thực hiện công việc thêm giao dịch rút tiền
            return comm1.executeUpdate() > 0;
            
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
     * Hàm thực hiện giao dịch nạp tiền và cập nhật số dư người dùng
     * @param objSaoKe
     * @param stk
     * @return true nếu thêm giao dịch thành công, false nếu thất bại
     */
    public boolean napTien(SaoKe objSaoKe, String stk){
        //Khởi tạo kết nối
        Connection conn = null;
        
        //Khởi tạo đối tượng theo thông tin số tài khoản truyền vào
        NguoiDung objNguoiDung = DataProvider.getNguoiDungBus().layChiTietNguoiDung(stk);
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String strRutTien = "Insert into LichSuGiaoDich(MaGiaoDich, SoTaiKhoan, "
            + "ThoiGianGiaoDich, GiaoDich, SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            //Tạo công việc thêm giao dịch rút tiền vào bảng LichSuGiaoDich
            PreparedStatement comm1 = conn.prepareStatement(strRutTien);
            
            comm1.setInt(1, layMaGiaoDich());
            comm1.setString(2, stk);
            comm1.setObject(3, thoiGianGiaoDich());
            comm1.setString(4, "+");
            comm1.setDouble(5, objSaoKe.getSoTien());
            comm1.setDouble(6, (objNguoiDung.getSoDu() + objSaoKe.getSoTien()));
            comm1.setString(7, null);
            comm1.setString(8, null);
            comm1.setString(9, objSaoKe.getNoiDung());
            
            String strSoDu = "Update NguoiDung set SoDu = ? where SoTaiKhoan = '" + stk + "'";
            
            //Tạo công việc cập nhật số dư người dùng
            PreparedStatement comm2 = conn.prepareStatement(strSoDu);
            
            comm2.setDouble(1, (objNguoiDung.getSoDu() + objSaoKe.getSoTien()));
            
            //Thực hiện cập nhật số dư người dùng
            comm2.executeUpdate();
            
            //Thực hiện công việc nạp tiền
            return comm1.executeUpdate() > 0;
            
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
     * Hàm in biên lai theo đường dẫn chọn trong fileChooser
     * @param duongDan
     * @param noiDung 
     */
    public static void xuatSaoKe(String duongDan, String noiDung)
    {
        try {
            FileWriter writer = new FileWriter(duongDan + ".txt");
            writer.write(noiDung);
            writer.flush();
            writer.close();
                
        } catch (IOException ex) {
            System.err.println("Lỗi trong quá trình viết file" + ex.getMessage());
        }
    }
    
    /**
     * Hàm in sao kê giao dịch vào file csv theo đường dẫn chọn trong FileChooser
     * @param duongDan
     * @param lstGiaoDich
     * @param stk
     * @return 
     */
    public static boolean inSaoKe(String duongDan, List<SaoKe> lstGiaoDich, String stk){
        //Lấy danh sách các giao dịch, tất cả hoặc theo số tài khoản được truyền vào
        lstGiaoDich = docGiaoDich(stk);
        
        String[] tieuDe = new String[]{"Mã giao dịch", "Thời gian giao dịch", "Giao dịch",
                "Số tiền", "Số dư", "Người thụ hưởng", "Số tài khoản thụ hưởng", "Nội dung"};
        
        try {   
            FileWriter writer = new FileWriter(duongDan + ".csv");

            //In dòng tiêu đề
            for(int i = 0; i < tieuDe.length; i++)
            {
               writer.write(tieuDe[i] + ",");
            }
            writer.write("\n");

            //In thông tin giao dịch
            for(SaoKe sk : lstGiaoDich){
                writer.write(sk.getMaGiaoDich() + ",");
                writer.write(sk.getThoiGianGiaoDich() + ",");
                writer.write(sk.getGiaoDich() + ",");
                writer.write(sk.getSoTien() + ",");
                writer.write(sk.getSoDu() + ",");
                writer.write(sk.getNguoiThuHuong() + ",");
                writer.write(sk.getSoTaiKhoanThuHuong() + ",");
                writer.write(sk.getNoiDung() + ",");
                writer.write("\n");
                }
            
            writer.flush();
            writer.close();
            return true;
            } catch (IOException ex) {
            Logger.getLogger(GiaoDich.class.getName()).log(Level.SEVERE, null, ex);
            } 
        return false;
    }
    
    /**
     * Hàm lấy thời gian hiện tại để gán chho thời gian giao dịch
     * @return 
     */
    private static LocalDateTime thoiGianGiaoDich(){
        return LocalDateTime.now();
    }
    
    /**
     * Hàm lấy mã giao dịch
     * @return 
     */
    private static int layMaGiaoDich(){
        //Khởi tạo kết nối
        Connection conn = null;
        
        //Khai báo danh sách để thêm các giao dịch
        List<SaoKe> lstGiaoDich = new ArrayList();
        
        try {
            //Thực hiện kết nối
            conn = DataProvider.ketNoi();
            
            String strGiaoDich = "Select MaGiaoDich, ThoiGianGiaoDich, GiaoDich, "
                    + "SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung from LichSuGiaoDich";
            
            //Tạo công việc
            Statement comm = conn.createStatement();
            
            //Thực hiện công việc
            ResultSet rs = comm.executeQuery(strGiaoDich);
            
            //Khai báo đối tượng để lấy thông tin
            SaoKe objSaoKe;
            
            while(rs.next()){
                objSaoKe = new SaoKe();
                objSaoKe.setMaGiaoDich(rs.getInt("MaGiaoDich"));
                objSaoKe.setThoiGianGiaoDich(rs.getTimestamp("ThoiGianGiaoDich"));
                objSaoKe.setGiaoDich(rs.getString("GiaoDich"));
                objSaoKe.setSoTien(rs.getDouble("SoTien"));
                objSaoKe.setSoDu(rs.getDouble("SoDu"));
                objSaoKe.setNguoiThuHuong(rs.getString("NguoiThuHuong"));
                objSaoKe.setSoTaiKhoanThuHuong(rs.getString("SoTaiKhoanThuHuong"));
                objSaoKe.setNoiDung(rs.getString("NoiDung"));
                
                lstGiaoDich.add(objSaoKe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaoKe.class.getName()).log(Level.SEVERE, null, ex);
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
        
        //Khai báo array để lấy giá trị int của mã giao dịch
        int[] arrGiaoDich = new int[lstGiaoDich.size()];
        
        //Thêm các mã giao dịch vào array
        for(int i = 0; i <= lstGiaoDich.size()-1; i++){
            arrGiaoDich[i] = lstGiaoDich.get(i).getMaGiaoDich();
        }
        
        //Lấy mã giao dịch gần nhất và cộng thêm 1 để lấy mã giao dịch mới
        int maGiaoDich = arrGiaoDich[arrGiaoDich.length - 1] + 1;
        
        return maGiaoDich;
    }
}
