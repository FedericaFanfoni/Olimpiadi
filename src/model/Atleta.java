package model;

import java.time.LocalDate;

public class Atleta {

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private int altezza;
    private Sport sport;
    private int id;

    public Atleta(String nome, String cognome, LocalDate dataNascita, int altezza){
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.altezza = altezza;
    }

    public Atleta(String nome, String cognome, LocalDate dataNascita, int altezza, Sport sport){
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.altezza = altezza;
        this.sport = sport;
    }


    // GETTER
   public String getNome(){
        return this.nome;
   }

   public String getCognome(){
        return  this.cognome;
   }

   public LocalDate getDataNascita(){
        return this.dataNascita;
   }

   public int getAltezza(){
        return this.altezza;
   }

   public Sport getSport(){
        return this.sport;
   }

   public int getId(){
        return this.id;
   }

   // SETTER
   public void setNome(String nome){
        this.nome = nome;
   }

   public void setCognome(String cognome){
        this.cognome = cognome;
   }

   public void setDataNascita(LocalDate dataNascita){
        this.dataNascita = dataNascita;
   }

   public void setAltezza(int altezza){
        this.altezza = altezza;
   }

   public void setSport(Sport sport){
        this.sport = sport;
   }

   public void setID(int id){
        this.id = id;
   }


}
