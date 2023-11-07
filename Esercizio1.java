
import java.util.*;


class Giocattolo {
    private int id;
    private String nome;
    private double prezzo;
    private int etaConsigliata;

    public Giocattolo(int id, String nome, double prezzo, int etaConsigliata) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.etaConsigliata = etaConsigliata;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getEtaConsigliata() {
        return etaConsigliata;
    }
}

class Cliente {
    private int id;
    private String nome;
    private String email;

    public Cliente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}

class Vendita {
    private Giocattolo giocattolo;
    private Cliente cliente;

    public Vendita(Giocattolo giocattolo, Cliente cliente) {
        this.giocattolo = giocattolo;
        this.cliente = cliente;
    }

    public Giocattolo getGiocattolo() {
        return giocattolo;
    }

    public Cliente getCliente() {
        return cliente;
    }
}

class Inventario {
    private List<Giocattolo> giocattoli;

    public Inventario() {
        giocattoli = new ArrayList<>();
    }

    public void aggiungiGiocattolo(Giocattolo giocattolo) {
        giocattoli.add(giocattolo);
    }

    public List<Giocattolo> getGiocattoliDisponibili() {
        return giocattoli;
    }
}

abstract class RegistroVendite {
    public Vendita[] vendite;
    abstract void registraVendita(Giocattolo giocattolo, Cliente cliente);
    abstract void visualizzaVendite();
}

class NegozioGiocattoli {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        RegistroVendite registroVendite = new RegistroVendite() {
            private List<Vendita> vendite = new ArrayList<>();

            @Override
            void registraVendita(Giocattolo giocattolo, Cliente cliente) {
                Vendita vendita = new Vendita(giocattolo, cliente);
                vendite.add(vendita);
            }

            @Override
            void visualizzaVendite() {
                System.out.println("Vendite effettuate:");
                for (Vendita vendita : vendite) {
                    System.out.println("Cliente: " + vendita.getCliente().getNome() + ", Giocattolo: " + vendita.getGiocattolo().getNome());
                }
            }
        };

        Giocattolo giocattolo1 = new Giocattolo(1, "Puzzle", 15.99, 6);
        Giocattolo giocattolo2 = new Giocattolo(2, "Lego", 24.99, 7);

        inventario.aggiungiGiocattolo(giocattolo1);
        inventario.aggiungiGiocattolo(giocattolo2);

        Cliente cliente1 = new Cliente(1, "Mario", "mario@email.com");
        Cliente cliente2 = new Cliente(2, "Luigi", "luigi@email.com");

        registroVendite.registraVendita(giocattolo1, cliente1);
        registroVendite.registraVendita(giocattolo2, cliente2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Benvenuto nel negozio di giocattoli!");
            System.out.println("1. Visualizza giocattoli disponibili");
            System.out.println("2. Registra una vendita");
            System.out.println("3. Visualizza vendite");
            System.out.println("4. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();  // Consuma la riga vuota dopo la lettura dell'input numerico

            switch (scelta) {
                case 1:
                    List<Giocattolo> giocattoliDisponibili = inventario.getGiocattoliDisponibili();
                    System.out.println("Giocattoli disponibili:");
                    for (Giocattolo giocattolo : giocattoliDisponibili) {
                        System.out.println("ID: " + giocattolo.getId() + ", Nome: " + giocattolo.getNome() + ", Prezzo: " + giocattolo.getPrezzo());
                    }
                    break;
                case 2:
                    System.out.println("Inserisci l'ID del giocattolo venduto:");
                    int idGiocattolo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci l'ID del cliente:");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    Giocattolo giocattoloVenduto = null;
                    Cliente cliente = null;

                    for (Giocattolo giocattolo : inventario.getGiocattoliDisponibili()) {
                        if (giocattolo.getId() == idGiocattolo) {
                            giocattoloVenduto = giocattolo;
                            break;
                        }
                    }

                    if (giocattoloVenduto == null) {
                        System.out.println("Giocattolo non trovato.");
                        continue;
                    }

                    for (Vendita vendita : registroVendite.vendite) {
                        if (vendita.getCliente().getId() == idCliente) {
                            cliente = vendita.getCliente();
                            break;
                        }
                    }

                    if (cliente == null) {
                        System.out.println("Cliente non trovato.");
                        continue;
                    }

                    registroVendite.registraVendita(giocattoloVenduto, cliente);
                    System.out.println("Vendita registrata con successo.");
                    break;
                case 3:
                    registroVendite.visualizzaVendite();
                    break;
                case 4:
                    System.out.println("Arrivederci!");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida. Riprova.");
            scanner.close();
                }

        }
    }
}


