
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class DataProvider {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_LINK = "jdbc:mysql://localhost:3306/JavaClass";
    
    /**
     * Hàm tạo kết nối với database
     * @return 
     */
    public static Connection ketNoi(){
        Connection conn = null;
        
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            
            conn = DriverManager.getConnection(DATABASE_LINK, "root", "Gerrardliver_08");
        } catch (ClassNotFoundException ex) {
            System.err.println("Lỗi không tìm thấy driver. Chi tiết: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Không kết nối được với database trên mySQL. Chi tiết: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println("Lỗi load driver. Chi tiết: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println("Lỗi load driver. Chi tiết: " + ex.getMessage());
        }
        return conn;
    }
    
    //Khởi tạo đối tượng thuộc lớp NguoiDungBusiness
    private static NguoiDungBusiness _nguoiDungBus = null;
    
    /**
     * Hàm về một đối tượng thuộc lớp NguoiDungBusiness
     * để có thể gọi, sử dụng nhiều nơi
     * @return 
     */
    public static NguoiDungBusiness getNguoiDungBus()
    {
        if(_nguoiDungBus == null)
        {
            _nguoiDungBus = new NguoiDungBusiness();
        }
        return _nguoiDungBus;
    }
    
    //Khởi tạo đối tượng thuộc lớp QuanLyBusiness
    private static QuanLyBusiness _quanLyBus = null;
    
    /**
     * Hàm về một đối tượng thuộc lớp QuanLyBusiness
     * để có thể gọi, sử dụng nhiều nơi
     * @return 
     */
    public static QuanLyBusiness getQuanLyBus()
    {
        if(_quanLyBus == null)
        {
            _quanLyBus = new QuanLyBusiness();
        }
        return _quanLyBus;
    }
    
    //Khởi tạo đối tượng thuộc lớp GiaoDich
    private static GiaoDich _giaoDichBus = null;
    
    /**
     * Hàm về một đối tượng thuộc lớp GiaoDich
     * để có thể gọi, sử dụng nhiều nơi
     * @return 
     */
    public static GiaoDich getGiaoDichBus()
    {
        if(_giaoDichBus == null)
        {
            _giaoDichBus = new GiaoDich();
        }
        return _giaoDichBus;
    }
    
    //Khởi tạo đối tượng thuộc lớp TaiKhoanBusiness
    private static TaiKhoanBusiness _TaiKhoanBus = null;
    
    /**
     * Hàm về một đối tượng thuộc lớp TaiKhoanBusiness
     * để có thể gọi, sử dụng nhiều nơi
     * @return 
     */
    public static TaiKhoanBusiness getTaiKhoanBus()
    {
        if(_TaiKhoanBus == null)
        {
            _TaiKhoanBus = new TaiKhoanBusiness();
        }
        return _TaiKhoanBus;
    }
}
