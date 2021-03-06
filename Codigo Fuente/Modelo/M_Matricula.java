private void M_MatriculaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
      try{
       Connection conectar= null;
   
       try{
           Class.forName("com.mysql.jdbc.Driver");
           conectar = DriverManager.getConnection("jdbc:mysql://localhost/colegio","root","");
        }catch(ClassNotFoundException ex){
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
        }catch(SQLException ex){
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
        }
       String insertar="INSERT INTO datos(id,nombre,apellido_materno,apellido_paterno,E_mail,genero,fecha_nacimiento,foto,ruta)VALUES(?,?,?,?,?,?,?,?,?)";
       PreparedStatement pst=conectar.prepareStatement(insertar);
       pst.setString(1,jIdentificacion.getText());
       pst.setString(2,jNombre.getText());
       pst.setString(3,jApelliMaterno.getText());
       pst.setString(4,jApelliPaterno.getText());
       pst.setString(5,jEmail.getText());
       pst.setString(6,genero);
       
       String fechaString=null;
       Date fecha = jDateChooser1.getDate();
       SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
       fechaString=sdf.format(fecha);
       pst.setString(7,fechaString);
       
       FileInputStream archivoFoto;
       archivoFoto = new FileInputStream(jruta_foto.getText());
       pst.setBinaryStream(8,archivoFoto);
       
       pst.setString(9, jruta_foto.getText());
       int i=pst.executeUpdate();
       if(i>0){
           JOptionPane.showMessageDialog(null,"Alumno registrado correctamente");
           
       }else{
           JOptionPane.showMessageDialog(null,"Ha ocurrido un error!, no se ha podido registrar");
       }
      
       }catch(SQLException ex){
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
       } catch (FileNotFoundException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        

// controles de click

    private void jMasculinoMouseClicked(java.awt.event.MouseEvent evt) {                                        
        
        genero = jMasculino.getLabel();
        
    }                                       

    private void jFemeninoMouseClicked(java.awt.event.MouseEvent evt) {                                       
        genero = jFemenino.getLabel();
    }                                      

    private void jImagenMouseClicked(java.awt.event.MouseEvent evt) {                                     
        
        JFileChooser archivo=new JFileChooser();
        int ventana=archivo.showOpenDialog(null);
        if(ventana==JFileChooser.APPROVE_OPTION){
            File file=archivo.getSelectedFile();
            jruta_foto.setText(String.valueOf(file));
            Image foto=getToolkit().getImage(jruta_foto.getText());
            jImagen.setIcon(new ImageIcon(foto));
        }
        
    }         

  private void M_EliminarActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
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

  private void M_GenerarConsultaActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        //NUevo
       jIdentificacion.setText("");
       jNombre.setText("");
       jApelliMaterno.setText("");
       jApelliPaterno.setText("");
       jEmail.setText("");
       buttonGroup1.clearSelection();//boton seleccion
       jDateChooser1.setDate(null);// fecha
       jImagen.setIcon(null);//imagen
       jruta_foto.setText("");//ruta
          
     
    }      

  private void M_ModificarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        //Modificar
        Connection conectar=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/colegio","root","");
            
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    try{
        String sql = "UPDATE datos SET id=?,"+"nombre=?,"
                +"apellido_materno=?,"
                +"apellido_paterno=?,"
                +"E_mail=?,"
                +"genero=?,"
                +"fecha_nacimiento=?,"
                +"foto=?,"
                +"ruta=?"
                +"WHERE id='"+ jIdentificacion.getText()+ "'";
        PreparedStatement pst= conectar.prepareStatement(sql);
        pst.setString(1,jIdentificacion.getText());
        pst.setString(2,jNombre.getText());
        pst.setString(3,jApelliMaterno.getText());
        pst.setString(4,jApelliPaterno.getText());
        pst.setString(5,jEmail.getText());
        pst.setString(6,genero);
        
        String fechaString = null;
        Date fecha=jDateChooser1.getDate();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        fechaString=sdf.format(fecha);
        pst.setString(7,fechaString);
        
        FileInputStream archivoFoto;
        archivoFoto=new FileInputStream(jruta_foto.getText());
        pst.setBinaryStream(8,archivoFoto);
        
        pst.setString(9,jruta_foto.getText());
        
        int n=pst.executeUpdate();
        if(n>0){
            JOptionPane.showMessageDialog(null,"Los datos se han modificado con éxito");
        }else{
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error!!, Los datos no se han modificado");
        }
        
    }catch(SQLException ex){
        Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }                



