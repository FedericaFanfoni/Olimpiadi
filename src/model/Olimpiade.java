package model;

import java.time.LocalDate;
import java.util.ArrayList;
import model.Sport;
import model.Atleta;

public class Olimpiade {

    private LocalDate dataApertura;
    private LocalDate dataChiusura;
    private int anno;
    private String luogo;
    private ArrayList<Atleta> partecipanti;
    private ArrayList<Sport> sport;

    public Olimpiade(LocalDate dataApertura, LocalDate dataChiusura, int anno, String luogo){

        this.dataApertura = dataApertura;
        this.dataChiusura = dataChiusura;
        this.anno = anno;
        this.luogo = luogo;

    }
    public Olimpiade(LocalDate dataApertura, LocalDate dataChiusura, int anno, String luogo,ArrayList<Atleta> partecipanti, ArrayList<Sport> sport){

        this.dataApertura = dataApertura;
        this.dataChiusura = dataChiusura;
        this.anno = anno;
        this.luogo = luogo;
        this.partecipanti = partecipanti;
        this.sport = sport;

    }


    // GETTER
    public LocalDate getDataApertura(){
        return this.dataApertura;
    }

    public LocalDate getDataChiusura(){
        return this.dataChiusura;
    }

    public int getAnno(){
        return this.anno;
    }

    public String getLuogo(){
        return this.luogo;
    }

    public ArrayList<Atleta> getAtleti(){
        return this.partecipanti;
    }

    public ArrayList<Sport> getSport(){
        return this.sport;
    }

    public void aggiungiPartecipanti(Atleta nuovoAtleta){
        this.partecipanti.add(nuovoAtleta);
    }

    public void aggiungiSport(Sport nuovoSport){
        this.sport.add(nuovoSport);
    }
}
