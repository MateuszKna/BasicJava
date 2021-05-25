package com.company;

import java.util.concurrent.Semaphore;
public class Main {


    static class Widelec {
        Semaphore semaphore;

        public Widelec(int permits) {
            semaphore = new Semaphore(permits, true);
        }

        void Podnies() {
            semaphore.acquireUninterruptibly();
        }

        void Odloz() {
            semaphore.release();
        }
    }

    static class Filozof extends Thread {
        int filozofNR;
        Widelec[] locks;
        Semaphore extraLock;

        public Filozof(int finr, Widelec[] widelce, Semaphore table) {
            filozofNR = finr;
            locks = widelce;
            extraLock = table;
        }

        private void poczekaj() throws InterruptedException {
            Thread.sleep((int) (Math.random() * 2000));
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 50; i++) {
                    System.out.println("Filozof nr " + filozofNR + " rozmyśla");
                    poczekaj();

                    extraLock.acquireUninterruptibly();
                    locks[filozofNR].Podnies();
                    locks[(filozofNR + 1) % locks.length].Podnies();
                    System.out.println("Filozof nr " + filozofNR + " podnosi widelce");
                    poczekaj();

                    System.out.println("Filozof nr " + filozofNR + " odklada widelce");
                    locks[filozofNR].Odloz();
                    locks[(filozofNR + 1) % locks.length].Odloz();
                    extraLock.release();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        int ilosc_widelcow = 5;
        Widelec[] widelce = new Widelec[ilosc_widelcow];
        Semaphore stol = new Semaphore(ilosc_widelcow - 1);

        for (int i = 0; i < ilosc_widelcow; i++) {
            widelce[i] = new Widelec(1);
        }

        for (int i = 0; i < ilosc_widelcow; i++) {
            (new Filozof(i, widelce, stol)).start();
        }
    }
}