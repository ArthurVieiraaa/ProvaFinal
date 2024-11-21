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
        final String url = "jdbc:mysql://localhost:3306/ *nomedobanco"; // Localização do banco de dados
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
}