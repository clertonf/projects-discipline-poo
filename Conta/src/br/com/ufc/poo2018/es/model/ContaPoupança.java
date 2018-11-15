

package br.com.ufc.poo2018.es.model;


public class ContaPoupança extends ContaBancaria implements Imprimivel {
    
    private double taxadeOperacao;
    public ContaPoupança(int numConta, double saldo) {
        super(numConta, saldo);
        this.taxadeOperacao = 0.03;
    }

    @Override
    public boolean sacar(double valor) {
        boolean aux = false;
        double resultado;
        double saldo = getSaldo();
        resultado = this.taxadeOperacao * valor;
        if(valor>getSaldo()){
            aux = false;
        }
        else
            if ( valor <= getSaldo()){
            saldo -= valor + resultado;
            setSaldo(saldo);
            aux = true;
        }
       if(aux = false){
           System.out.println("Valor menor que o saldo");
           return false;
       }
       else{
           System.out.println("Valor retirado");
           return true;
       }
  }

    @Override
    public boolean depositar(double valor) {
        double resultado;
        resultado = this.taxadeOperacao * valor;
        valor -= resultado;
        setSaldo(valor + getSaldo());
         System.out.println("Valor adicionado");
        return true;
    }

    @Override
    public String mostrarDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
