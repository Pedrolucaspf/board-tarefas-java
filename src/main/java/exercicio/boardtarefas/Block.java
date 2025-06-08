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
public class Block {
    public void insert(String motivoBlock, int idCard){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "INSERT INTO block (ativo, motivoBlock, idCard) values (" +
                    1 + ", " +
                    motivoBlock + "', " +
                    idCard + "' )";
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    
    public void update(String motivoUnblock, int idCard){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "UPDATE block set " +
                    "ativo = " + 0 + "'," +
                    "motivoUnblock = '" + motivoUnblock +
                    "' WHERE idCard = " + idCard;
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
}
