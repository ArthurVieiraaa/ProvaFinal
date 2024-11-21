import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) throws Exception{
        // Conexão com o banco de dados
        final String url = "jdbc:mysql://localhost:3306/ *nomedobanco"; // Localização do banco de dados
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

            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    System.out.println("* Cadastrar Organizador *");

                    System.out.println("Digite o nome do organizador:");
                    String NomeOrganizador = scanner.next();

                    System.out.println("Digite o CPF do organizador:");
                    String cpfOrganizador = scanner.next();

                    System.out.println("Digite o email do organizador:");
                    String email = scanner.next();

                    System.out.println("Digite o matricula do organizador:");
                    String matricula = scanner.next();
        
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO organizador"
                        + " (NomeOrganizador, cpfOrganizador, email, matricula) VALUES "
                        + " ('"NomeOrganizador "', '" cpfOrganizador "', '" email "', '" matricula "')");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Organizador cadastrado com sucesso!");
                    }
                    con.close();
                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 2:
                    System.out.println("* Cadastrar Local *");
                    System.out.println("Digite o nome do local:");
                    nomeLocal = scanner.next();
                    System.out.println("Digite o endereço do local:");
                    endereco = scanner.next();
                    System.out.println("Digite a capacidade do local:");
                    capacidade = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO local"
                        + " (nome, endereco, capacidade) VALUES "
                        + " ('" nomeLocal "', '" endereco "', " capacidade ")");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Local cadastrado com sucesso!");
                    }
                    break;
                case 3:
                    System.out.println("* Cadastrar Participante *");
                    System.out.println("Digite o nome do participante:");
                    String nomeParticipante = scanner.next();
                    System.out.println("Digite o CPF do participante:");
                    String cpfParticipante = scanner.next();
                    System.out.println("Digite o código do ingresso:");
                    String codIngresso = scanner.nextInt();
                    System.out.println("Digite o telefone do participante:");
                    String telefone = scanner.next();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO participante"
                        + " (nomeParticipante, cpfParticipante, codIngresso, telefone) VALUES "
                        + " ('" nomeParticipante "', '" cpfParticipante "', " codIngresso ", '" telefone "')");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Participante cadastrado com sucesso!");
                    }
                    break;
                case 4:
                    System.out.println("* Cadastrar Evento *");
                    System.out.println("Digite o nome do evento:");
                    nomeEvento = scanner.next();
                    System.out.println("Digite a data do evento:");
                    data = scanner.next();
                    System.out.println("Digite a descrição do evento:");
                    descricaoEvento = scanner.next();
                    System.out.println("Digite o código do organizador:");
                    idOrganizador = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO evento"
                        + " (nome, data, descricao, idOrganizador) VALUES "
                        + " ('" nomeEvento "', '" data "', '" descricaoEvento "', " idOrganizador ")");
                    if(!sql) {
                        System.out.println("Falha na execução");
                    } else {
                        System.out.println("Evento cadastrado com sucesso!");
                    }
                    break;
                case 5: {
                    System.out.println("\nListar Organizador:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM orgazinador");
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
                            sql.getString("nome"),
                            sql.getString("endereco"),
                            sql.getInt("capacidade")
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
                            sql.getInt("codIngresso"),
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
                            sql.getString("nome"),
                            sql.getString("data"),
                            sql.getString("descricao"),
                            sql.getInt("idOrganizador")
                        );

                        System.out.println(evento);
                    }
                    con.close();
                }
                    break;
                case 9: {
                    System.out.println("Programa encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while(opcao != 9);
        scanner.close();
    }
}
