private void C_EliminarActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
        int ax=JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar el registro? "+jIdentificacion.getText()+"?");
        if(ax==JOptionPane.YES_OPTION){
            
        Connection conectar = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost/colegio","root","");
         } catch (ClassNotFoundException ex){
             Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
         } catch(SQLException ex){
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
        }
        try{
        Statement s= conectar.createStatement();
        int i=s.executeUpdate("DELETE FROM datos WHERE id='"+jIdentificacion.getText()+"'");
        if(i==1){
            jIdentificacion.setText("");
            jNombre.setText("");
            jApelliMaterno.setText("");
            jApelliPaterno.setText("");
            jEmail.setText("");
            buttonGroup1.clearSelection();
            jDateChooser1.setDate(null);
            jImagen.setIcon(null);
            jruta_foto.setText ("");
        }
        if(i>0){
            JOptionPane.showMessageDialog(null,"El registro se ha eliminado con éxito");
        }else{
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error!!, el registro no se elimino");
        }
        } catch (SQLException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }               
