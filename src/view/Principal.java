package view;

import java.util.concurrent.Semaphore;

import controller.CassinoController;

public class Principal {
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1); 
		
		for(int id = 1; id<11; id++) {
			CassinoController cs = new CassinoController(id, semaforo); 
			cs.start();
		}
		
	}

}
