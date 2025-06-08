/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio.boardtarefas;

import java.sql.SQLException;

/**
 *
 * @author REJANE
 */
public class Coluna {
    public void insert(String nome, int idBoard, String tipo){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "INSERT INTO coluna (nome, idBoard, tipo) values ('" +
                    nome + "', " +
                    idBoard + "', " +
                    tipo + "' )";
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
     
    
}
