package gui;

import dao.StudentCollectionsDAO;
import dao.StudentDAO;
import domain.Student;
import gui.helpers.SimpleListModel;
import javax.swing.JOptionPane;

public class StudentReportDialog extends javax.swing.JDialog {

   private StudentDAO dao = new StudentCollectionsDAO();
   
   private SimpleListModel studentsModel = new SimpleListModel(dao.getAll());

   public StudentReportDialog(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
      initComponents();
      
      lstStudents.setModel(studentsModel);
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      scrollPane = new javax.swing.JScrollPane();
      lstStudents = new javax.swing.JList();
      btnClose = new javax.swing.JButton();
      btnDelete = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      scrollPane.setViewportView(lstStudents);

      btnClose.setText("Close");
      btnClose.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCloseActionPerformed(evt);
         }
      });

      btnDelete.setText("Delete");
      btnDelete.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(18, 18, 18)
                  .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnClose)
               .addComponent(btnDelete))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
      this.dispose();
   }//GEN-LAST:event_btnCloseActionPerformed

   private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
      if (!lstStudents.isSelectionEmpty()){
         int result = JOptionPane.showConfirmDialog(this,
                           "Are you sure you want to delete this student?");
         if (result == JOptionPane.YES_OPTION){
            dao.delete((Student)lstStudents.getSelectedValue());
            studentsModel.updateItems(dao.getAll());
         } 
      }
   }//GEN-LAST:event_btnDeleteActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnClose;
   private javax.swing.JButton btnDelete;
   private javax.swing.JList lstStudents;
   private javax.swing.JScrollPane scrollPane;
   // End of variables declaration//GEN-END:variables
}
