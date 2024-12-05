package repository;

import config.DbConnection;

import model.Atleta;
import model.Olimpiade;
import model.Sport;

import java.sql.*;
import java.util.ArrayList;

public class OlimpiadeRepository {

    // CREATE OLIMPIADE
    public void createOlimpiade(Olimpiade olimpiade){
        String sql = "INSERT INTO olimpiadi (data_apertura, data_chiusura, anno, luogo) VALUES (?, ?, ?, ?)";
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);
        ) {
            pstmt.setDate(1, Date.valueOf(olimpiade.getDataApertura()));
            pstmt.setDate(2, Date.valueOf(olimpiade.getDataChiusura()));
            pstmt.setInt(3, olimpiade.getAnno());
            pstmt.setString(4, olimpiade.getLuogo());
            pstmt.executeUpdate();
            System.out.println("Olimpiade creata con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    // READ OLIMPIADI
    public ArrayList<Olimpiade> readOlimpiadi(){
        ArrayList<Olimpiade> listaOlimpiadi = new ArrayList<>();
        String sql = "SELECT * FROM olimpiadi";

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            SportRepository sr = new SportRepository();
            AtletaRepository ar = new AtletaRepository();
            while (rs.next()) {
                ArrayList<Sport> listaSport;
                listaSport = sr.searchSportOfOlimpiade(rs.getInt("id"));
                ArrayList<Atleta> atletiPartecipanti;
                atletiPartecipanti = ar.searchAtletiOfOlimpiade(rs.getInt("id"));

                Olimpiade olimpiade = new Olimpiade(
                        rs.getDate("data_apertura").toLocalDate(),
                        rs.getDate("data_chiusura").toLocalDate(),
                        rs.getInt("anno"),
                        rs.getString("luogo"),
                        atletiPartecipanti,
                        listaSport
                );
                listaOlimpiadi.add(olimpiade);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }

        return listaOlimpiadi;
    }

    // UPDATE OLIMPIADE
    public void updateOlimpiade(Olimpiade olimpiade, int vecchioAnno){
        String sql = "UPDATE olimpiadi SET data_apertura = ?, data_chiusura = ?, anno = ?, luogo = ? WHERE anno = " + vecchioAnno;
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);
        ) {
            pstmt.setDate(1, Date.valueOf(olimpiade.getDataApertura()));
            pstmt.setDate(2, Date.valueOf(olimpiade.getDataChiusura()));
            pstmt.setInt(3, olimpiade.getAnno());
            pstmt.setString(4, olimpiade.getLuogo());
            pstmt.executeUpdate();
            System.out.println("Olimpiade modificata con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    // DELETE OLIMPIADE
    public void deleteOlimpiade(int anno){
        String sql = "DELETE FROM olimpiadi WHERE anno = " + anno;
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)
        ) {
            pstmt.executeUpdate();
            System.out.println("Olimpiade eliminata con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

}
