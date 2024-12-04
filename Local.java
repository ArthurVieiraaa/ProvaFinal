import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Local {
   
    int idLocal;
    String nomeLocal;
    String endereco;
    int capacidadeLocal;
 
    public Local(int idLocal, String nomeLocal, String endereco, int capacidadeLocal) {
        this.idLocal = idLocal;
        this.nomeLocal = nomeLocal;
        this.endereco = endereco;
        this.capacidadeLocal = capacidadeLocal;
    }
   
    static Local buscaLocal(int idLocal) {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
        Local local = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM local WHERE idLocal = " + idLocal);
            if (rs.next()) {
                local = new Local(
                    rs.getInt("idLocal"),
                    rs.getString("nomeLocal"),
                    rs.getString("endereco"),
                    rs.getInt("capacidadeLocal")
                );
                System.out.println(local);
            } else {
                throw new RuntimeException("Local não encontrado!");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar local!");
        }
        return local;
    }
 
    public String toString() {
        return  "ID: " + this.idLocal
            + "\nNome do Local: " + this.nomeLocal
            + "\nEndereço do Local: " + this.endereco
            + "\nCapacidade do Local: " + this.capacidadeLocal
            + "\n===================================";
    }

    public void atualizarLocal() {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE local SET nomeLocal = ?, endereco = ?, capacidadeLocal = ? WHERE idLocal = ?";
            var preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, this.nomeLocal);
            preparedStatement.setString(2, this.endereco);
            preparedStatement.setInt(3, this.capacidadeLocal);
            preparedStatement.executeUpdate();
            System.out.println("Informações do local atualizadas com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar local!");
        }
    }

    // Método para deletar o organizador do banco de dados
    public void deletarLocal() {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
    
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexão com o banco de dados estabelecida."); // Mensagem de depuração
            System.out.println("Tentando deletar local com ID: " + this.idLocal); // Mensagem de depuração
    
            String sql = "DELETE FROM local WHERE idLocal = ?";
            var preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, this.idLocal);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Local deletado com sucesso!");
            } else {
                System.out.println("Nenhum local encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar local!");
        }
    }

}