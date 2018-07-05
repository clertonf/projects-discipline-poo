/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package clinica_vet;


import java.util.ArrayList;
import java.util.Scanner;
//fiz o remover cliente e remover serviço, mas só por curiosidade mesmo, comentei essa parte;
class Animal{
	
	public int idani;
                     public String nomeani;
	public Cliente dono;
                     public String especie;
	
	
	public Animal(int idani, String nome, String especie, Cliente dono) {
                            this.idani = idani;
                            this.nomeani = nome;
                            this.especie = especie;
                            this.dono = dono;
	}
	public String toString() {
                              return "[" + this.idani + ":"+ this.nomeani + ":" + this.especie + "]";
	}
	public String getNomea() {
                              return this.nomeani;
	}
}

class Cliente{
            private Repositório<Animal> animais;
           // Protector é como se fosse provarem, porém vc pode chamar nas classes filhas;
            protected String idCli;
            protected String nome;
	

	public Cliente(String idCli, String nome) {
                            this.idCli = idCli;
                            this.nome = nome;
                            this.animais = new Repositório<Animal>("animal");
	}
	public void addAni(Animal ani) {
                                        if(!animais.getAll().isEmpty()) {
                                            for(Animal an : animais.getAll())
                                                   if(an.nomeani == ani.nomeani) {
                                                       throw new RuntimeException("Animal já existe");
                                                    }
		}
		int idani = ++ServicoClínica.nextAniID;
		ani.idani = idani;
		this.animais.add(ani);
		return;
	}
	public String getIdCli() {
		return this.idCli;
	}
	
	public String getNome() {
		return this.nome;
	}

	public Repositório<Animal> getAnimais() {
		return animais;
	}
	
	public String animaisCliente() {
                            String osAnimais = "";
                            for(Animal ani : this.animais.getAll()) {
                                osAnimais += ani.toString();
                            }
                        return this.toString() + " " + osAnimais;
	}
                        public String toString() {
                            return "cli " + idCli + ": " + nome;
	}
	
}

class Serviço {
	public  String idSer;
	public int valor;
	
	
            public Serviço(String idSer,int valor) {
    	this.idSer = idSer;
	this.valor = valor;
	}
	public String getidSer() {
                            return this.idSer;
	}
	public float getvalor() {
                            return this.valor;
	}
	public String toString() {
                            return "[" + idSer + " " + valor + "] ";

        }
}

class Venda{
    public String nomea;
    public String idCli;    
    public String idSer;
    public int venID;
	
            public Venda(String idCli, String nomea, String idSer) {
                            this.idCli = idCli;
                            this.nomea = nomea;
                            this.idSer = idSer;
	}
	
	public String toString() {
		return "[" + venID + " " + idCli + " " + nomea + " " + idSer + "] ";	
	}
}
class ServicoClínica  {
	static int nextSerID;
	static int nextAniID;
	static int nextVenId;
	private Repositório<Serviço> serviços;
	private Repositório<Animal> animais;
	private Repositório<Cliente> clientes;
	private Repositório<Venda> vendas;
	private Cliente clien;
	private float saldo;
	
	public ServicoClínica() {
		nextAniID = 0;
		nextVenId = 0;
	serviços = new Repositório<Serviço>("serviço");
	animais = new Repositório<Animal>("animal");
	clientes = new Repositório<Cliente>("clientes");
	vendas = new Repositório<Venda>("venda");
                            clien = null;
                            saldo = 0;
		
	}
	
