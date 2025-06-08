/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio.boardtarefas;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author REJANE
 */
public class Board {
    
    
    public void insert(String nome){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "INSERT INTO board (nome) values ('" +
                    nome +"' )";
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
     
    public void delete(int id){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "DELETE FROM board WHERE id = " + id;
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
    
    public void execById(int id){
         try(
                var connection = ConnectionUtil.getConnection();
        ){
            Card card = new Card();
            Block block = new Block();
            String titulo, desc, motivo;
            Scanner scanner = new Scanner(System.in);
            int option, idCard;
            while(true){
                System.out.println(" ");
                System.out.println("Opcoes:");
                System.out.println("1.Criar card");
                System.out.println("2.Mover card para a próxima coluna");
                System.out.println("3.Cancelar card");
                System.out.println("4.Bloquear card");
                System.out.println("5.Desbloquear card");
                System.out.println("6.Fechar board");
                System.out.print("Digite o numero da opcao que deseja:");
                option = scanner.nextInt();
                if(option == 6){
                    break;
                }
                switch(option){
                    case(1):
                        System.out.print("Digite o titulo do card:");
                        titulo = scanner.nextLine();
                        System.out.print("Digite a descricao:");
                        desc = scanner.nextLine();
                        card.insert(titulo, desc, id);
                        break;
                    case(2):
                        System.out.print("Digite o id do card:");
                        idCard = scanner.nextInt();
                        scanner.next();
                        card.mover(idCard);
                        break;
                    case(3):
                        System.out.print("Digite o id do card:");
                        idCard = scanner.nextInt();
                        scanner.next();
                        card.cancelar(idCard);
                        break;
                    case(4):
                        System.out.print("Digite o id do card:");
                        idCard = scanner.nextInt();
                        scanner.next();
                        System.out.print("Digite o motivo:");
                        motivo = scanner.nextLine();
                        block.insert(motivo, idCard);
                        break;
                    case(5):
                        System.out.print("Digite o id do card:");
                        idCard = scanner.nextInt();
                        scanner.next();
                        System.out.print("Digite o motivo:");
                        motivo = scanner.nextLine();
                        block.update(motivo, idCard);
                        break;
                    default:
                        System.out.println("Operacao invalida.");
                }
            }
             
        }catch (SQLException ex){
            System.out.println("Erro: "+ex.getMessage());
        }
    }
}
