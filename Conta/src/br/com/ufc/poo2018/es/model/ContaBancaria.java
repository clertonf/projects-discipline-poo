
package br.com.ufc.poo2018.es.model;


public abstract class ContaBancaria {
    
    private int numConta;
    private double saldo;
    
    public ContaBancaria(int numConta, double saldo){
        this.numConta = numConta;
        this.saldo = saldo;
        }
    public abstract boolean sacar(double valor);
    public abstract boolean  depositar(double valor);

    /**
     * @return the numConta
     */
    public int getNumConta() {
        return numConta;
    }

    /**
     * @param numConta the numConta to set
     */
    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
