
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phuong Anh Hoang
 */
public class test {
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            if(!DataProvider.ketNoi().isClosed())
            {
                System.out.println("Kết nối đến MySQL thành công");
            }
        } catch (SQLException ex) {
            System.err.println("Lỗi kết nối: " + ex.getMessage());
        }
        
        LocalDateTime thoiGian = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        String formatDateTime = thoiGian.format(format); 
        System.out.println(formatDateTime);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(thoiGian));
    } 
}
