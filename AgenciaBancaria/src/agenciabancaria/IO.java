
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package agenciabancaria;
import java.util.ArrayList;
import java.util.Scanner;



class Agencia {
        // substitui as arraylist pelo repositório;
    private Repositório<Cliente> clientes;
            public Agencia(){
                     clientes = new Repositório<Cliente>("clientes");
	}
            public boolean addCliente(String cpf){
                        for(Cliente c: clientes.getAll()){
                            if(c.getIdCliente().equals(cpf)){
                                   throw new RuntimeException("CPF já cadastrado");
		}
	}
                this.clientes.add(new Cliente(cpf));
                        return true;
	}
	
            public boolean abrirNovaConta(String cpf){
	for(Cliente c: clientes.getAll()){
                            if(c.getIdCliente().equals(cpf)){
                                c.addConta(new Conta(Conta.ultIdConta++));
                         return true;
		}
	}
                         return false;
	}
                public Repositório<Cliente> getClientes(){
                        return clientes;
	}
}

class Cliente {
           private String idCliente;
         private Repositório<Conta> contas;
	
         public Cliente(String idCliente){
                this.idCliente = idCliente;
                this.contas    = new Repositório<Conta>("contas");
                this.contas.add(new Conta(Conta.ultIdConta));
                            Conta.ultIdConta++;
                                    if(contas == null){
	throw new RuntimeException("Ops, conta nula!");
	}
}

        public boolean addConta(Conta conta){
            int qtd = 0;
                for(Conta c: contas.getAll()){
                    if(c.isAtiva()){
                        qtd++;
		}
	}
                            if(qtd == 2){
                                    throw new RuntimeException("Limite de contas ativas estourado!");
                            }
                               this.contas.add(conta);
		return true;
	}
                
        public boolean encerrarConta(int numero){
                for(Conta c : contas.getAll()){
                    if(c.getNumero() == numero){
                        if(c.getSaldo() == 0){
                            c.encerrar();
                                 return true;
		}
                                   }
	}
                                 return false;
}
	
                public String getIdCliente() {
		return idCliente;
	}
                public Repositório<Conta> getContas() {
		return contas;
	}
}
    
class Conta {
    //inicializando a conta com  1;        
                public static int ultIdConta = 1;
                private float saldo;
                private int numero;
                private Repositório<Operacao> extrato;
                private boolean ativa;
	
	public Conta(int numero){
		this.numero  = numero;
		this.saldo   = 0;
		this.extrato = new Repositório<Operacao>("extrato");
		this.ativa   = true;
	}
	
	public boolean depositar(float valor){
		if(valor <= 0){
			return false;
		}
		
		this.saldo += valor;
		this.extrato.add(new Operacao("depósito", valor, saldo));
		return true;
	}
	
        public boolean sacar(float valor){
                   if(valor <= 0){
	throw new RuntimeException("Valor negativo");
	}
		
                if(valor > saldo){
	throw new RuntimeException("Tentativa de saque maior do que o saldo!");
	}
		
	this.saldo -= valor;
                        this.extrato.add(new Operacao("saque", valor, saldo));
	return true;
	}
	
	public boolean transferir(Conta other, float valor){
	if(!other.isAtiva()){
                            throw new RuntimeException("A conta destino está inativa");
                        }
		
                if(this.sacar(valor)){
	other.depositar(valor);
	return true;
	}
	return false;
	}
	
            public void encerrar(){
	this.ativa = false;
	}
	
            public float getSaldo() {
	return saldo;
	}

            public int getNumero() {
	return numero;
	}

            public Repositório<Operacao> getExtrato() {
                    return extrato;
	}
            
            public boolean isAtiva() {
                 return ativa;
	}
            
            public String toString(){
                  return numero + " - " + saldo + " - " + extrato + " - "+ ativa;
	}
}
class Operacao {
                private String descricao;
                private float valor;
                private float saldoParcial;
	
