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
        int opt = 0;

        do{
            System.out.println("\nMenu:");
            System.out.println("[1] - Cadastrar Organizador");
            System.out.println("[2] - Listar Organizadores");
            System.out.println("[3] - Alterar ou Excluir Organizadores");
            System.out.println("[4] - Cadastrar Local");
            System.out.println("[5] - Listar Local");
            System.out.println("[6] - Alterar ou Excluir Local");
            System.out.println("[7] - Cadastrar Participante");
            System.out.println("[8] - Listar Participantes");
            System.out.println("[9] - Alterar ou Excluir Participante");
            System.out.println("[10] - Cadastrar Evento");
            System.out.println("[11] - Listar Eventos");
            System.out.println("[12] - Alterar ou Excluir Eventos");
            System.out.println("[13] - Enviar Notificação");
            System.out.println("[14] - Encerrar Programa");
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
                case 2: {
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
                case 3: {
                    System.out.print("Digite o ID do organizador que deseja buscar: ");
                    int id = scanner.nextInt();
            
                    Organizador organizador = Organizador.buscaOrganizador(id);
            
                    if (organizador != null) {
                        System.out.println("Organizador encontrado!");
                        System.out.println(organizador);
            
                        System.out.println("Deseja atualizar ou deletar o organizador?");
                        System.out.println("Digite [a] - alterar");
                        System.out.println("Digite [d] - deletar");


                        char option = scanner.next().charAt(0);
                        scanner.nextLine();
            
                        if (option == 'a') {
                            System.out.print("Digite o novo nome do organizador: ");
                            String novoNomeOrganizador = scanner.nextLine();
                            System.out.print("Digite o novo CPF do organizador: ");
                            String novoCpfOrganizador = scanner.nextLine();
                            System.out.print("Digite o novo email do organizador: ");
                            String novoEmail = scanner.nextLine();
                            System.out.print("Digite a nova matrícula do organizador: ");
                            String novaMatriculaOrganizador = scanner.nextLine();
            
                            organizador.NomeOrganizador = novoNomeOrganizador;
                            organizador.cpfOrganizador = novoCpfOrganizador;
                            organizador.email = novoEmail;
                            organizador.matricula = novaMatriculaOrganizador;
            
                            organizador.atualizarOrganizador();
            
                            System.out.println("Depois da alteração:");
                            System.out.println(organizador);
                        } else if (option == 'd') {
                            organizador.deletarOrganizador();
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("Organizador não encontrado!");
                    }
                    scanner.close();
                    break;
                }
                case 4:
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
                case 5: {
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
                case 6: {
                    System.out.print("Digite o ID do local que deseja buscar: ");
                    int id = scanner.nextInt();
            
                    Local local = Local.buscaLocal(id);
            
                    if (local != null) {
                        System.out.println("Local encontrado!");
                        System.out.println(local);
                        System.out.println("Deseja atualizar ou deletar o local?");
                        System.out.println("Digite [a] - alterar");
                        System.out.println("Digite [d] - deletar");

                        char option = scanner.next().charAt(0);
                        scanner.nextLine();
            
                        if (option == 'a') {
                            System.out.print("Digite o novo nome do local: ");
                            String novoNomeLocal = scanner.nextLine();
                            System.out.print("Digite o novo endereço do local: ");
                            String novoEndereco = scanner.nextLine();
                            System.out.print("Digite a nova capacidade do local: ");
                            int novaCapacidadeLocal = scanner.nextInt();
            
                            local.nomeLocal = novoNomeLocal;
                            local.endereco = novoEndereco;
                            local.capacidadeLocal = novaCapacidadeLocal;
            
                            local.atualizarLocal();
            
                            System.out.println("Depois da alteração:");
                            System.out.println(local);
                        } else if (option == 'd') {
                            local.deletarLocal();
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("Local não encontrado!");
                    }
                    scanner.close();
                    break;
                }
                case 7:
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
                case 8: {
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
                case 9: {
                    System.out.print("Digite o ID do participante que deseja buscar: ");
                    int id = scanner.nextInt();
            
                    Participante participante = Participante.buscaParticipante(id);
            
                    if (participante != null) {
                        System.out.println("Participante encontrado!");
                        System.out.println(participante);
            
                        System.out.println("Deseja atualizar ou deletar o participante?");
                        System.out.println("Digite [a] - alterar");
                        System.out.println("Digite [d] - deletar");

                        char option = scanner.next().charAt(0);
                        scanner.nextLine();
            
                        if (option == 'a') {
                            System.out.print("Digite o novo nome do participante: ");
                            String novoNomeParticipante = scanner.nextLine();
                            System.out.print("Digite o novo CPF do participante: ");
                            String novoCpfParticipante = scanner.nextLine();
                            System.out.print("Digite o novo código de ingresso do participante: ");
                            String novoIngresso = scanner.nextLine();
                            System.out.print("Digite a novo número de telefone do participante: ");
                            String novoNumero = scanner.nextLine();
            
                            participante.nomeParticipante = novoNomeParticipante;
                            participante.cpfParticipante = novoCpfParticipante;
                            participante.codIngresso = novoIngresso;
                            participante.telefone = novoNumero;
            
                            participante.atualizarParticipante();
            
                            System.out.println("Depois da alteração:");
                            System.out.println(participante);
                        } else if (option == 'd') {
                            participante.deletarOrganizador();
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("Participante não encontrado!");
                    }
                    scanner.close();
                    break;
                }
                case 10:
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
                    System.out.println("Digite o código do local:");
                    idLocal = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO evento"
                        + " (nomeEvento, dataEvento, descricaoEvento, idOrganizador, idLocal) VALUES "
                        + " ('" + nomeEvento + "', '" + dataEvento + "', '" + descricaoEvento + "', " + idOrganizador + ", " + idLocal + ")");
                    if(!sql) {
                        System.out.println("Evento cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;
                case 11: {
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
                    break;
                }
                case 12: {
                    System.out.print("Digite o ID do Evento que deseja buscar: ");
                    int id = scanner.nextInt();
            
                    Evento evento = Evento.buscaEvento(id);
            
                    if (evento != null) {
                        System.out.println("Evento encontrado!");
                        System.out.println(evento);
            
                        System.out.println("Deseja atualizar ou deletar o evento?");
                        System.out.println("Digite [a] - alterar");
                        System.out.println("Digite [d] - deletar");


                        char option = scanner.next().charAt(0);
                        scanner.nextLine();
            
                        if (option == 'a') {
                            System.out.print("Digite o novo nome do evento: ");
                            String novoNomeEvento = scanner.nextLine();
                            System.out.print("Digite o nova data do evento: ");
                            String novoData = scanner.nextLine();
                            System.out.print("Digite o nova descrição do evento: ");
                            String novoDescricao = scanner.nextLine();
            
                            evento.nomeEvento = novoNomeEvento;
                            evento.dataEvento = novoData;
                            evento.descricaoEvento = novoDescricao;

                            evento.atualizarEvento();
            
                            System.out.println("Depois da alteração:");
                            System.out.println(evento);
                        } else if (option == 'd') {
                            evento.deletarEvento();
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    } else {
                        System.out.println("Organizador não encontrado!");
                    }
                    scanner.close();
                    break;
                }
                case 13: {
                    System.out.println("\n* Enviar Notificações *");
                    System.out.println("Escolha para quem mandar a notificação:");
                    System.out.println("[1] - Organizador");
                    System.out.println("[2] - Participante");
                    opt = scanner.nextInt();

                    Notificacao notificacao = opt == 1 
                        ? new NotificacaoEmail()
                        : new NotificacaoSMS();

                    notificacao.enviarNotificacao();
                    
                    break;
                }
                case 14: {
                    System.out.println("Programa encerrando...");
                    break;
                }
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while(opcao != 10);
        scanner.close();
    }
}