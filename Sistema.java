import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) throws Exception{
        // Conexão com o banco de dados
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root"; // Usuário do banco de dados vamos usar root pq é padrão
        final String password = ""; // Senha do banco de dados vamos deixar vazio pq é padrão
        Scanner scanner = new Scanner(System.in);
        System.out.println(" SISTEMA DE EVENTOS \n");

        int opcao = 0;

        do{
            System.out.println("\nMenu:");
            System.out.println("[1] - Cadastrar Organizador");
            System.out.println("[2] - Cadastrar Local");
            System.out.println("[3] - Cadastrar Participante");
            System.out.println("[4] - Cadastrar Evento");
            System.out.println("[5] - Listar Organizadores");
            System.out.println("[6] - Listar Local");
            System.out.println("[7] - Listar Participantes");
            System.out.println("[8] - Listar Eventos");
            System.out.println("[9] - Encerrar Programa");
            System.out.println("\nDigite uma opção:");
            opcao = scanner.nextInt();

            int idOrganizador = 0, idLocal = 0, capacidadeLocal, idParticipante = 0, idEvento = 0;
            String NomeOrganizador, cpfOrganizador, email, matricula, nomeLocal, endereco, nomeParticipante, cpfParticipante, codIngresso, telefone, nomeEvento, dataEvento, descricaoEvento;

            switch (opcao) {
                case 1:
                try{
                    System.out.println("\n* Cadastrar Organizador *");
                    System.out.println("Digite o nome do organizador:");
                    NomeOrganizador = scanner.next();
                    System.out.println("Digite o CPF do organizador:");
                    cpfOrganizador = scanner.next();
                    System.out.println("Digite o email do organizador:");
                    email = scanner.next();
                    System.out.println("Digite o matricula do organizador:");
                    matricula = scanner.next();
        
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO organizador"
                        + " (NomeOrganizador, cpfOrganizador, email, matricula) VALUES "
                        + " ('" + NomeOrganizador + "', '" + cpfOrganizador + "', '" + email + "', '" + matricula + "')");
                    if(!sql) {
                        System.out.println("Organizador cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 2:
                try{
                    System.out.println("\n* Cadastrar Local *");
                    System.out.println("Digite o nome do local:");
                    nomeLocal = scanner.next();
                    System.out.println("Digite o endereço do local:");
                    endereco = scanner.next();
                    System.out.println("Digite a capacidade do local:");
                    capacidadeLocal = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO local"
                        + " (nomeLocal, endereco, capacidadeLocal) VALUES "
                        + " ('" + nomeLocal + "', '" + endereco + "', " + capacidadeLocal + ")");
                    if(!sql) {
                        System.out.println("Local cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 3:
                try{
                    System.out.println("\n* Cadastrar Participante *");
                    System.out.println("Digite o nome do participante:");
                    nomeParticipante = scanner.next();
                    System.out.println("Digite o CPF do participante:");
                    cpfParticipante = scanner.next();
                    System.out.println("Digite o código do ingresso:");
                    codIngresso = scanner.next();
                    System.out.println("Digite o telefone do participante:");
                    telefone = scanner.next();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO participante"
                        + " (nomeParticipante, cpfParticipante, codIngresso, telefone) VALUES "
                        + " ('" + nomeParticipante + "', '" + cpfParticipante + "', " + codIngresso + ", '" + telefone + "')");
                    if(!sql) {
                        System.out.println("Participante cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 4:
                try{
                    System.out.println("\n* Cadastrar Evento *");
                    System.out.println("Digite o nome do evento:");
                    nomeEvento = scanner.next();
                    System.out.println("Digite a data do evento:");
                    dataEvento = scanner.next();
                    System.out.println("Digite a descrição do evento:");
                    descricaoEvento = scanner.next();
                    System.out.println("Digite o código do organizador:");
                    idOrganizador = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO evento"
                        + " (nomeEvento, dataEvento, descricaoEvento, idOrganizador) VALUES "
                        + " ('" + nomeEvento + "', '" + dataEvento + "', '" + descricaoEvento + "', " + idOrganizador + ")");
                    if(!sql) {
                        System.out.println("Evento cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 5: {
                    System.out.println("\nListar Organizador:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM organizador");
                    while(sql.next()) {
                        Organizador organizador = new Organizador(
                            sql.getInt("idOrganizador"),
                            sql.getString("NomeOrganizador"),
                            sql.getString("cpfOrganizador"),
                            sql.getString("email"),
                            sql.getString("matricula")
                        );

                        System.out.println(organizador);
                    }
                    con.close();
                    break;
                }
                case 6: {
                    System.out.println("\nListar Local:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM local");
                    while(sql.next()) {
                        Local local = new Local(
                            sql.getInt("idLocal"),
                            sql.getString("nomeLocal"),
                            sql.getString("endereco"),
                            sql.getInt("capacidadeLocal")
                        );
                        System.out.println(local);
                    }
                    con.close();
                }
                    break;
                case 7: {
                    System.out.println("\nListar Participante:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM participante");
                    while(sql.next()) {
                        Participante participante = new Participante(
                            sql.getInt("idParticipante"),
                            sql.getString("nomeParticipante"),
                            sql.getString("cpfParticipante"),
                            sql.getString("codIngresso"),
                            sql.getString("telefone")
                        );

                        System.out.println(participante);
                    }
                    con.close();
                    break;
                }
                case 8: {
                    System.out.println("\nListar Evento:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM evento");
                    while(sql.next()) {
                        Evento evento = new Evento(
                            sql.getInt("idEvento"),
                            sql.getString("nomeEvento"),
                            sql.getString("dataEvento"),
                            sql.getString("descricaoEvento"),
                            sql.getInt("idOrganizador"),
                            sql.getInt("idLocal")
                        );

                        System.out.println(evento);
                    }
                    con.close();
                }
                    break;
                case 9: {
                    System.out.println("Programa encerrando...");
                    break;
                }
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while(opcao != 9);
        scanner.close();
    }
}