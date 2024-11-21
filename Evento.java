import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Evento {
   
    int idEvento;
    String nomeEvento;
    String dataEvento;
    String descricaoEvento;
 
    public Evento(int idEvento, String nomeEvento, String dataEvento, String descricaoEvento) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.descricaoEvento = descricaoEvento;
    }
   
    static Evento buscaEvento(int idEvento) {
        final String url = "jdbc:mysql://localhost:3306/ *nomedobanco"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
        Evento evento = null;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM local WHERE idEvento = " + idEvento);
            if (rs.next()) {
                evento = new Evento(
                    rs.getInt("idEvento"),
                    rs.getString("nomeEvento"),
                    rs.getString("dataEvento"),
                    rs.getString("descricaoEvento")
                );
                System.out.println(evento);
            } else {
                throw new RuntimeException("Evento não encontrado!");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar evento!");
        }
        return evento;
    }
 
    public String toString() {
        return  "ID: " + this.idEvento
            + "\nNome do Local: " + this.nomeEvento
            + "\nEndereço do Local: " + this.dataEvento
            + "\nCapacidade do Local: " + this.descricaoEvento
            + "\n===================================";
    }
}