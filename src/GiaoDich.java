
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
     * Method to get list of transactions and search for transactions based on account number
     * @param stk 
     * @return list of transactions or transactions based on the transmitted account number
     */
    public static List<SaoKe> docGiaoDich(String stk) {
        //Initialize database connection
        Connection conn = null;
        
        //Create transaction list
        List<SaoKe> lstGiaoDich = new ArrayList();
        
        try {
            
            String strGiaoDich = "Select MaGiaoDich, ThoiGianGiaoDich, GiaoDich, "
                    + "SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung from LichSuGiaoDich where 1=1 ";
            
            //if the keyword is transmitted -> get list based on keyword
            if(!stk.isEmpty()){
                strGiaoDich += "AND SoTaiKhoan = '" + stk + "'";
            }
            
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            Statement comm = conn.createStatement();
            
            //Execute statement
            ResultSet rs = comm.executeQuery(strGiaoDich);
            
            //Create object of class SaoKe
            SaoKe objSaoKe;
            
            //While the list has not ended, assign the information to the object and add the object to the list
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
        
        //Close connection
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
     * Method for money transfer, account balance update
     * @param objSaoKe
     * @param stk
     * @return true if the transfer was successful, false if failed
     */
    public boolean chuyenTien(SaoKe objSaoKe, String stk){
        //Initialize database connection
        Connection conn = null;
        
        //Create object and assign user info according to account number
        NguoiDung objNguoiDung = DataProvider.getNguoiDungBus().layChiTietNguoiDung(stk);
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement for money transfer
            String strThem = "Insert into LichSuGiaoDich(MaGiaoDich, SoTaiKhoan, "
            + "ThoiGianGiaoDich, GiaoDich, SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            //Create statement to update account balance
            String strCapNhatSoDu = "Update NguoiDung set SoDu = ? where SoTaiKhoan = '" + stk + "'";

            PreparedStatement comm2 = conn.prepareStatement(strCapNhatSoDu);

            comm2.setDouble(1, objNguoiDung.getSoDu() - objSaoKe.getSoTien()); 
            
            //Execute account balance update statement
            comm2.executeUpdate();
            
            //Execute transaction statement and add it to LichSuGiaoDich table
            return comm1.executeUpdate() > 0;
            
            
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
     * Method for money withdraw and account balance update
     * @param objSaoKe
     * @param stk
     * @return true if the transfer was successful, false if failed
     */
    public boolean rutTien(SaoKe objSaoKe, String stk){
        //Initialize database connection
        Connection conn = null;
        
        //Create object and assign user info according to transmitted account number
        NguoiDung objNguoiDung = DataProvider.getNguoiDungBus().layChiTietNguoiDung(stk);
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();

            //Create statement for money withrawal and add it to LichSuGiaoDich table
            String strRutTien = "Insert into LichSuGiaoDich(MaGiaoDich, SoTaiKhoan, "
            + "ThoiGianGiaoDich, GiaoDich, SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            //Create statement to update account balance
            String strSoDu = "Update NguoiDung set SoDu = ? where SoTaiKhoan = '" + stk + "'";

            PreparedStatement comm2 = conn.prepareStatement(strSoDu);

            comm2.setDouble(1, objNguoiDung.getSoDu() - objSaoKe.getSoTien());

            //Execute account balance update statement
            comm2.executeUpdate();
            
            //Execute transaction statement and add it to LichSuGiaoDich table
            return comm1.executeUpdate() > 0;
            
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
     * Method to add money and account balance update
     * @param objSaoKe
     * @param stk
     * @return true if the addition was successful, false if failed
     */
    public boolean napTien(SaoKe objSaoKe, String stk){
        //Initialize database connection
        Connection conn = null;
        
        //Create object and assign user info according to transmitted account number
        NguoiDung objNguoiDung = DataProvider.getNguoiDungBus().layChiTietNguoiDung(stk);
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement to add money and add transaction to LichSuGiaoDich table
            String strRutTien = "Insert into LichSuGiaoDich(MaGiaoDich, SoTaiKhoan, "
            + "ThoiGianGiaoDich, GiaoDich, SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
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
            
            //Create statement to update account balance
            String strSoDu = "Update NguoiDung set SoDu = ? where SoTaiKhoan = '" + stk + "'";
            
            PreparedStatement comm2 = conn.prepareStatement(strSoDu);
            
            comm2.setDouble(1, (objNguoiDung.getSoDu() + objSaoKe.getSoTien()));
            
            //Execute account balance update statement
            comm2.executeUpdate();
            
            //Execute money add statement
            return comm1.executeUpdate() > 0;
            
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
     * Method to print receipt according to the path in the fileChooser
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
            System.err.println("Error while writing file: " + ex.getMessage());
        }
    }
    
    /**
     * Method to print transaction into a .csv file according to the path in the FileChooser
     * @param duongDan
     * @param lstGiaoDich
     * @param stk
     * @return true if printed, false if failed
     */
    public static boolean inSaoKe(String duongDan, List<SaoKe> lstGiaoDich, String stk){
        //Get transaction list, all or of transmitted account number
        lstGiaoDich = docGiaoDich(stk);
        
        String[] tieuDe = new String[]{"Mã giao dịch", "Thời gian giao dịch", "Giao dịch",
                "Số tiền", "Số dư", "Người thụ hưởng", "Số tài khoản thụ hưởng", "Nội dung"};
        
        try {   
            FileWriter writer = new FileWriter(duongDan + ".csv");

            //Print header line
            for(int i = 0; i < tieuDe.length; i++)
            {
               writer.write(tieuDe[i] + ",");
            }
            writer.write("\n");

            //Print transaction info
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
     * Method to get transaction time
     * @return transaction time
     */
    private static LocalDateTime thoiGianGiaoDich(){
        return LocalDateTime.now();
    }
    
    /**
     * Method to get transaction id
     * @return transaction id
     */
    private static int layMaGiaoDich(){
        //Initiale database connection
        Connection conn = null;
        
        //Create lis of transactions
        List<SaoKe> lstGiaoDich = new ArrayList();
        
        try {
            //Connect to database
            conn = DataProvider.ketNoi();
            
            //Create statement
            String strGiaoDich = "Select MaGiaoDich, ThoiGianGiaoDich, GiaoDich, "
                    + "SoTien, SoDu, NguoiThuHuong, SoTaiKhoanThuHuong, NoiDung from LichSuGiaoDich";
            
            Statement comm = conn.createStatement();
            
            //Execute statement
            ResultSet rs = comm.executeQuery(strGiaoDich);
            
            //Create object of class SaoKe to add info
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
        
        //Close connection
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungBusiness.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Create array to get values from the transaction list
        int[] arrGiaoDich = new int[lstGiaoDich.size()];
        
        //Add all transaction ids to the array
        for(int i = 0; i <= lstGiaoDich.size()-1; i++){
            arrGiaoDich[i] = lstGiaoDich.get(i).getMaGiaoDich();
        }
        
        //Get most recent transaction id and add 1 to get the new transaction id
        int maGiaoDich = arrGiaoDich[arrGiaoDich.length - 1] + 1;
        
        return maGiaoDich;
    }
}
