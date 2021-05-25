package com.company;

public class Main {

    	static class Watek extends Thread{
    		public Watek(){}
    		public void run(){
				try {
					Thread.sleep((int) (Math.random() * 10000.0));
				}
				catch(InterruptedException err){System.out.print("Omzi");}
    			System.out.println(Thread.currentThread().getId());}
    	}

    	static class Watekk implements Runnable{
        public Watekk(){}
        public void run(){
			try {
				Thread.sleep((int) (Math.random() * 10000.0));
			}
			catch(InterruptedException err){System.out.print("Omzi");}
        	System.out.println(Thread.currentThread().getId());}

        }

    public static void main(String[] args) {

        		Watek w = new Watek();
        		w.start();

        		Watekk wRun = new Watekk();
        		Thread t = new Thread(wRun);
        		t.start();

        		try{
        			w.join();
        			t.join();
        		}
        		catch(Exception err){System.out.print("Omzi");}


        		
    	}

}
