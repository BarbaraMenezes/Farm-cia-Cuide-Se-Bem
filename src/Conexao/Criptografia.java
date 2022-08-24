/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Bárbara
 */
public class Criptografia {
    
    public String Criptografar(String senha) {
        String senhacriptografada;
        //INSTANCIAR A CLASSE MESSAGEDIGEST
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("MD5");
            
            //ATUALIZANDO A SENHA EM UMA MENSAGEM DO TIPO MESSAGEDIGEST
            algoritmo.update(senha.getBytes(),0,senha.length());
            
            //CONVERTENDO EM UMA MENSAGEM DE BASE HEXADECIMAL EM STRING
            senhacriptografada = new BigInteger(1,algoritmo.digest()).toString(16);
            
            return senhacriptografada;
        } catch(NoSuchAlgorithmException ex) {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
}
