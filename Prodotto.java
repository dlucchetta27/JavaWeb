import java.util.ArrayList;
import java.util.Scanner;



         // creazione classe prodotto
class Prodotto {
    private String nome;
    private double prezzo;
    private int quantita;


            //formattazione prodotto
    public Prodotto(String nome, double prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}


            //creazione catalogo
class CatalogoProdotti {
    public static void main(String[] args) {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(new Prodotto("Prodotto1", 19.99, 10));
        prodotti.add(new Prodotto("Prodotto2", 29.99, 5));
        prodotti.add(new Prodotto("Prodotto3", 9.99, 15));
           
        
        //inserimento credenziali d'accesso 
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        
        
        do { //scelta utente o amministratore
            System.out.println("Benvenuto al Catalogo Prodotti!");
            System.out.println("1. Accedi come Utente");
            System.out.println("2. Accedi come Amministratore");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();  

           
           
            switch (scelta) {
                case 1: //utente
                    System.out.print("Inserisci il nome utente: ");
                    username = scanner.nextLine();
                    System.out.print("Inserisci la password: ");
                    password = scanner.nextLine();

                    if (username.equals("Utente") && password.equals("password")) {
                        accessoComeUtente(prodotti, scanner);
                    } else {
                        System.out.println("Credenziali non valide. Riprova.");
                    }
                    break;
               
               
                    case 2:             //credenziali amministratore
                    System.out.print("Inserisci il nome utente: ");
                    username = scanner.nextLine();
                    System.out.print("Inserisci la password: ");
                    password = scanner.nextLine();

                    if (username.equals("Amministratore") && password.equals("adminpass")) {
                        accessoComeAmministratore(prodotti, scanner);
                    } else {
                        System.out.println("Credenziali non valide. Riprova.");
                    }
                    break;
                
                
                    case 3:             //uscita
                    System.out.println("Arrivederci!");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (true);
    }

   
   
   
    private static void accessoComeUtente(ArrayList<Prodotto> prodotti, Scanner scanner) {
        System.out.println("Accesso come Utente riuscito!");
            //credenziali corrette
        while (true) {
            System.out.println("Menu Utente:");
            System.out.println("1. Visualizza Prodotti");
            System.out.println("2. Esci");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();  

            switch (scelta) {
                case 1:
                    visualizzaProdotti(prodotti);
                    break;
                case 2:
                    System.out.println("Uscita dalla sessione Utente.");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

   
   
   
    private static void accessoComeAmministratore(ArrayList<Prodotto> prodotti, Scanner scanner) {
        System.out.println("Accesso come Amministratore riuscito!");
            //credenziali amministratori corrette
        while (true) {
            System.out.println("Menu Amministratore:");
            System.out.println("1. Visualizza Prodotti");
            System.out.println("2. Aggiungi Prodotto");
            System.out.println("3. Rimuovi Prodotto");
            System.out.println("4. Modifica Prodotto");
            System.out.println("5. Cerca Prodotto");
            System.out.println("6. Esci");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();  
            switch (scelta) {
                case 1:
                    visualizzaProdotti(prodotti);
                    break;
                case 2:
                    aggiungiProdotto(prodotti, scanner);
                    break;
                case 3:
                    rimuoviProdotto(prodotti, scanner);
                    break;
                case 4:
                    modificaProdotto(prodotti, scanner);
                    break;
                case 5:
                    cercaProdotto(prodotti, scanner);
                    break;
                case 6:
                    System.out.println("Uscita dalla sessione Amministratore.");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
   
   
   
             //ricerca dei prodotti
    private static void visualizzaProdotti(ArrayList<Prodotto> prodotti) {
        System.out.println("Prodotti disponibili:");
        for (Prodotto prodotto : prodotti) {
            System.out.println("Nome: " + prodotto.getNome() + " - Prezzo: $" + prodotto.getPrezzo() + " - Quantità disponibile: " + prodotto.getQuantita());
        }
    }
   
   
   
             //aggiunta nuovi prodotti
    private static void aggiungiProdotto(ArrayList<Prodotto> prodotti, Scanner scanner) {
        System.out.print("Inserisci il nome del nuovo prodotto: ");
        String nomeProdotto = scanner.nextLine();
        System.out.print("Inserisci il prezzo del nuovo prodotto: ");
        double prezzoProdotto = scanner.nextDouble();
        System.out.print("Inserisci la quantità disponibile del nuovo prodotto: ");
        int quantitaProdotto = scanner.nextInt();
        scanner.nextLine();  // Consuma il newline

        prodotti.add(new Prodotto(nomeProdotto, prezzoProdotto, quantitaProdotto));
        System.out.println("Prodotto aggiunto con successo.");
    }
            
    
    
                //rimozione dei prodotti
    private static void rimuoviProdotto(ArrayList<Prodotto> prodotti, Scanner scanner) {
        System.out.print("Inserisci il nome del prodotto da rimuovere: ");
        String nomeDaRimuovere = scanner.nextLine();
        boolean rimosso = false;
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).getNome().equals(nomeDaRimuovere)) {
                prodotti.remove(i);
                rimosso = true;
                System.out.println("Prodotto rimosso con successo.");
                break;
            }
        }
        if (!rimosso) {
            System.out.println("Nessun prodotto con il nome specificato è stato trovato.");
        }
    }
          
    
    
                //modifica prodotti
    private static void modificaProdotto(ArrayList<Prodotto> prodotti, Scanner scanner) {
        System.out.print("Inserisci il nome del prodotto da modificare: ");
        String nomeDaModificare = scanner.nextLine();
        boolean trovato = false;

        for (Prodotto prodotto : prodotti) {
            if (prodotto.getNome().equals(nomeDaModificare)) {
                System.out.println("Prodotto trovato: " + prodotto.getNome() + " - Prezzo: $" + prodotto.getPrezzo() + " - Quantità disponibile: " + prodotto.getQuantita());
                System.out.print("Inserisci il nuovo nome: ");
                String nuovoNome = scanner.nextLine();
                System.out.print("Inserisci il nuovo prezzo: ");
                double nuovoPrezzo = scanner.nextDouble();
                System.out.print("Inserisci la nuova quantità disponibile: ");
                int nuovaQuantita = scanner.nextInt();
                scanner.nextLine();  // Consuma il newline

                prodotto.setNome(nuovoNome);
                prodotto.setPrezzo(nuovoPrezzo);
                prodotto.setQuantita(nuovaQuantita);
                System.out.println("Prodotto modificato con successo.");
                trovato = true;
                break;
            }
        }

        if (!trovato) {
            System.out.println("Nessun prodotto con il nome specificato è stato trovato.");
        }
    }
         
    
    
                //ricerca dei prodotti
    private static void cercaProdotto(ArrayList<Prodotto> prodotti, Scanner scanner) {
        System.out.print("Inserisci il criterio di ricerca (ad esempio, nome del prodotto): ");
        String criterioRicerca = scanner.nextLine();
        boolean trovato = false;

        System.out.println("Prodotti corrispondenti al criterio di ricerca:");
        for (Prodotto prodotto : prodotti) {
            if (prodotto.getNome().contains(criterioRicerca)) {
                System.out.println("Nome: " + prodotto.getNome() + " - Prezzo: $" + prodotto.getPrezzo() + " - Quantità disponibile: " + prodotto.getQuantita());
                trovato = true;
            }
        }

        if (!trovato) {
            System.out.println("Nessun prodotto corrispondente al criterio di ricerca è stato trovato.");
        }
    }
}