	public void addCli(Cliente cliente) {
                            if(!clientes.getAll().isEmpty()) {
                                for(Cliente cl : clientes.getAll())
                                    if(cl.getIdCli().equals(cliente.getIdCli())) {
		throw new RuntimeException("Cliente já existe");
		}
	}
                            clientes.add(cliente.getIdCli(), cliente);
                             return;
	}
	public void rmCli(String idCli) {
		for(Cliente cl : clientes.getAll())
			if(cl.getIdCli().equals(idCli)) {
				clientes.remove(idCli);
			}
	}   
	public void addSer(Serviço serviço) {
                          if(!serviços.getAll().isEmpty()) {
                              for(Serviço se : serviços.getAll())
                                   if(se.getidSer().equals(serviço.getidSer())) {
                                                throw new RuntimeException("Serviço já existe");
                                                }
                            }
                        serviços.add(serviço.getidSer(),serviço);
                            return;
	}
	public void rmSer(String idSer) {
		for(Serviço se : serviços.getAll())
			if(se.getidSer().equals(idSer)) {
				serviços.remove(idSer);
			}
	}   
            public void addAni(String idCli, String nomea, String especie) {
                  Cliente dono = null;
                   if(!clientes.getAll().isEmpty())
	for(Cliente cl : clientes.getAll())
                         if(cl.getIdCli().equals(idCli)) {
                                    dono = cl;
                                      break;
                                }
                       if(dono != null) {
                        Animal novoAni = new Animal(0, nomea,especie, dono);
                                           dono.addAni(novoAni);
		animais.add(novoAni);
		}
	} 
            public void vender(String idCli, String nomea,String idSer) {
            
                Serviço serviço = null;
                    if(!serviços.getAll().isEmpty()) {
                        for(Serviço se : serviços.getAll())
                            if(se.getidSer().equals(idSer)) {
                                            serviço = se;
                                                    break;
				}
                                            if(serviço == null) {
                                                    throw new RuntimeException("Serviço não existe");
			}
		}
                                    if(!clientes.getAll().isEmpty()) {
		Cliente cliente = null;
                                            for(Cliente cl : clientes.getAll())
                                                if(cl.getIdCli().equals(idCli)) {
                                                    cliente = cl;
                                                        break;
                                }
			
                    if(cliente != null) {
                            Animal animal = null;
                        for(Animal an : cliente.getAnimais().getAll())
                          if(an.nomeani.equals(nomea)) {
                            animal = an;
                              break;
                        }
                if(animal != null) {
                     Venda venda = new Venda(idCli, nomea, idSer);
                         venda.venID = ++ServicoClínica.nextVenId;
                            vendas.add(venda);
                            saldo += serviço.getvalor();
		} else {
                        throw new RuntimeException("Animal inexistente");
		}	
	}else {
                       throw new RuntimeException("Cliente inexistente");
		}
	} else {
                       throw new RuntimeException("Sem clientes no sistema");
		}
	return;
        }
	
                public float saldo() {
	return saldo; 
	}
                public ArrayList<Cliente> getAllCli() {
                        return clientes.getAll();
		
	}
                public ArrayList<Animal> getAllAni() {
	return animais.getAll();
		
	}
                public ArrayList<Serviço> getAllSer() {
	return serviços.getAll();
		
	}
                public ArrayList<Venda> getAllVen() {
	return vendas.getAll();
		
	}
                 public Cliente getCliente() {
	return clien;
	}
}


class Controller{
	ServicoClínica servc;
	
	public Controller() {
		servc = new ServicoClínica();
		 
	}

    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "Adicionar: nwcli _ CPF _nome, nwani _CPF _nome_ANIMAL  _especie, nwser _Serviço _valor, nwven _CPF _nome_ANIMAL _Serviço, rmcli _ CPF , rmser _ Serviço\n" + 
                   "Mostrar: laani, laser, laven, lacli, lscli _idCli, saldo\n";
        else if(ui[0].equals("nwcli")) {
        	String nome = "";
        	for(int i = 2; i < ui.length; i++)
        		nome += ui[i] + " ";
        	servc.addCli(new Cliente(ui[1], nome));
        }	
//remover clie e serviç
//            else if(ui[0].equals("rmcli")) {
//             servc.rmCli(ui[1]);
//	}
//           else if(ui[0].equals("rmser")) {
//               servc.rmSer(ui[1]);
//          }
        else if(ui[0].equals("lacli")) {
        	String saida = "";
        	for(Cliente cli : servc.getAllCli())
        		saida += cli.toString() + "\n";
        	return saida;
        }
        else if(ui[0].equals("nwani")) {
        	servc.addAni(ui[1], ui[2], ui[3]);	
        }
        else if(ui[0].equals("nwser"))
        	servc.addSer(new Serviço(ui[1], Integer.parseInt(ui[2])));	
        else if(ui[0].equals("nwven"))
        	servc.vender(ui[1], ui[2], ui[3]);	
        else if(ui[0].equals("laani")) {
        	String saida = "";
        	for(Animal ani : servc.getAllAni())
        		saida += ani.toString() + "\n";
        	return saida;
        }
        else if(ui[0].equals("laser")) {
        	String saida = "";
        	for(Serviço ser : servc.getAllSer())
        		saida += ser.toString() + "\n";
        	return saida;
        }
        else if(ui[0].equals("laven")) {
        	String saida = "";
        	for(Venda ven : servc.getAllVen())
        		saida += ven.toString() + "\n";
        	return saida;
        }
        else if(ui[0].equals("saldo")) {
        	return "" + servc.saldo();
        }
        else if(ui[0].equals("lscli")) {
        Cliente clienteMostrar = null;
                for(Cliente cli : servc.getAllCli())
                    if(cli.getIdCli().equals(ui[1])) {
                          clienteMostrar = cli;
                            break;
            }
        if(clienteMostrar != null) {
                  return clienteMostrar.animaisCliente();
      } else {
                   return "Cliente inexistente";
                }
          }	
        else
            return "comando invalido";
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
        System.out.println("Digita algum comando ou help");
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