        public Operacao(String descricao, float valor, float saldoParcial){
                this.descricao = descricao;
                this.valor = valor;
                this.saldoParcial = saldoParcial;
            }
	
        public String getDescricao(){
	return descricao;
	}   
        public void setDescricao(String descricao){
                        this.descricao = descricao;
	}
        public String toString(){
	return descricao + " " + valor + " " + saldoParcial;
	}
}

class GerenciadorDeLogin{
            private Repositório<Cliente> clientes;
            private Cliente client;
	
                    public GerenciadorDeLogin(Repositório<Cliente> clientes) {
                        this.clientes = clientes;
                            client = null;
	}
	
                void login(String idCliente){
                    if(client != null)
                        throw new RuntimeException("fail: ja existe alguem logado");
                            this.client = clientes.get(idCliente);
	}
	
              void logout(){
                      if(client == null)
                         throw new RuntimeException("fail: ninguem logado");
                            client = null;
	}
	
             public Cliente getCliente(){
                        if(client == null)
                            throw new RuntimeException("fail: ninguem logado");
                                return client;
	}
}
class Controller{
    Repositório<Cliente> clientes;
    Repositório<Conta> contas;
    Repositório<Operacao> extrato;
    Agencia agencia;
    Cliente cliente;
    Conta conta;
    String idCliente;
    int numero;
    GerenciadorDeLogin gerLogin;
	
        public Controller() {
            clientes = new Repositório<Cliente>("clientes");
            contas = new Repositório<Conta>("contas");
            extrato = new Repositório<Operacao>("extrato");
           agencia = new Agencia();
            cliente = new Cliente(idCliente);
            conta = new Conta(numero);
            gerLogin = new GerenciadorDeLogin(clientes);
	}
	
        public String oracle(String line){
                String ui[] = line.split(" ");
// >> addCliente e abrirConta  - CPF;
// >> encerrarConta - numero da conta;
// >> sacar,transferir e depositar colocar somente o valor;
        if(ui[0].equals("help"))
            return "addCliente , abrirConta , encerrarConta , \n" + 
                   "login , logout \n" + 
                   "sacar , transferir , depositar \n";
        else if(ui[0].equals("addCliente")) {
        	clientes.add(ui[1], new Cliente(ui[1]));
        	return "done: conta add ao Cliente";
        }
        else if(ui[0].equals("abrirConta")) {
        	agencia.abrirNovaConta(ui[1]);
        	return "done: conta add ao Cliente";
        }	
        else if(ui[0].equals("encerrarConta"))
        	cliente.encerrarConta(Integer.parseInt(ui[1]));
        else if(ui[0].equals("depositar"))
        	conta.depositar(Float.parseFloat(ui[1]));
        else if(ui[0].equals("transferir"))
        	conta.transferir(new Conta(Integer.parseInt(ui[1])),Float.parseFloat(ui[2]));
        else if(ui[0].equals("sacar"))
        	conta.sacar(Float.parseFloat(ui[1]));
        else if(ui[0].equals("showAll")) {
        	String saida = " ";
        	for(Cliente c : clientes.getAll())
                            saida += c.getIdCliente() + "\n";
                                return saida;
        }
	else if(ui[0].equals("login"))
                         gerLogin.login(ui[1]);
                    else if(ui[0].equals("logout"))
                         gerLogin.logout();
        
        else
            return "Comando Invalido";
        return "done";
    }
}

public class IO {
    static Scanner scan = new Scanner(System.in);
    
    static private String tab(String text){
        return "  " + String.join("\n  ", text.split("\n"));
    }
    
    public static void main(String[] args) {
        Controller cont = new Controller();
        System.out.println("Digite algum comando ou help:");
        while(true){
            String line = scan.nextLine();
            try {
                System.out.println(tab(cont.oracle(line)));
            }catch(Exception e) {
                System.out.println(tab(e.getMessage()));
            }
        }
}
}