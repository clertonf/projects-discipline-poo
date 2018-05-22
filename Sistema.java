/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agiota;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author clerton filho
 */

 class Cliente{
    
     public String clienteid;
     public String nome;
     public String estado = "vivo";
     public float saldo = 0;
    
    public Cliente (String clienteid, String nome){
        this.clienteid = clienteid;
        this.nome = nome;
    }
    public String toString(){
        return " " + nome + " "+clienteid+" "+estado; 
    }
    public String Saldo(){
        return " " + nome + "saldo: " + saldo;
    }
    
 }
     public class Sistema{
        public float saldo = 0;
        public float dinheiro;
        public int id_transacao = 0;
        public int id = 0;
        
        ArrayList<Cliente> clientes;
        ArrayList<Transacao> transacao;

        public Sistema(float dinheiro){
            this.dinheiro = dinheiro;
            clientes = new ArrayList<Cliente>();
            transacao = new ArrayList<Transacao>();
            saldo += dinheiro; 
            
        }
        public String toString(){
            
            return "Sistema inicializado com: " +saldo+ " "+ clientes.toString();
        }
        public void CadastroClientes(String nome, String clienteid){
                for(Cliente c : this.clientes){
                    if(c.clienteid.equals(clienteid)){
                        throw new RuntimeException("Cliente já cadastrado");
                    }
                }
                clientes.add(new Cliente(clienteid, nome));
        }
       
        public void CadastrarDivida(String nome , float valor){
            if(valor <= saldo){
                saldo += valor;
                this.transacao.add(new Transacao(nome,valor,id));
                id ++ ;
                return;
            }
            else{
                throw new RuntimeException ("Saldo insufisciente ao dinheiro pedido");
                
            }
        }
        public void Emprestimo(String nome, float valor){
                for(Cliente c: clientes){
                    if(c.nome.equals(nome)){
                        c.saldo += valor;
                    this.CadastrarDivida(nome, valor);
                    return;
                    }
                  throw new RuntimeException ("Pessoa ou saldo errado!");
                }
        }
        
        public String ShowAllClientes(){
            String s = " ";
            for(int i = 0; i < clientes.size(); i++){
                s += "" + this.clientes.get(i).Saldo();
                
            }
           return s; 
        }  
        
        public void ShowCliente(String nome){
            int i = 0;
            for(Cliente c : clientes){
                if(c.nome.equals(nome)){
                    System.out.println(c.Saldo());
                    while (transacao.get(i).nome.equals(nome)){
                        System.out.println(transacao.get(i).toString());
                    i++;
                    }
                }
             return;
            }
           throw new RuntimeException("Cliente não foi encontrado");
        }
        
        
        public String ShowTransacao(){
            return " " + transacao.toString();
        }
        
           
        public void DellDividas(String nome){
            for(int i = 0; i<transacao.size(); i++){
                this.transacao.remove(transacao.get(i));
            }
        }
        
        public void PagarDebito (String nome, float valor) {
            for (Cliente c : clientes) {
                    if (c.nome.equals(nome)) {
                        if (c.saldo < 0) {
				c.saldo = saldo + valor;
				dinheiro = dinheiro + valor;
				this.transacao.add(new Transacao(nome, valor, id));
                                    id++;
                                    return;
				}
			}
		}
		
		throw new RuntimeException("Cliente não encontrado!");
		
}
       public void MatarCliente(String nome) {
            for( int i = 0; i < clientes.size(); i++) {
		if(clientes.get(i).nome.equals(nome)) {
                    this.clientes.remove(clientes.get(i));
                    DellDividas(nome);
		       return;
                }
}
     
  
     
     }

   class Transacao {
            public String nome;
            public float vdebito;
            public int id = 0;
            public float total = 0;
            
            public Transacao(String nome, float valor,int id) {
                this.nome = nome;
                this.vdebito = valor;
                this.id = id;
                this.total = vdebito + total;
           }
        public String toString(){
            return "id: " + id + " nome: " +nome+ "valor:" + vdebito;
        }    
            
    }


class Controller {
	Sistema sis;
	Scanner sca;
	    
	    
	public Controller() {
		sca = new Scanner(System.in);
		
	}

	public String query(String line) {
		String[] ui = line.split(" ");
		
	    if (ui[0].equals("Iniciar"))
			sis = new Sistema(Float.parseFloat(ui[1]));
	    else if (ui[0].equals("Mostrar"))
			return " " + sis;
	    else if(ui[0].equals("CadastrarCliente"))
	    	sis.CadastroClientes(ui[1],ui[2]);
	    else if(ui[0].equals("MostrarTrasacoes"))
	    	return "" + sis.ShowTransacao() ;
	    else if(ui[0].equals("Emprestimo"))
	    	sis.Emprestimo(ui[1],Float.parseFloat(ui[2]));
	    else if(ui[0].equals("MostrarClientes"))
	    	return ""+ sis.ShowAllClientes();
	    else if(ui[0].equals("MostrarDeterminadoCliente"))
	        sis.ShowCliente(ui[1]);
	    else if(ui[0].equals("ReceberDebito"))
	    	sis.PagarDebito(ui[1],Float.parseFloat(ui[2]));
	    else if(ui[0].equals("MatarCliente"))
	    	sis.MatarCliente(ui[1]);
	    else
	    	return " Comando invalido";
		return "done";
	}

	public void shell() {
		while (true) {
			String line = sca.nextLine();
			try {
				System.out.println(query(line));
			} catch (RuntimeException re) {
				System.out.println(re.getMessage());
			}
		}
	}
	
            public static void main(String[] args) {
            Controller c = new Controller();
            c.shell();
    }
  
}