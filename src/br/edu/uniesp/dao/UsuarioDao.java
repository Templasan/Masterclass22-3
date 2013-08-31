/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniesp.dao;

import br.edu.uniesp.entity.Cliente;
import br.edu.uniesp.entity.Usuario;
import br.edu.uniesp.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rafael
 */
public class UsuarioDao {

    private static Connection con;
    private static Statement stmt;
    private static Statement stmtNavegar;
    private static ResultSet rsNavegar;

    public UsuarioDao() throws ClassNotFoundException, SQLException {

        getRsNavegar();

    }

    private static Statement conectar() throws ClassNotFoundException, SQLException{
        con = ConexaoFactory.getConnection();
        stmt = con.createStatement();
        stmtNavegar = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        return stmtNavegar;
    }
    
    public static ResultSet getRsNavegar() throws ClassNotFoundException, SQLException{
        
        if (con == null)
            stmtNavegar = conectar();
        rsNavegar = stmtNavegar.executeQuery("select * from Usuario");
        return rsNavegar;
    }
    public boolean inserir(Cliente cliente) throws SQLException{
        stmt.executeUpdate("insert into aplicacao.Cliente (`id`, `nome`, `cpf`, `Telefone`) values ( NULL, '" + cliente.getNome() + "', '" + cliente.getCpf() + "',  " + cliente.getTelefone() + " )");
        rsNavegar = stmtNavegar.executeQuery("select * from aplicacao.Cliente");
        return true;
    }
    
    public boolean atualizar(Cliente cliente) throws SQLException{
        stmt.executeUpdate("update aplicacao.Cliente set nome =  '" + cliente.getNome() + "', cpf = '" + cliente.getCpf() + "' , Telefone = " + cliente.getTelefone() + " where id = " + cliente.getId());
        rsNavegar = stmtNavegar.executeQuery("select * from aplicacao.Cliente");
        return true;
    }
    
    public boolean salvar(Cliente cliente) throws SQLException{
        
        if (cliente.getId() == 0){
            inserir(cliente);
        }else{
            atualizar(cliente);
        }
        return true;
    }

    public Usuario pesquisar(int usuarioId) throws SQLException {
            ResultSet rs = stmt.executeQuery("select * from aplicacao.Usuario where id = " + usuarioId);

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs.getString( "usuario" ) );
                usuario.setSenha(rs.getString( "senha" ) );
                return usuario;
            } else 
                return null;
            
    }
    
    public boolean validar(String usuario, String senha) throws SQLException{
        
        ResultSet rs = stmt.executeQuery("select * from aplicacao.Usuario where usuario = '" + usuario+"' AND senha='"+senha+"'");
        if (rs.next()) {
            return true;
        }
        return false;
  
    }
}
