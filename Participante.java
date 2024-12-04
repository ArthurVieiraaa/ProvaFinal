import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Participante {
   
    int idParticipante;
    String nomeParticipante;
    String cpfParticipante;
    String codIngresso;
    String telefone;

    public Participante(int idParticipante, String nomeParticipante, String cpfParticipante, String codIngresso, String telefone) {
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.cpfParticipante = cpfParticipante;
        this.codIngresso = codIngresso;
        this.telefone = telefone;
    }
   
    static Participante buscaParticipante(int idParticipante) {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
        Participante participante = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM participante WHERE idParticipante = " + idParticipante);
            if (rs.next()) {
                participante = new Participante(
                    rs.getInt("idParticipante"),
                    rs.getString("nomeParticipante"),
                    rs.getString("cpfParticipante"),
                    rs.getString("codIngresso"),
                    rs.getString("telefone")
                );
                System.out.println(participante);
            } else {
                throw new RuntimeException("Participante não encontrado!");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar participante!");
        }
        return participante;
    }
 
    public String toString() {
        return  "ID: " + this.idParticipante
            + "\nNome: " + this.nomeParticipante
            + "\nCPF: " + this.cpfParticipante
            + "\nCódigo do Ingresso: " + this.codIngresso
            + "\nTelefone: " + this.telefone
            + "\n===================================";
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void atualizarParticipante() {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE participante SET nomeParticipante = ?, cpfParticipante = ?, codIngresso = ?, telefone = ? WHERE idParticipante = ?";
            var preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, this.nomeParticipante);
            preparedStatement.setString(2, this.cpfParticipante);
            preparedStatement.setString(3, this.codIngresso);
            preparedStatement.setString(4, this.telefone);
            preparedStatement.executeUpdate();
            System.out.println("Informações do participante atualizadas com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar participante!");
        }
    }

    // Método para deletar o organizador do banco de dados
    public void deletarOrganizador() {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
    
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão com o banco de dados estabelecida."); // Mensagem de depuração
            System.out.println("Tentando deletar participante com ID: " + this.idParticipante); // Mensagem de depuração
    
            String sql = "DELETE FROM participante WHERE idParticipante = ?";
            var preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, this.idParticipante);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Participante deletado com sucesso!");
            } else {
                System.out.println("Nenhum participante encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar participante!");
        }
    }

}