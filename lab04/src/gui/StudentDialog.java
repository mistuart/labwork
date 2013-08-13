package gui;

import dao.StudentDAO;
import dao.StudentJdbcDAO;
import domain.Student;
import gui.helpers.SimpleListModel;

public class StudentDialog extends javax.swing.JDialog {

   private StudentDAO dao;
   
   private Student student = new Student();

   public StudentDialog(java.awt.Window parent, boolean modal, StudentDAO dao) {
      super(parent);
      super.setModal(modal);
      this.dao = dao;
      initComponents();
      cmbMajor.setEditable(true);  
      SimpleListModel majorsModel = new SimpleListModel(dao.getMajors());
      cmbMajor.setModel(majorsModel);
   }  
   public StudentDialog(java.awt.Window parent, boolean modal, Student student, StudentDAO dao) {
      this(parent, modal, dao);
      this.student = student;
      txtId.setText(student.getId().toString());
      txtName.setText(student.getName());
      cmbMajor.setSelectedItem(student.getMajor());
      txtId.setEditable(false);
   }
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      txtName = new javax.swing.JTextField();
      btnCancel = new javax.swing.JButton();
      btnSave = new javax.swing.JButton();
      lblId = new javax.swing.JLabel();
      txtId = new javax.swing.JTextField();
      lblName = new javax.swing.JLabel();
      lblMajor = new javax.swing.JLabel();
      cmbMajor = new javax.swing.JComboBox();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      txtName.setName("txtName"); // NOI18N

      btnCancel.setText("Cancel");
      btnCancel.setName("btnCancel"); // NOI18N
      btnCancel.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCancelActionPerformed(evt);
         }
      });

      btnSave.setText("Save");
      btnSave.setName("btnSave"); // NOI18N
      btnSave.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSaveActionPerformed(evt);
         }
      });

      lblId.setText("ID:");
      lblId.setName("lblId"); // NOI18N

      txtId.setName("txtId"); // NOI18N

      lblName.setText("Name:");
      lblName.setName("lblName"); // NOI18N

      lblMajor.setText("Major:");
      lblMajor.setName("lblMajor"); // NOI18N

      cmbMajor.setName("cmbMajor"); // NOI18N
      cmbMajor.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbMajorActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(19, 19, 19)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(lblMajor, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(lblId, javax.swing.GroupLayout.Alignment.TRAILING))
                  .addGap(5, 5, 5)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                     .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                     .addComponent(cmbMajor, 0, 219, Short.MAX_VALUE)))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                  .addGap(24, 24, 24)
                  .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblName)
               .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblMajor)
               .addComponent(cmbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnSave)
               .addComponent(btnCancel))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
      this.dispose();
   }//GEN-LAST:event_btnCancelActionPerformed

   private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      student.setId(Integer.parseInt(txtId.getText()));
      student.setName(txtName.getText());
      student.setMajor((String) cmbMajor.getSelectedItem());

      dao.save(student);

      this.dispose();
   }//GEN-LAST:event_btnSaveActionPerformed

   private void cmbMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMajorActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_cmbMajorActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCancel;
   private javax.swing.JButton btnSave;
   private javax.swing.JComboBox cmbMajor;
   private javax.swing.JLabel lblId;
   private javax.swing.JLabel lblMajor;
   private javax.swing.JLabel lblName;
   private javax.swing.JTextField txtId;
   private javax.swing.JTextField txtName;
   // End of variables declaration//GEN-END:variables
}
