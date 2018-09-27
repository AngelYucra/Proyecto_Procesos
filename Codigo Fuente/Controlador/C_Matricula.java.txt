private void C_MatriculaActionPerformed(java.awt.event.ActionEvent evt) {                                            
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
       String insertar="INSERT INTO datos(id,nombre,apellido_materno,apellido_paterno,E_mail,genero,"
               + "fecha_nacimiento,foto,ruta)VALUES(?,?,?,?,?,?,?,?,?)";
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
