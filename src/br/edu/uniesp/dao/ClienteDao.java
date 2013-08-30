/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniesp.dao;

import br.edu.uniesp.entity.Cliente;
import br.edu.uniesp.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rafael
 */
public class ClienteDao {

    private static Connection con;
    private static Statement stmt;
    private static Statement stmtNavegar;
    private static ResultSet rsNavegar;

    public ClienteDao() throws ClassNotFoundException, SQLException {

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
        rsNavegar = stmtNavegar.executeQuery("select * from Cliente");
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

    public Cliente pesquisar(String clienteId) throws SQLException {
            ResultSet rs = stmt.executeQuery("select * from aplicacao.Cliente where id = " + clienteId);

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt( "id" ) );
                cliente.setNome( rs.getString( "nome" ) );
                cliente.setCpf(rs.getString( "cpf" ) );
                cliente.setTelefone(rs.getString( "Telefone" ) );
                return cliente;
            } else 
                return null;
            
    }
}
