
import java.awt.font.TextHitInfo;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Phuong Anh Hoang
 */
public class frmLichSuGiaoDich extends javax.swing.JFrame {

    private String stk = "";

    public String getStk() {
        return stk;
    }

    public void setStk(String stk) {
        this.stk = stk;
    }
    /**
     * Creates new form frmLichSuGiaoDich
     */
    public frmLichSuGiaoDich() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGiaoDich = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnIn = new javax.swing.JButton();
        btnDong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        jDateChooserTu = new com.toedter.calendar.JDateChooser();
        jDateChooserDen = new com.toedter.calendar.JDateChooser();
        btnXacLap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTableGiaoDich.setBackground(new java.awt.Color(255, 240, 240));
        jTableGiaoDich.setForeground(new java.awt.Color(51, 0, 102));
        jTableGiaoDich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableGiaoDich);

        jPanel1.setBackground(new java.awt.Color(255, 240, 240));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 102), 3));

        btnIn.setBackground(new java.awt.Color(255, 200, 210));
        btnIn.setForeground(new java.awt.Color(51, 0, 102));
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print icon.png"))); // NOI18N
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnDong.setBackground(new java.awt.Color(255, 200, 210));
        btnDong.setForeground(new java.awt.Color(51, 0, 102));
        btnDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cancel icon.png"))); // NOI18N
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDong)
                    .addComponent(btnIn))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 240, 240));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 102), 3));

        jLabel1.setBackground(new java.awt.Color(255, 240, 240));
        jLabel1.setForeground(new java.awt.Color(51, 0, 102));
        jLabel1.setText("Lọc giao dịch theo thời gian từ:");

        jLabel2.setBackground(new java.awt.Color(255, 240, 240));
        jLabel2.setForeground(new java.awt.Color(51, 0, 102));
        jLabel2.setText("đến:");

        btnLoc.setBackground(new java.awt.Color(255, 200, 210));
        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search icon.png"))); // NOI18N
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnXacLap.setBackground(new java.awt.Color(255, 200, 210));
        btnXacLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Reset.png"))); // NOI18N
        btnXacLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacLapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooserDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXacLap)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXacLap)
                    .addComponent(jDateChooserDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(stk.isEmpty()){
            this.setTitle("Lịch sử giao dịch");
        }else{
            this.setTitle("Lịch sử giao dịch của tài khoản " + stk);
        }
        
        hienThiLichSuGiaoDich();
    }//GEN-LAST:event_formWindowOpened

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        List<SaoKe> lstGiaoDich = DataProvider.getGiaoDichBus().docGiaoDich(stk);
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Lưu lịch sử giao dịch");
        
        boolean ketQua = false;
        
        if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            String duongDan = chooser.getSelectedFile().getAbsolutePath();
            ketQua = DataProvider.getGiaoDichBus().inSaoKe(duongDan, lstGiaoDich, stk);
        }
        
        if(ketQua){
            JOptionPane.showMessageDialog(rootPane, "In hóa đơn thành công");
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnDongActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        Date batDau = (Date) jDateChooserTu.getDate();
        Date temp = (Date) jDateChooserDen.getDate();
        Date ketThuc = new Date(temp.getTime() + (1000 * 60 * 60 * 24));

        List<SaoKe> lstTatCa = DataProvider.getGiaoDichBus().docGiaoDich(stk);
        
        String[] tieuDe = new String[]{"Mã giao dịch", "Thời gian giao dịch", "Giao dịch",
                "Số tiền", "Số dư", "Người thụ hưởng", "Số tài khoản thụ hưởng", "Nội dung"};
        
        DefaultTableModel model = new DefaultTableModel(tieuDe, 0);
        
        List<SaoKe> lstGiaoDich = new ArrayList();
        
        lstTatCa.stream().filter((sk) -> (!sk.getThoiGianGiaoDich().before(batDau) && !sk.getThoiGianGiaoDich().after(ketThuc))).forEachOrdered((sk) -> {
            lstGiaoDich.add(sk);
        });
        
        Object row[];
        
        for(SaoKe sk : lstGiaoDich){
            row = new Object[8];
            row[0] = sk.getMaGiaoDich();
            row[1] = sk.getThoiGianGiaoDich();
            row[2] = sk.getGiaoDich();
            row[3] = sk.getSoTien();
            row[4] = sk.getSoDu();
            row[5] = sk.getNguoiThuHuong();
            row[6] = sk.getSoTaiKhoanThuHuong();
            row[7] = sk.getNoiDung();
            
            model.addRow(row);
        }
        jTableGiaoDich.setModel(model);
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnXacLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacLapActionPerformed
        hienThiLichSuGiaoDich();
    }//GEN-LAST:event_btnXacLapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmLichSuGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLichSuGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLichSuGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLichSuGiaoDich.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLichSuGiaoDich().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnXacLap;
    private com.toedter.calendar.JDateChooser jDateChooserDen;
    private com.toedter.calendar.JDateChooser jDateChooserTu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGiaoDich;
    // End of variables declaration//GEN-END:variables

    private void hienThiLichSuGiaoDich() {
        String[] tieuDe = new String[]{"Mã giao dịch", "Thời gian giao dịch", "Giao dịch",
                "Số tiền", "Số dư", "Người thụ hưởng", "Số tài khoản thụ hưởng", "Nội dung"};
        
        List<SaoKe> lstGiaoDich = DataProvider.getGiaoDichBus().docGiaoDich(stk);
        
        
        DefaultTableModel model = new DefaultTableModel(tieuDe, 0);
        
        Object row[];
        
        for(SaoKe sk : lstGiaoDich){
            row = new Object[8];
            row[0] = sk.getMaGiaoDich();
            row[1] = sk.getThoiGianGiaoDich();
            row[2] = sk.getGiaoDich();
            row[3] = sk.getSoTien();
            row[4] = sk.getSoDu();
            row[5] = sk.getNguoiThuHuong();
            row[6] = sk.getSoTaiKhoanThuHuong();
            row[7] = sk.getNoiDung();
            
            model.addRow(row);
        }
        
        jTableGiaoDich.setModel(model);
    }
}