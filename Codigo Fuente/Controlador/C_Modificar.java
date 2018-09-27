private void C_ModificarActionPerformed(java.awt.event.ActionEvent evt) {                                            
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
