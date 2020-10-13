/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.usuario;
import util.MySQLConexion;
/**
 * 
 * @author Josue Emmanuel Medina Garcia
 */
public class UsuarioController {

    //listar usuarios
    public List<usuario> listaUsuarios() {
        List<usuario> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select id_usuario ,nombre_us, apellidos_us, fecha_nacimiento, dni_us, contrasena_us, correo_us, sexo_us, t.nombre_tipo, avatar\n" +
            "from usuario u inner join tipo_us t\n" +
            "on u.tipo_us = t.id_tipo_us;";
            //st.setString(1, cad);
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                usuario a = new usuario();
                a.setId_usuario(rs.getInt(1));
                a.setNombre_us(rs.getString(2));
                a.setApellidos_us(rs.getString(3));
                a.setFecha_nacimiento(rs.getString(4));
                a.setDni_us(rs.getString(5));
                a.setContrasena_us(rs.getString(6));
                a.setCorreo_us(rs.getString(7));
                a.setSexo_us(rs.getString(8));
                a.setNombre_tipo_us(rs.getString(9));
                a.setAvatar(rs.getString(10));
                lis.add(a);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

        return lis;
    }
    
    //listar usuarios por nombre
    public List<usuario> listaUsuarios(String nombre) {
        List<usuario> lis = new ArrayList<>();
        Connection conn = null;

        try {
            if(!nombre.equals("")) {
                conn = MySQLConexion.getConexion();
            String sql = "select id_usuario ,nombre_us, apellidos_us, fecha_nacimiento, dni_us, contrasena_us, correo_us, sexo_us, t.nombre_tipo, avatar\n" +
            "from usuario u inner join tipo_us t\n" +
            "on u.tipo_us = t.id_tipo_us where nombre_us like ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                usuario a = new usuario();
                a.setId_usuario(rs.getInt(1));
                a.setNombre_us(rs.getString(2));
                a.setApellidos_us(rs.getString(3));
                a.setFecha_nacimiento(rs.getString(4));
                a.setDni_us(rs.getString(5));
                a.setContrasena_us(rs.getString(6));
                a.setCorreo_us(rs.getString(7));
                a.setSexo_us(rs.getString(8));
                a.setNombre_tipo_us(rs.getString(9));
                a.setAvatar(rs.getString(10));
                lis.add(a);
            }
            }
            else {
                conn = MySQLConexion.getConexion();
            String sql = "select id_usuario ,nombre_us, apellidos_us, fecha_nacimiento, dni_us, contrasena_us, correo_us, sexo_us, t.nombre_tipo, avatar\n" +
            "from usuario u inner join tipo_us t\n" +
            "on u.tipo_us = t.id_tipo_us";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                usuario a = new usuario();
                a.setId_usuario(rs.getInt(1));
                a.setNombre_us(rs.getString(2));
                a.setApellidos_us(rs.getString(3));
                a.setFecha_nacimiento(rs.getString(4));
                a.setDni_us(rs.getString(5));
                a.setContrasena_us(rs.getString(6));
                a.setCorreo_us(rs.getString(7));
                a.setSexo_us(rs.getString(8));
                a.setNombre_tipo_us(rs.getString(9));
                a.setAvatar(rs.getString(10));
                lis.add(a);
            }
            }
           
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

        return lis;
    }
    
    //update de alumno a docente
    public void updateUsuario(int idUsuario) {
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "update usuario set tipo_us = 2 where id_usuario = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idUsuario);
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    //downgrade de docente a alumno
    public void downgradeUsuario(int idUsuario) {
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "update usuario set tipo_us = 3 where id_usuario = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idUsuario);
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    //cambiar avatar
    public void changeAvatar(String ruta, int idUsuario) {
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "update usuario set avatar = ? where id_usuario = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, ruta);
            st.setInt(2, idUsuario);
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    //cambiar contraseña
    public void changePassword(String contrasena, int idUsuario) {
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "update usuario set contrasena_us = ? where id_usuario = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, contrasena);
            st.setInt(2, idUsuario);
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    //ingresar usuario
    public void createUser(usuario u) {
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "insert into usuario (nombre_us, apellidos_us, fecha_nacimiento, dni_us, contrasena_us, correo_us, sexo_us, tipo_us, avatar) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, u.getNombre_us());
            st.setString(2, u.getApellidos_us());
            st.setString(3, u.getFecha_nacimiento());
            st.setString(4, u.getDni_us());
            st.setString(5, u.getContrasena_us());
            st.setString(6, u.getCorreo_us());
            st.setString(7, u.getSexo_us());
            st.setInt(8, u.getId_tipo_us());
            st.setString(9, u.getAvatar());
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    //Eliminar usuario
    public void deleteUser(int idUsuario) {
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "delete from usuario where id_usuario = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idUsuario);
            st.executeUpdate();
            //llenar el arraylist con la clase entidad
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
}
