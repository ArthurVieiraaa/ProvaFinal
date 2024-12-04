import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Organizador {
   
    private int idOrganizador;
    private String NomeOrganizador;
    private String cpfOrganizador;
    private String email;
    private String matricula;
 
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
}