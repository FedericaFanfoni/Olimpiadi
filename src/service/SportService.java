package service;

import model.Sport;
import repository.SportRepository;

import java.util.ArrayList;

public class SportService {

    SportRepository sp = new SportRepository();

    public void createSport(String nomeSport, boolean squadra){
        Sport sport = new Sport();
        sport.setNomeSport(nomeSport);
        sport.setSquadra(squadra);
        sp.createSport(sport);
    }
    public ArrayList<Sport> readSport(){
        return sp.readSport();
    }
    public Sport searchSportByName(String nomeSport){
        return  sp.searchSportByName(nomeSport);
    }
    public Sport searchSportByID(int idSport){ return sp.searchSportByID(idSport);}
    public void updateSport(String nomeSport, boolean squadra, int idSport){
        sp.updateSport(nomeSport, squadra, idSport);
    }
    public void deleteSport(int idSport){
        sp.deleteSport(idSport);
    }
}
