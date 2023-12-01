package com.company;
import java.util.Scanner;

public class ProstyKantor {
    private static String haslo;
    private static final Scanner skaner = new Scanner(System.in);

    private static double kursUSDdoEUR = 0.9;
    private static double kursEURdoPLN = 4.40;
    private static double kursPLNdoUSD = 4.00;

    private static double saldoUSD = 1000.0;
    private static double saldoEUR = 1000.0;
    private static double saldoPLN = 1000.0;


    public static void main(String[] args) {
        ustawHaslo();

        System.out.println("Witamy w Kantorze Piotra!"); // wyświetla na konsoli
        if (logowanie()) { //instrukcja warunkowa
            boolean kontynuuj = true;
            int opcja;

            do {
                wyswietlMenuGlowne();
                opcja = skaner.nextInt();
                skaner.nextLine();

                switch (opcja) {
                    case 1:
                        wyswietlKursyWalut();
                        break;
                    case 2:
                        pokazSaldo();
                        break;
                    case 3:
                        wykonajWymiane();
                        break;
                    case 4:
                        zmienHaslo();
                        logowaniePoZmianieHasla();
                        break;
                    case 5:
                        System.out.println("Dziękujemy za skorzystanie z Kantoru Piotra. Do widzenia!");
                        kontynuuj = false;
                        break;
                    default:
                        System.out.println("Wybrano nieprawidłową opcję.");
                        break;
                }
            } while (kontynuuj);
        } else {
            System.out.println("Nieprawidłowe hasło. Program zostanie zamknięty.");
        }

        skaner.close();
    }

    private static void ustawHaslo() {
        System.out.print("Ustal swoje hasło: ");
        haslo = skaner.nextLine();
    }

    private static boolean logowanie() {
        System.out.print("Wprowadź swoje hasło: ");
        String wprowadzoneHaslo = skaner.nextLine();
        return haslo.equals(wprowadzoneHaslo);
    }

    private static void zmienHaslo() {
        System.out.print("Podaj nowe hasło: ");
        haslo = skaner.nextLine();
        System.out.println("Hasło zostało zmienione.");
    }

    private static void logowaniePoZmianieHasla() {
        boolean poprawneLogowanie = false;
        int liczbaProb = 3;

        do {
            System.out.print("Wprowadź swoje hasło: ");
            String wprowadzoneHaslo = skaner.nextLine();

            if (haslo.equals(wprowadzoneHaslo)) {
                System.out.println("Logowanie udane.");
                poprawneLogowanie = true;
            } else {
                liczbaProb--;
                System.out.println("Błędne hasło. Pozostałe próby: " + liczbaProb);
            }

        } while (!poprawneLogowanie && liczbaProb > 0);

        if (!poprawneLogowanie) {
            System.out.println("Przekroczono liczbę prób logowania. Program zostanie zamknięty.");
            System.exit(0);
        }
    }

    private static void wyswietlMenuGlowne() {
        System.out.println("Proszę wybrać opcję:");
        System.out.println("1 - Pokaż kursy walut");
        System.out.println("2 - Zobacz saldo");
        System.out.println("3 - Wymień walutę");
        System.out.println("4 - Zmiana hasła");
        System.out.println("5 - Wyjście");
        System.out.print("Wpisz swoją opcję: ");
    }

    private static void wyswietlKursyWalut() {
        System.out.printf("Kurs wymiany USD na EUR: %.2f", kursUSDdoEUR);
        System.out.printf("Kurs wymiany EUR na USD: %.2f", (1 / kursUSDdoEUR));

        System.out.printf("Kurs wymiany EUR na PLN: %.2f", kursEURdoPLN);
        System.out.printf("Kurs wymiany PLN na EUR: %.2f", (1 / kursEURdoPLN));

        System.out.printf("Kurs wymiany PLN na USD: %.2f", (1 / kursPLNdoUSD));
        System.out.printf("Kurs wymiany USD na PLN: %.2f", kursPLNdoUSD);
    }

    private static void pokazSaldo() {
        System.out.println("Twoje saldo:");
        System.out.printf("USD: %.2f", saldoUSD);
        System.out.printf("EUR: %.2f", saldoEUR);
        System.out.printf("PLN: %.2f", saldoPLN);
    }

    private static void wykonajWymiane() {
        System.out.println("Wybierz opcję wymiany:");
        System.out.println("1 - USD na EUR");
        System.out.println("2 - EUR na USD");
        System.out.println("3 - EUR na PLN");
        System.out.println("4 - PLN na EUR");
        System.out.println("5 - USD na PLN");
        System.out.println("6 - PLN na USD");

        int opcjaWymiany = skaner.nextInt();
        skaner.nextLine();

        System.out.print("Podaj kwotę do wymiany: ");
        double kwota = skaner.nextDouble();
        skaner.nextLine();

        switch (opcjaWymiany) {
            case 1:
                if (kwota <= saldoUSD) {
                    double wymienionaKwota = kwota * kursUSDdoEUR;
                    saldoUSD -= kwota;
                    saldoEUR += wymienionaKwota;
                    System.out.printf("Wymieniono %.2f USD na %.2f EUR.", kwota, wymienionaKwota);
                } else {
                    System.out.println("Niewystarczające środki do wymiany.");
                }
                break;
            case 2:
                if (kwota <= saldoEUR) {
                    double wymienionaKwota = kwota / kursUSDdoEUR;
                    saldoUSD += wymienionaKwota;
                    saldoEUR -= kwota;
                    System.out.printf("Wymieniono %.2f EUR na %.2f USD.\n", kwota, wymienionaKwota);
                } else {
                    System.out.println("Niewystarczające środki do wymiany.");
                }
                break;
            case 3:
                if (kwota <= saldoEUR) {
                    double wymienionaKwota = kwota * kursEURdoPLN;
                    saldoEUR -= kwota;
                    saldoPLN += wymienionaKwota;
                    System.out.printf("Wymieniono %.2f EUR na %.2f PLN.\n", kwota, wymienionaKwota);
                } else {
                    System.out.println("Niewystarczające środki do wymiany.");
                }
                break;
            case 4:
                if (kwota <= saldoPLN) {
                    double wymienionaKwota = kwota / kursEURdoPLN;
                    saldoEUR += wymienionaKwota;
                    saldoPLN -= kwota;
                    System.out.printf("Wymieniono %.2f PLN na %.2f EUR.\n", kwota, wymienionaKwota);
                } else {
                    System.out.println("Niewystarczające środki do wymiany.");
                }
                break;
            case 5:
                if (kwota <= saldoUSD) {
                    double wymienionaKwota = kwota / kursPLNdoUSD;
                    saldoUSD -= kwota;
                    saldoPLN += wymienionaKwota;
                    System.out.printf("Wymieniono %.2f USD na %.2f PLN.\n", kwota, wymienionaKwota);
                } else {
                    System.out.println("Niewystarczające środki do wymiany.");
                }
                break;
            case 6:
                if (kwota <= saldoPLN) {
                    double wymienionaKwota = kwota * kursPLNdoUSD;
                    saldoUSD += wymienionaKwota;
                    saldoPLN -= kwota;
                    System.out.printf("Wymieniono %.2f PLN na %.2f USD.\n", kwota, wymienionaKwota);
                } else {
                    System.out.println("Niewystarczające środki do wymiany.");
                }
                break;
            default:
                System.out.println("Nieprawidłowa opcja wymiany.");
                break;
        }
    }
}




