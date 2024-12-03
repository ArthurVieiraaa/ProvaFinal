import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Evento {
   
    int idEvento;
    String nomeEvento;
    String dataEvento;
    String descricaoEvento;
    int idOrganizador;
    int idLocal;
 
    public Evento(int idEvento, String nomeEvento, String dataEvento, String descricaoEvento, int idOrganizador, int idLocal) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.descricaoEvento = descricaoEvento;
        this.idOrganizador = idOrganizador;
        this.idLocal = idLocal;
    }
    
    static List<Evento> evento;

    static Evento buscaEvento(int idEvento) {
        final String url = "jdbc:mysql://localhost:3306/provafinaljava_db"; // Localização do banco de dados
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
                    rs.getString("descricaoEvento"),
                    rs.getInt("idOrganizador"),
                    rs.getInt("idLocal")
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
            + "\nNome do Evento: " + this.nomeEvento
            + "\nData do Evento: " + this.dataEvento
            + "\nDescrição do Evento: " + this.descricaoEvento
            + "\nID do Organizador: " +  this.idOrganizador
            + "\nID do Local: " + this.idLocal
            + "\n===================================";
    }
}