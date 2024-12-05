import model.Olimpiade;
import model.Sport;
import model.Atleta;
import service.AtletaService;
import service.OlimpiadeService;
import service.SportService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) { sceltaMenu(); }

    public static void sceltaMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scegli in quale menù andare.");
        System.out.println("1. Sport");
        System.out.println("2. Atleti");
        System.out.println("3. Olimpiadi");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();

        avvioMenu(choice);
    }

    public static void avvioMenu(int choice){
        Scanner scanner = new Scanner(System.in);
        if(choice == 1){
            int select;
            do{
                System.out.println("***Menù Sport***");
                System.out.println("1. Crea uno sport");
                System.out.println("2. Lista degli sport");
                System.out.println("3. Modifica uno sport");
                System.out.println("4. Elimina uno sport");
                System.out.println("5. Torna alla selezione del menù");
                select = scanner.nextInt();

                switch (select) {
                    case 1:
                        createSport();
                        break;
                    case 2:
                        readSport();
                        break;
                    case 3:
                        updateSport();
                        break;
                    case 4:
                        deleteSport();
                        break;
                    case 5:
                        sceltaMenu();
                    default:
                        System.out.println("Scelta non valida");
                        avvioMenu(choice);
                }

            }while (choice != 6);
            scanner.close();
        } else if(choice == 2){
            int select;
            do{
                System.out.println("***Menù Atleta***");
                System.out.println("1. Iscrivi un atleta");
                System.out.println("2. Lista degli atleti");
                System.out.println("3. Modifica un atleta");
                System.out.println("4. Elimina un atleta");
                System.out.println("5. Torna alla selezione del menù");
                select = scanner.nextInt();

                switch (select) {
                    case 1:
                        createAtleta();
                        break;
                    case 2:
                        readAtleti();
                        break;
                    case 3:
                        updateAtleta();
                        break;
                    case 4:
                        deleteAtleta();
                        break;
                    case 5:
                        sceltaMenu();
                    default:
                        System.out.println("Scelta non valida");
                        avvioMenu(choice);
                }

            }while (choice != 5);
            scanner.close();
        }else if (choice == 3){
            int select;
            do{
                System.out.println("***Menù Olimpiadi***");
                System.out.println("1. Crea olimpiade");
                System.out.println("2. Lista delle olimpiadi");
                System.out.println("3. Modifica olimpiade");
                System.out.println("4. Elimina olimpiade");
                System.out.println("5. Torna alla selezione del menù");
                select = scanner.nextInt();

                switch (select) {
                    case 1:
                        createOlimpiade();
                        break;
                    case 2:
                        readOlimpiadi();
                        break;
                    case 3:
                        updateOlimpiade();
                        break;
                    case 4:
                        deleteOlimpiade();
                        break;
                    case 5:
                        sceltaMenu();
                    default:
                        System.out.println("Scelta non valida");
                        avvioMenu(choice);
                }

            }while (choice != 5);
            scanner.close();
        }
    }

    public static void createSport(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nome dello sport: ");
        String nomeSport = scanner.nextLine();

        System.out.println("E' uno sport di squadra?");
        System.out.println("1. SI");
        System.out.println("2. NO");

        int response = scanner.nextInt();
        boolean squadra;

        if(response == 1){

            squadra = true;
        } else {

            squadra = false;
        }

        SportService sportService = new SportService();
        sportService.createSport(nomeSport, squadra);
    }
    public static void readSport(){
        SportService sportService = new SportService();

        for(Sport sport : sportService.readSport()){
            System.out.println(sport.getNomeSport());
        }
    }
    public static void updateSport(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il nuovo nome dello sport: ");
        String nomeSport = scanner.nextLine();

        System.out.println("E' uno sport di squadra?");
        System.out.println("1. SI");
        System.out.println("2. NO");

        int response = scanner.nextInt();
        boolean squadra;

        if(response == 1){
            squadra = true;
        } else {
            squadra = false;
        }

        System.out.println("Inserisci l'id dello sport che vuoi modificare");
        int idSport = scanner.nextInt();

        SportService sportService = new SportService();
        sportService.updateSport(nomeSport, squadra, idSport);
    }
    public static void deleteSport(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'id dello sport che vuoi eliminare");
        int idSport = scanner.nextInt();

        SportService sportService = new SportService();
        sportService.deleteSport(idSport);
    }

    public static void createAtleta(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nome dell'atleta: ");
        String nome = scanner.nextLine();

        System.out.println("Inserisci cognome dell'atleta: ");
        String cognome = scanner.nextLine();

        System.out.println("Inserisci la data di nascita");
        String dataNascitaString = scanner.nextLine();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascita = LocalDate.parse(dataNascitaString, dateFormatter);

        System.out.println("Inserisci l'altezza");
        int altezza = scanner.nextInt();

        System.out.println("Inserisci nome dello sport praticato");
        String nomeSport = scanner.next();
        SportService sportService = new SportService();
        Sport sport = sportService.searchSportByName(nomeSport);

        AtletaService atletaService = new AtletaService();
        atletaService.createAtleta(nome, cognome, dataNascita, altezza, sport);
    }
    public static void readAtleti(){
        AtletaService atletaService = new AtletaService();

        for(Atleta atleta : atletaService.readAtleti()){
            System.out.println(atleta.getNome());
        }
    }
    public static void updateAtleta(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci  il nuovo nome: ");
        String nome = scanner.nextLine();

        System.out.println("Inserisci il nuovo cognome: ");
        String cognome = scanner.nextLine();

        System.out.println("Inserisci la nuova data di nascita");
        String dataNascitaString = scanner.nextLine();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascita = LocalDate.parse(dataNascitaString, dateFormatter);

        System.out.println("Inserisci la nuova altezza");
        int altezza = scanner.nextInt();

        System.out.println("Inserisci nome dello sport praticato");
        String nomeSport = scanner.next();
        SportService sportService = new SportService();
        Sport sport = sportService.searchSportByName(nomeSport);

        System.out.println("Inserisci l'id dell'atleta da modificare");
        int idSport = scanner.nextInt();

        Atleta atleta =  new Atleta(nome,cognome,dataNascita,altezza,sport);

        AtletaService atletaService = new AtletaService();
        atletaService.updateAtleta(atleta, idSport);
    }
    public static void deleteAtleta(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'id dell'atleta che vuoi eliminare");
        int idAtleta = scanner.nextInt();

        AtletaService atletaService = new AtletaService();
        atletaService.deleteAtleta(idAtleta);
    }

    public static void createOlimpiade(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Inserisci la data di apertura delle olimpiadi");
        String dataAperturaString = scanner.nextLine();
        LocalDate dataApertura = LocalDate.parse(dataAperturaString, dateFormatter);

        System.out.println("Inserisci la data di chiusura delle olimpiadi");
        String dataChiusuraString = scanner.nextLine();
        LocalDate dataChiusura = LocalDate.parse(dataChiusuraString, dateFormatter);

        System.out.println("Inserisci l'anno in cui si sono svolte");
        int anno = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Inserisci il luogo in cui si sono svolte");
        String luogo = scanner.nextLine();

        Olimpiade olimpiade = new Olimpiade(dataApertura, dataChiusura, anno, luogo);

        OlimpiadeService olimpiadeService = new OlimpiadeService();
        olimpiadeService.createOlimpiade(olimpiade);
    }
    public static void readOlimpiadi(){
        OlimpiadeService olimpiadeService = new OlimpiadeService();

        for(Olimpiade olimpiade : olimpiadeService.readOlimpiadi()){
            System.out.println(olimpiade.getLuogo());
        }
    }
    public static void updateOlimpiade(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Inserisci la data di apertura delle olimpiadi");
        String dataAperturaString = scanner.nextLine();
        LocalDate dataApertura = LocalDate.parse(dataAperturaString, dateFormatter);

        System.out.println("Inserisci la data di chiusura delle olimpiadi");
        String dataChiusuraString = scanner.nextLine();
        LocalDate dataChiusura = LocalDate.parse(dataChiusuraString, dateFormatter);

        System.out.println("Inserisci l'anno in cui si sono svolte");
        int anno = scanner.nextInt();

        scanner.nextLine(); // Consuma la riga successiva

        System.out.println("Inserisci il luogo in cui si sono svolte");
        String luogo = scanner.nextLine();

        System.out.println("Inserisci l'anno delle olimpiadi che vuoi modificare");
        int vecchioAnno = scanner.nextInt();

        Olimpiade olimpiade = new Olimpiade(dataApertura, dataChiusura, anno, luogo);

        OlimpiadeService olimpiadeService = new OlimpiadeService();
        olimpiadeService.updateOlimpiade(olimpiade, vecchioAnno);
    }
    public static void deleteOlimpiade(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci l'anno delle olimpiadi che vuoi eliminare");
        int anno = scanner.nextInt();

        OlimpiadeService olimpiadeService = new OlimpiadeService();
        olimpiadeService.deleteOlimpiade(anno);
    }
}