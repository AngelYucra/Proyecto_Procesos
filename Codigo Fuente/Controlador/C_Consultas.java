private void C_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        Connection conectar= null;
   
       try{
           Class.forName("com.mysql.jdbc.Driver");
           conectar = DriverManager.getConnection("jdbc:mysql://localhost/colegio","root","");
        }catch(ClassNotFoundException ex){
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
        }catch(SQLException ex){
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE,null,ex);
        }
       
       String id=this.jConsulta.getText();
       try{
           Statement s =conectar.createStatement();
           ResultSet rs =s.executeQuery("SELECT id, nombre, apellido_materno,apellido_paterno, E_mail, genero, fecha_nacimiento, foto, ruta FROM datos WHERE id='"+id+"'");
           if(rs.next()){
               jIdentificacion.setText(rs.getString(1));
               jNombre.setText(rs.getString(2));
               jApelliMaterno.setText(rs.getString(3));
               jApelliPaterno.setText(rs.getString(4));
               jEmail.setText(rs.getString(5));
               
               String generof="Femenino";
               String generom="Masculino";
               String gen=rs.getString("genero");
               if(generof.equals(gen)){
                   jFemenino.setSelected(true);
               }
               if(generom.equals(gen)){
                   jMasculino.setSelected(true);
               }
               
               jDateChooser1.setDate(rs.getDate(7));
               
               Image i=null;
               Blob blob=rs.getBlob("foto");
               i=javax.imageio.ImageIO.read(blob.getBinaryStream());
               ImageIcon image=new ImageIcon(i);
               jImagen.setIcon(image);
               
               jruta_foto.setText(rs.getString(9));
               
           }else{
               JOptionPane.showMessageDialog(null,"El registro"+id+"no existe");
           }
           
       } catch (SQLException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(M_Datos_De_Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }                        
