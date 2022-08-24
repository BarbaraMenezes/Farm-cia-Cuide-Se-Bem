/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

/**
 *
 * @author Alana
 */
public class Funcionario {

    public boolean VerificarLogin(Connection con, String CPF, String senha) throws SQLException {
        String sql = "select nome from Funcionario where cpf= ? and senha= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, CPF);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            InformacoesSistema.CPF = CPF;
            rs.close();
            stmt.close();
            return true;
        } else {
            rs.close();
            stmt.close();
            return false;
        }
    }
    
    public void Inserir(Connection con,String Nome,String CPF,String Email,String Senha) throws SQLException {
        String sql = "insert into Funcionario(nome,cpf,email,senha)"
                + "values(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, Nome);
        stmt.setString(2, CPF);
        stmt.setString(4, Senha);
        stmt.setString(3, Email);
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Dados Inseridos com Sucesso!");
    }

    public void Excluir(Connection con, String CPF) throws SQLException {
        String sql = "delete from Funcionario where cpf = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, CPF);
        stmt.executeUpdate();
        stmt.close();
    }

     public void Alterar(Connection con,String Nome,String CPF,String Email,String Senha) throws  SQLException, ParseException {
        String sql = "update Funcionario set nome =?,cpf=?,email=?,senha=? where cpf=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, Nome);
        stmt.setString(2, CPF);
        stmt.setString(3, Email);
        stmt.setString(4, Senha);
        stmt.setString(5, CPF);
        stmt.executeUpdate();
        stmt.close();
    }
}

