package service;

import model.Olimpiade;
import repository.OlimpiadeRepository;

import java.util.ArrayList;

public class OlimpiadeService {

    OlimpiadeRepository or = new OlimpiadeRepository();

    public void createOlimpiade(Olimpiade olimpiade){
        or.createOlimpiade(olimpiade);
    }
    public ArrayList<Olimpiade> readOlimpiadi(){
        return or.readOlimpiadi();
    }
    public void updateOlimpiade(Olimpiade olimpiade, int vecchioAnno){
        or.updateOlimpiade(olimpiade, vecchioAnno);
    }
    public void deleteOlimpiade(int anno){
        or.deleteOlimpiade(anno);
    }
}
