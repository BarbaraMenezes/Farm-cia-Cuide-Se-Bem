/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Alana
 */
public class Produto {
    
     public boolean Inserir(Connection con, String nome,String funcionario_cpf, String validade,
            Double valor_venda,Double valor_compra,String generico,Integer quantidade) throws SQLException, ParseException {

        String sql = "insert into Produtos (nome,funcionario_cpf,validade,valor_venda,valor_compra,generico,quantidade) "
                + "values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = sdf.parse(validade);
            java.sql.Date datasql = new java.sql.Date(data.getTime());
            stmt.setString(2,InformacoesSistema.CPF );
            stmt.setDate(3, datasql);

            stmt.setDouble(4,valor_venda);
            stmt.setDouble(5,valor_compra);
            stmt.setString(6,generico);
            stmt.setInt(7,quantidade);

            int rs = stmt.executeUpdate();

            if (!(rs > 0)) {
                stmt.close();
                return false;
            }

            stmt.close();

        } catch (SQLException | ParseException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }
     
     public boolean Alterar(Connection con, String nome,String funcionario_cpf,Integer id_produto, String validade,
            Double valor_venda,Double valor_compra,String generico,Integer quantidade) {

        String sql = "update produtos set nome = ?, validade = ?, valor_venda = ?, valor_compra = ?, generico = ?, quantidade = ? where id_produto = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = sdf.parse(validade);
            java.sql.Date datasql = new java.sql.Date(data.getTime());
            stmt.setDate(2, datasql);

            stmt.setDouble(3,valor_venda);
            stmt.setDouble(4,valor_compra);
            stmt.setString(5,generico);
            stmt.setInt(6,quantidade);
            stmt.setInt(7,id_produto);

            int rs = stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso", "Operação Realizada", 1);

            stmt.close();

        } catch (SQLException | ParseException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }
     
      public void Excluir(Connection con, int id) {
        String sql = "delete from produtos where id_produto = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int rs = stmt.executeUpdate();

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso", "Operação Realizada!", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Não Foi Possível Excluir os Dados", "Operação Não Realizada!", 0);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }

    }
       public boolean Atualizar(Connection con,Integer quantidade, String nome) throws SQLException, ParseException {

        
         String sql=  "update produtos set quantidade= ? where nome= ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1,quantidade);
            stmt.setString(2,nome);
           
            int rs = stmt.executeUpdate();

            if (!(rs > 0)) {
                stmt.close();
                return false;
            }

            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return true;
    }
    
}
