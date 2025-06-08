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
public class Card {
    public void insert(String titulo, String desc, int idBoard){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            statement.executeQuery("SELECT * FROM coluna WHERE idBoard = " + idBoard);
            var resultSet = statement.getResultSet();
            int idColuna = -1;
            while(resultSet.next()){
                var tipo = resultSet.getString("tipo");
                if("iniciado".equals(tipo)){
                    idColuna = resultSet.getInt("id");
                    break;
                }
            }
            if(idColuna != -1){
                var sql = "INSERT INTO card (titulo, descricao, idColuna) values ('" +
                        titulo + "', " +
                        desc + "', " +
                        idColuna + "' )";
                statement.executeUpdate(sql);
            }
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    
    public void mover(int id){
         try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement();
        ){
            statement.executeQuery("SELECT * FROM block WHERE idCard = " + id);
            var resultSet = statement.getResultSet();
            int bloq = 0;
            while(resultSet.next()){
                int ativo = resultSet.getInt("ativo");
                if(ativo == 1){
                    bloq = 1;
                    System.out.println("Operacao invalida, pois o card esta bloqueado.");
                }
            }
            if(bloq == 0){
                statement.executeQuery("SELECT * FROM card WHERE id = " + id);
                var resultSet2 = statement.getResultSet();
                if (resultSet2.next()){
                    int coluna = resultSet2.getInt("idColuna");
                    statement.executeQuery("SELECT * FROM coluna WHERE id = " + coluna);
                    var resultSet3 = statement.getResultSet();
                    String tipo = resultSet3.getString("tipo");
                    switch(tipo){
                        case "concluido":
                            System.out.println("Tarefa do card ja concluida.");
                            break;
                        case "cancelado":
                            System.out.println("Card foi cancelado anteriormente.");
                            break;
                        default:
                            coluna += 1;
                            var sql = "UPDATE card set " +
                            "idColuna = " + coluna + 
                            "' WHERE id = " + id;
                            statement.executeUpdate(sql);
                    }
                }
            }
        }catch (SQLException ex){
            System.out.println("Card nao encontrado.");
        }
    }
    
    public void cancelar(int id){
         try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement();
        ){
            statement.executeQuery("SELECT * FROM block WHERE idCard = " + id);
            var resultSet = statement.getResultSet();
            int bloq = 0;
            while(resultSet.next()){
                int ativo = resultSet.getInt("ativo");
                if(ativo == 1){
                    bloq = 1;
                    System.out.println("Operacao invalida, pois o card esta bloqueado.");
                }
            }
            if(bloq == 0){
                statement.executeQuery("SELECT * FROM card WHERE id = " + id);
                var resultSet2 = statement.getResultSet();
                if (resultSet2.next()){
                    int coluna = resultSet2.getInt("idColuna");
                    statement.executeQuery("SELECT * FROM coluna WHERE id = " + coluna);
                    var resultSet3 = statement.getResultSet();
                    int board = resultSet3.getInt("idBoard");
                    String cancelado = "cancelado";
                    statement.executeQuery("SELECT * FROM coluna WHERE tipo = "+ cancelado +" AND  idBoard = " + board);
                    var resultSet4 = statement.getResultSet();
                    int novaColuna = resultSet4.getInt("id");
                    var sql = "UPDATE card set " +
                            "idColuna = " + novaColuna + 
                            "' WHERE id = " + id;
                            statement.executeUpdate(sql);
                }
            }
        }catch (SQLException ex){
            System.out.println("Card nao encontrado.");
        }
    }
}
