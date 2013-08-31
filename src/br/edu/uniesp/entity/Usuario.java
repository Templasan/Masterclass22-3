/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniesp.entity;

/**
 *
 * @author Alunos
 */
public class Usuario {
    private int id;
    private String usuario;
    private String senha;
    private int idCliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
