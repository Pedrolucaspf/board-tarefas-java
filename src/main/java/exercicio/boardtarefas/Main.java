/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package exercicio.boardtarefas;

import org.flywaydb.core.Flyway;
import java.util.Scanner;

/**
 *
 * @author REJANE
 */
public class Main {

    public static void main(String[] args) {
        var flyway = Flyway.configure()
            .dataSource("jdbc:mysql://localhost:3306/board_tarefas", "root", "")
            .load();
        
        flyway.migrate();
        Board board = new Board();
        Coluna coluna = new Coluna();
        String nomeB, nomeC, tipo;
        Scanner scanner = new Scanner(System.in);
        int option, ncols, idBoard, indice = 1;
        while(true){
            System.out.println(" ");
            System.out.println("Opcoes:");
            System.out.println("1.Criar board");
            System.out.println("2.Selecionar board");
            System.out.println("3.Excluir board");
            System.out.println("4.Sair");
            System.out.print("Digite o numero da opcao que deseja:");
            option = scanner.nextInt();
            if(option == 4){
                break;
            }
            switch(option){
                case(1):
                    System.out.print("Insira o nome do board:");
                        nomeB = scanner.nextLine();
                        while(true){
                            System.out.print("Insira o numero de colunas:");
                            ncols = scanner.nextInt();
                            scanner.next();
                            if(ncols < 3){
                                System.out.println("Devem haver pelo menos 3 colunas");
                            }
                            else{
                                for(int i = 0; i < ncols; i++){
                                    System.out.print("Insira o nome da coluna:");
                                    nomeC = scanner.nextLine();
                                    if(i==0){
                                        tipo = "iniciado";
                                    }
                                    else if(i==(ncols-1)){
                                        tipo = "cancelado";
                                    }
                                    else if(i==(ncols-2)){
                                        tipo = "concluido";
                                    }
                                    else{
                                        tipo = "pendente";
                                    }
                                    coluna.insert(nomeC, indice, tipo);
                                }
                                break;
                            }
                        }
                        board.insert(nomeB);
                        indice += 1;
                    break;
                case(2):
                    System.out.print("Digite o id do board:");
                    idBoard = scanner.nextInt();
                    scanner.next();
                    board.execById(idBoard);
                    break;
                case(3):
                    System.out.print("Digite o id do board:");
                    idBoard = scanner.nextInt();
                    scanner.next();
                    board.delete(idBoard);
                    break;
                default:
                    System.out.println("Operacao invalida.");
            }
        }
    }
}
