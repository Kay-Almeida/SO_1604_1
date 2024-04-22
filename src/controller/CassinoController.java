package controller;

import java.util.concurrent.Semaphore;

public class CassinoController extends Thread {
	
	int id; 
	private static int chegada = 0; 
	static double valor = 5000;
	Semaphore semaforo; 
	
	public CassinoController (int id, Semaphore semaforo) {
		this.id=id; 
		this.semaforo = semaforo; 
		
	}
	
	public void run() {
		
		int pontos = 0;
		
		while(pontos<5) {
		
		int dado1 = (int) ((Math.random()*6)+1); 
		int dado2 = (int) ((Math.random()*6)+1); 
		
		System.out.println("Apostador: "+ id+ " 1° Dado: "+ dado1+ " 2° Dado: "+ dado2+ " Soma: "+ (dado1+dado2));
		
		if(dado1+dado2==7 || dado1+dado2==11 ) {
			pontos = pontos + 1; 
			System.out.println("Apostador: "+ id+ " Total de pontos: "+ pontos);

		}
		}
		
		chegada(); 
	}
	
	public void chegada() {
		try {
			semaforo.acquire();
			chegada++; 
			if(chegada < 4) {
				System.out.println(chegada + "° Lugar, Apostador: "+id+ " Ganhou R$"+ valor );
			}else {
				System.out.println("O Apostador "+ id+ " terminou fora do Top 3");
			}
			
			valor = (valor - 1000); 
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}

	}
}
