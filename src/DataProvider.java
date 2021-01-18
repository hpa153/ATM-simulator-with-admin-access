
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
     * Method to connect with database
     * @return connection
     */
    public static Connection ketNoi(){
        Connection conn = null;
        
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            
            conn = DriverManager.getConnection(DATABASE_LINK, "root", "Gerrardliver_08");
        } catch (ClassNotFoundException ex) {
            System.err.println("Could not find driver. Details: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Could not connect to database on mySQL. Details: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println("Driver load error. Details: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println("Driver load error. Details: " + ex.getMessage());
        }
        return conn;
    }
    
    //Initialize object of class NguoiDungBusiness
    private static NguoiDungBusiness _nguoiDungBus = null;
    
    /**
     * Method of object of class NguoiDungBusiness
     * to call and use at multiple places
     * @return object of class NguoiDungBusiness
     */
    public static NguoiDungBusiness getNguoiDungBus()
    {
        if(_nguoiDungBus == null)
        {
            _nguoiDungBus = new NguoiDungBusiness();
        }
        return _nguoiDungBus;
    }
    
    //Initialize object of class QuanLyBusiness
    private static QuanLyBusiness _quanLyBus = null;
    
    /**
     * Method of object of class QuanLyBusiness
     * to call and use at multiple places
     * @return object of class QuanLyBusiness
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
     * Method of object of class GiaoDich
     * to call and use at multiple places
     * @return object of class GiaoDich
     */
    public static GiaoDich getGiaoDichBus()
    {
        if(_giaoDichBus == null)
        {
            _giaoDichBus = new GiaoDich();
        }
        return _giaoDichBus;
    }
    
    //Initialize object of class TaiKhoanBusiness
    private static TaiKhoanBusiness _TaiKhoanBus = null;
    
    /**
     * Method of object of class TaiKhoanBusiness
     * to call and use at multiple places
     * @return object of class TaiKhoanBusiness
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
