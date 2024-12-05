package repository;

import config.DbConnection;

import model.Sport;


import java.sql.*;
import java.util.ArrayList;

public class SportRepository {

    // CREATE SPORT
    public void createSport(Sport sport){
        String sql = "INSERT INTO sport (nome, squadra) VALUES (?, ?)";
        try (Connection c = DbConnection.openConnection();
            PreparedStatement pstmt = c.prepareStatement(sql)
        ) {
              pstmt.setString(1, sport.getNomeSport());
              pstmt.setBoolean(2, sport.getSquadra());
              pstmt.executeUpdate();
              System.out.println("Sport inserito con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    // READ SPORT
    public ArrayList<Sport> readSport(){

        ArrayList<Sport> listaSport = new ArrayList<>();
        String sql = "SELECT * FROM sport";

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Ecco la lista degli sport!");

            while (rs.next()) {
                Sport sport = new Sport();
                sport.setNomeSport(rs.getString("nome"));
                sport.setSquadra(rs.getBoolean("squadra"));
                sport.setId(rs.getInt("id"));
                listaSport.add(sport);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        return listaSport;
    }

    // READ SPORT PER NOME
    public Sport searchSportByName(String nomeSport) {
        Sport sport = new Sport();

        String sql = "SELECT * FROM sport WHERE nome = ? " ;

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)) {
             pstmt.setString(1, nomeSport);

             ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                sport.setNomeSport(rs.getString("nome"));
                sport.setSquadra(rs.getBoolean("squadra"));
                sport.setId(rs.getInt("id"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }

        return sport;
    }

    // READ SPORT PER ID
    public Sport searchSportByID(int idSport) {
        Sport sport = new Sport();

        String sql = "SELECT * FROM sport WHERE id = ? " ;

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, idSport);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sport.setNomeSport(rs.getString("nome"));
                sport.setSquadra(rs.getBoolean("squadra"));
                sport.setId(rs.getInt("id"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }

        return sport;
    }

    public ArrayList<Sport> searchSportOfOlimpiade(int idOlimpiade){
        ArrayList<Sport> listaSport = new ArrayList<>();

        String sql = "SELECT s.* FROM SportOlimpiadi sp\n" +
                     "JOIN olimpiadi o ON o.id = sp.id_olimpiade\n" +
                     "JOIN sport s ON s.id = sp.id_sport\n" +
                     "WHERE o.id = " + idOlimpiade;

        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Sport sport = new Sport();
                sport.setNomeSport(rs.getString("nome"));
                sport.setSquadra(rs.getBoolean("squadra"));
                sport.setId(rs.getInt("id"));
                listaSport.add(sport);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        return listaSport;
    }

    // UPDATE SPORT
    public void updateSport(String nomeSport, boolean squadra, int idSport){
        String sql = "UPDATE sport SET nome = ?, squadra = ? WHERE id = " + idSport;
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)
        ) {
            pstmt.setString(1, nomeSport);
            pstmt.setBoolean(2, squadra);;
            pstmt.executeUpdate();
            System.out.println("Sport modificato con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    // DELETE SPORT
    public void deleteSport(int idSport){
        String sql = "DELETE FROM sport WHERE id = " +idSport;
        try (Connection c = DbConnection.openConnection();
             PreparedStatement pstmt = c.prepareStatement(sql)
        ) {
            pstmt.executeUpdate();
            System.out.println("Sport eliminato con successo!");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }


}
