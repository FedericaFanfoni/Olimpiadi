package service;

import model.Atleta;
import model.Sport;
import repository.AtletaRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class AtletaService {
    AtletaRepository ar = new AtletaRepository();

    public void createAtleta(String nome, String cognome, LocalDate dataNascita,int altezza, Sport sport){

        Atleta atleta = new Atleta(nome, cognome, dataNascita, altezza, sport);
        ar.createAtlata(atleta);
    }
    public ArrayList<Atleta> readAtleti(){
        return ar.readAtleti();
    }
    public void updateAtleta(Atleta atleta, int idAtleta){
        ar.updateAtleta(atleta, idAtleta);
    }
    public void deleteAtleta(int idAtleta){
        ar.deleteAtleta(idAtleta);
    }
}
