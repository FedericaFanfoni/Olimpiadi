package repository;

import config.DbConnection;

import model.Atleta;
import model.Sport;
import service.SportService;

import java.sql.*;
import java.util.ArrayList;

public class AtletaRepository {

    // CREATE ATLETA
    public void createAtlata(Atleta atleta){
        String sql = "INSERT INTO atleti (nome, cognome, data_nascita, altezza, id_sport) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);
        ) {
            pstmt.setString(1, atleta.getNome());
            pstmt.setString(2, atleta.getCognome());
            pstmt.setDate(3, Date.valueOf(atleta.getDataNascita()));
            pstmt.setInt(4, atleta.getAltezza());
            pstmt.setInt(5,atleta.getSport().getId());
            pstmt.executeUpdate();
            System.out.println("Atleta inserito con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    // READ ATLETI
    public ArrayList<Atleta> readAtleti(){
        ArrayList<Atleta> listaAtleti = new ArrayList<>();
        SportService ss = new SportService();
        String sql = "SELECT * FROM atleti";

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Sport sport = ss.searchSportByID(rs.getInt("id_sport"));

                Atleta atleta = new Atleta(
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getDate("data_nascita").toLocalDate(),
                        rs.getInt("altezza"),
                        sport
                );
                listaAtleti.add(atleta);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }

        return listaAtleti;
    }

    public ArrayList<Atleta> searchAtletiOfOlimpiade(int idOlimpiade){

        ArrayList<Atleta> listaAtleti = new ArrayList<>();

        String sql = "SELECT a.* FROM partecipazioni p\n" +
                "JOIN olimpiadi o ON o.id = p.id_olimpiade\n" +
                "JOIN atleti a ON a.id = p.id_atleta\n" +
                "WHERE o.id = " + idOlimpiade;

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Atleta atleta = new Atleta(
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            rs.getDate("data_nascita").toLocalDate(),
                            rs.getInt("altezza")
                    );
                    listaAtleti.add(atleta);
                }
        } catch (ClassNotFoundException | SQLException e) {
                    System.err.println(e.getMessage());
                    System.exit(0);
        }

        return listaAtleti;

    }

    // UPDATE ATLETA
    public void updateAtleta(Atleta atleta, int idAtleta){
        String sql = "UPDATE atleti SET nome = ?, cognome = ?, data_nascita = ?, altezza = ?, id_sport = ? WHERE id = " + idAtleta;
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)
        ) {
            pstmt.setString(1, atleta.getNome());
            pstmt.setString(2, atleta.getCognome());
            pstmt.setDate(3,Date.valueOf(atleta.getDataNascita()) );
            pstmt.setInt(4, atleta.getAltezza());
            pstmt.setInt(5, atleta.getSport().getId());

            pstmt.executeUpdate();
            System.out.println("Atleta modificato con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void deleteAtleta(int idAtleta){
        String sql = "DELETE FROM atleti WHERE id = " +idAtleta;
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)
        ) {
            pstmt.executeUpdate();
            System.out.println("Atleta eliminato con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
