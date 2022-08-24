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

/**
 *
 * @author Alana
 */
public class Vendas {
    
     public boolean Inserir(Connection con, String produto,Float valor,Integer quantidade,
            Float  total,Float  valor_pago,Float  troco,String cliente_cpf,String data_compra) throws SQLException, ParseException {

        String sql = "insert into venda (produto, valor, quantidade, total,valor_pago,troco, cliente_cpf,data_compra) "
                + "values (?,?,?,?,?,?,?,?)";
    

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
          
            
            stmt.setString(1, produto);
            stmt.setFloat (2, valor);
            stmt.setInt(3,quantidade);
            stmt.setFloat (4, total);
            stmt.setFloat (5,valor_pago);
            stmt.setFloat (6,troco);
            stmt.setString(7,cliente_cpf);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = sdf.parse(data_compra);
            java.sql.Date datasql = new java.sql.Date(data.getTime());
            stmt.setDate(8, datasql);
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
     
     
     
    
     
}