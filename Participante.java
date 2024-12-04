import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Participante {
   
    private int idParticipante;
    private String nomeParticipante;
    private String cpfParticipante;
    private String codIngresso;
    private String telefone;

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
}