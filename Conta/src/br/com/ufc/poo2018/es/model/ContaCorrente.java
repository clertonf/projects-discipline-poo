

package br.com.ufc.poo2018.es.model;


public  class ContaCorrente extends ContaBancaria implements Imprimivel {

      private double limite;
    
    public ContaCorrente(int numConta, double saldo) {
        super(numConta, saldo);
        this.limite = 100;
       }
      /**
     * @return the limite
     */
    public double getLimite() {
        return limite;
    }

    /**
     * @param limite the limite to set
     */
    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor) {
        boolean aux = false;
        double saldo = getSaldo();
        if(valor>getSaldo() + this.limite){
            aux = false;
       }
       else
            if(valor<=getSaldo()){
                setSaldo(getSaldo() - valor);
            aux = true;
            }
        else
              if(valor>getSaldo() && valor<=getSaldo() + this.limite){
                  double result;
                  result = getSaldo() - valor;
                  saldo = result;
                  setSaldo(saldo);
                  result += this.limite;
                  aux = true;
                 
              }
        if(aux == false){
            System.out.println("Saldo insuficiente");
            return false;
        }
        else{
            System.out.println("Valor retirado");
            return true;
        }
        
    }

    @Override
    public boolean depositar(double valor) {
        setSaldo(valor + getSaldo());
       System.out.println("Valor debitado");
        return true;
    }

    @Override
    public String mostrarDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
