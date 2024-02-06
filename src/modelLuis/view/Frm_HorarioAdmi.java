package modelLuis.view;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import modelLuis.controller.ControllerHorario;
import modelLuis.tablas.ModelTableHorario;
import modelLuis.view.util.Util_VistaLinked1_Asistencia;

public class Frm_HorarioAdmi extends javax.swing.JFrame {

    ControllerHorario a = new ControllerHorario();
    private ModelTableHorario mt1 = new ModelTableHorario();

    public Frm_HorarioAdmi() {
        initComponents();
        limpiar();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void limpiar() {
        cargarTabla();
        try {
            Util_VistaLinked1_Asistencia.cargaMaterias(cbxAsistencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarTabla() {
        mt1.setHorarios(a.list_All());
        tblM.setModel(mt1);
        tblM.updateUI();
    }

    private void guardarHorario() {

        try {
            a.getAsistencia().setDia(cbxDia.getSelectedItem().toString());
            a.getAsistencia().setHoraFin(cbxHoraFin.getSelectedItem().toString());
            a.getAsistencia().setHoraInicio(cbxHoraInicio.getSelectedItem().toString());
            a.getAsistencia().setIdAsignatura(Util_VistaLinked1_Asistencia.getComboAsignatura(cbxAsistencia).getId());
            a.getAsistencia().setId(a.generatedId());
            if (a.saved()) {
                JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e + "Error");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblM = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxAsistencia = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        cbxDia = new javax.swing.JComboBox<>();
        cbxHoraInicio = new javax.swing.JComboBox<>();
        cbxHoraFin = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        menu_Admin2 = new plantilla.components.Menu_Admin();
        header1 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

<<<<<<< HEAD
        jLabel5.setText("Hora Inicio:");

        jLabel6.setText("Hora final:");

        jLabel7.setText("Dia:");

        cbxAsistencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton6.setText("Guardar Horario");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" }));

        cbxHoraInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:30", "09:30", "10:30", "11:30" }));

        cbxHoraFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:30", "10:30", "11:30", "13:30" }));

        jLabel1.setText("Materia:");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(cbxHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                        .addComponent(cbxAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cbxDia, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addContainerGap())))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jButton6)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        roundPanel1.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 580, 200));

=======
>>>>>>> dd1d271620e78c2f09e43f1f257bdf3f98ee4dd9
        tblM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblM);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 920, 230));

<<<<<<< HEAD
<<<<<<< HEAD
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1040, 620));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
=======
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 1010, 620));
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 620));
>>>>>>> master
=======
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administración de Horarios");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
>>>>>>> dd1d271620e78c2f09e43f1f257bdf3f98ee4dd9

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hora Inicio:");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hora final:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dia:");
        roundPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        cbxAsistencia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxAsistencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxAsistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, 187, -1));

        jButton6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton6.setText("Guardar Horario");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 156, -1));

        cbxDia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" }));
        roundPanel1.add(cbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 187, -1));

        cbxHoraInicio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxHoraInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:30", "09:30", "10:30", "11:30" }));
        roundPanel1.add(cbxHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 211, -1));

        cbxHoraFin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxHoraFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:30", "10:30", "11:30", "13:30" }));
        roundPanel1.add(cbxHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 211, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Materia:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, -1, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));
        bg_panel.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        guardarHorario();
    }//GEN-LAST:event_jButton6ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_HorarioAdmi().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JComboBox<String> cbxAsistencia;
    private javax.swing.JComboBox<String> cbxDia;
    private javax.swing.JComboBox<String> cbxHoraFin;
    private javax.swing.JComboBox<String> cbxHoraInicio;
    private plantilla.components.Header header1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin2;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblM;
    // End of variables declaration//GEN-END:variables

}
