package com.company;

public class Main {

    static class Platforma{
        String przedmiot;
        boolean prz = false;
        synchronized void set(String przedmiot) throws InterruptedException{
            while (prz) {this.wait();}
            this.przedmiot = przedmiot;
            prz = true;
            notifyAll();
        }

        synchronized String get() throws InterruptedException{
            while (!prz) {this.wait();}
            this.przedmiot = przedmiot;
            prz = false;
            notifyAll();
            return przedmiot;
        }

    }

    static class Producent extends Thread{
        Platforma p;
        int Idprzedmiot;
        public Producent(Platforma p){this.p = p;}
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    p.set("Towar id: " + Idprzedmiot);
                    Idprzedmiot++;
                }

            } catch (InterruptedException ex) { }


        }

    }

    static class Konsument extends Thread{
        Platforma p;
        public Konsument(Platforma p){
            this.p = p;
        }
        public void run(){
            try {
                while(true){
                    Thread.sleep(1000);
                    String s = p.get();
                    System.out.println("Pobrano towar: " + s );
                }
            } catch(InterruptedException ex){}
        }


    }

    public static void main(String[] args) throws InterruptedException {

            Platforma p = new Platforma();
            Thread t1 = new Thread(new Producent(p));
            Thread t2 = new Thread(new Konsument(p));
            t1.start();
            t2.start();
            Thread.sleep(10000);
            t1.interrupt();
            t2.interrupt();
            System.out.println("Koniec");
        }
    }

