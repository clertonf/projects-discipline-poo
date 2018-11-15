

package br.com.ufc.poo2018.es.exec;

import br.com.ufc.poo2018.es.model.*;


public class Principal {
    public static void main(String args []){
        ContaBancaria cc = new ContaCorrente(0645, 200);
        ContaBancaria cp = new ContaPoupança(2405, 230);
        Relatório rel = new Relatório();
        
    //    cc.sacar(600);
    //    cc.depositar(300);
   // System.out.println("Saldo"+cc.getSaldo());
      
  // cp.sacar(150);
  cp.depositar(500);
  System.out.println("Saldo:" + cp.getSaldo());
   
    }
    
    

}
