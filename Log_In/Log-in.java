public class Login_Estudiante extends javax.swing.JFrame {

   private void V_IniciarActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
        String user = jTextField1.getText();
        String pass = new String(jPasswordField1.getPassword());
        
       if(user.equals("Admin") && pass.equals("Anthony"))
       {
            new Pag_PrincipalClinica().setVisible(true);
            this.setVisible(false);
       }else{
             JOptionPane.showMessageDialog(null, "Error!!, Password o Usuario Incorrecto.");
       }
        
        
    }
} 
