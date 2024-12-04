import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Organizador {
   
    int idOrganizador;
    String NomeOrganizador;
    String cpfOrganizador;
    String email;
    String matricula;
 
    public Organizador(int idOrganizador, String NomeOrganizador, String cpfOrganizador, String email, String matricula) {
        this.idOrganizador = idOrganizador;
        this.NomeOrganizador = NomeOrganizador;
        this.cpfOrganizador = cpfOrganizador;
        this.email = email;
        this.matricula = matricula;
    }
   
    static Organizador buscaOrganizador(int idOrganizador) {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
        Organizador organizador = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM organizador WHERE idOrganizador = " + idOrganizador);
            if (rs.next()) {
                organizador = new Organizador(
                    rs.getInt("idOrganizador"),
                    rs.getString("NomeOrganizador"),
                    rs.getString("cpfOrganizador"),
                    rs.getString("email"),
                    rs.getString("matricula")
                );
                System.out.println(organizador);
            } else {
                throw new RuntimeException("Organizador não encontrado!");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar organizador!");
        }
        return organizador;
    }
 
    public String toString() {
        return  "ID: " + this.idOrganizador
            + "\nNome: " + this.NomeOrganizador
            + "\nCPF: " + this.cpfOrganizador
            + "\nEmail: " + this.email
            + "\nMatricula: " + this.matricula
            + "\n===================================";
    }

    public int getIdOrganizador() {
        return idOrganizador;
    }

    public String getEmail() {
        return email;
    }

    // Método para atualizar informações do organizador no banco de dados
    public void atualizarOrganizador() {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE organizador SET NomeOrganizador = ?, cpfOrganizador = ?, email = ?, matricula = ? WHERE idOrganizador = ?";
            var preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, this.NomeOrganizador);
            preparedStatement.setString(2, this.cpfOrganizador);
            preparedStatement.setString(3, this.email);
            preparedStatement.setString(4, this.matricula);
            preparedStatement.setInt(5, this.idOrganizador);
            preparedStatement.executeUpdate();
            System.out.println("Informações do organizador atualizadas com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar organizador!");
        }
    }

    // Método para deletar o organizador do banco de dados
    public void deletarOrganizador() {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
    
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão com o banco de dados estabelecida."); // Mensagem de depuração
            System.out.println("Tentando deletar organizador com ID: " + this.idOrganizador); // Mensagem de depuração
    
            String sql = "DELETE FROM organizador WHERE idOrganizador = ?";
            var preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, this.idOrganizador);
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("Organizador deletado com sucesso!");
            } else {
                System.out.println("Nenhum organizador encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar organizador!");
        }
    }
}