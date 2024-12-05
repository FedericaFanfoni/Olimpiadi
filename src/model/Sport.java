package model;

public class Sport {

    private String nomeSport;
    private boolean squadra;
    private int id;


    public String getNomeSport(){
        return this.nomeSport;
    }

    public boolean getSquadra(){
        return this.squadra;
    }

    public int getId(){return this.id;}

    public void setNomeSport(String nomeSport){
        this.nomeSport = nomeSport;
    }

    public void setSquadra(boolean squadra){
        this.squadra = squadra;
    }

    public void setId(int id){
        this.id = id;
    }

}
