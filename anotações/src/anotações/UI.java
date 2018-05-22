/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Anotações;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Scanner;


/**
 *
 * @author Clerton filho
 */

//método utilizando repositórios:


class Anotação{
    String titulo;
    String texto;
    
        public Anotação(String titulo,String texto){
        this.titulo = titulo;
        this.texto = texto;
    }
        public String getTitulo(){
        return titulo;
    }
        public void setTitulo(String titulo){
        this.titulo = titulo;
    }
        public String getTexto(){
        return texto;
    }
    
        public void setTexto(String texto){
        this.texto = texto;
    }
        public String toString(){
            return titulo + ":" + texto;
        }
  }   

class User implements Comparable <User>{
    private String password;
    private String username;
    public repositório <Anotação> notas;
    
    public repositório<Anotação> getNotas(){
        return notas;
    }
    public void setNotas(repositório<Anotação> notas){
        this.notas = notas;
    }
    public User(String username,String password){
        this.password = password;
        this.username = username;
        notas =  new repositório<Anotação>(username);
       }
    
    public void setPassword(String password){
        this.password = password;
    }
    public boolean matchPassword(String password){
        return this.password.equals(password);
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String toString(){
        return username + ":" + password;
    }
    public int compareTo (User s){
        return this.username.compareTo(s.username);
    }
    
}


class GerenciadorDeLogin{
	private repositório<User> usuarios;
	private User user;
	
	public GerenciadorDeLogin(repositório<User> usuarios) {
		this.usuarios = usuarios;
		user = null;
	}
	
	public void Login(String username, String password){
		if(user != null)
			throw new RuntimeException("fail: ja existe alguem logado");
		if(!usuarios.get(username).matchPassword(password))
			throw new RuntimeException("fail: password invalido");
		this.user = usuarios.get(username);
	}
	
	public void Logout(){
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		this.user = null;
	}
	
	public User getUser(){
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		return user;
	}
}

class Controller{
    repositório<User> usuarios;
    repositório<Anotação> notas;
    Scanner sca;
    GerenciadorDeLogin ger;
    
    public Controller(){
        sca = new Scanner(System.in);
        usuarios = new repositório<User>("usuário");
        ger = new GerenciadorDeLogin(usuarios);
        notas = new repositório<Anotação>("notas");
    }
    // função oraculo que recebe u pergunta e retorna uma resposta
    
    public String oracle(String line){
        String ui[] = line.split(" ");
        
        if(ui[0].equals("help")){
            return "add_user, login_user, show_user, add_anotacao, show_anotacao, logout_user, att_password, rm_anotacao.";
        }   
        else if(ui[0].equals("add_user")){
            usuarios.add(ui[1],new User(ui[1], ui[2]));
        }
        else if(ui[0].equals("login_user")){
            ger.Login(ui[1], ui[2]);
        }
        else if(ui[0].equals("showUsers")){
            String saida = "";
            for(User us : usuarios.getAll()){
                saida += us.getUsername() + "\n";
            }
            return saida;
        }
        else if(ui[0].equals("add_anotacao")){
            String texto = " ";
            for(int i = 2; i <ui.length; i++){
                texto += ui[i] + "";
                ger.getUser().notas.add(ui[1],new Anotação(ui[1],texto));
            }
        } 
        else if (ui[0].equals("att_password")){
                    if(ger.getUser().matchPassword(ui[1]))
                        ger.getUser().setPassword(ui[2]);
                        
                    }
        else if(ui[0].equals("show_anotacao")){
            String saida = " ";
            for(User u : usuarios.getAll()){
                saida += u.getNotas() + "\n";
                return saida;
            }
        }
        else if(ui[0].equals("logout_user")){
                 ger.Logout();
        }
        else if(ui[0].equals("rm_anotacao")){
            ger.getUser().notas.remove(ui[1]);
        }
        else if(ui[0].equals("rm_anotacao")){
            ger.getUser().notas.remove(ui[1]);
         }
        else
            return "comando inválido";
                return "done";

    }
}

public class UI {
    //cria um objeto scan para ler strings do teclado
    static Scanner scan = new Scanner(System.in);
    
    //aplica um tab e retorna o texto tabulado com dois espaços
    static private String tab(String text){
        return "  " + String.join("\n  ", text.split("\n"));
    }
    
    public static void main(String[] args) {
        Controller cont = new Controller();
        System.out.println("Digite um comando ou help:");
        while(true){
            String line = scan.nextLine();
            try {
                //se não der problema, faz a pergunta e mostra a resposta
                System.out.println(tab(cont.oracle(line)));
            }catch(Exception e) {
                //se der problema, mostre o erro que deu
                System.out.println(tab(e.getMessage()));
            	}
        	}
    	}
}






//public class Anotações {
//
//    public class Nota{
//        String titulo;
//        String texto;
//        
//        public Nota(String titulo, String texto){
//            this.titulo = titulo;
//            this.texto = texto;
//        }
//        
//        public String toString(){
//            return " Titulo: " + titulo + " | Texto: " + texto;
//        } 
//        
//    }
//
//    public static class Usuario{
//        String username;
//        String password;
//        ArrayList<Nota> notas;
//        
//        public Usuario(String username, String password){
//            this.notas = new ArrayList<Nota>();
//            this.username = username;
//            this.password = password;
//          
//        }
//        
//        public String getUsername(){
//            return this.username;
//        }
//        
//        public String getPassword(){
//            return this.password;
//        }
//        
//        public boolean addNote(Nota n ){
//            
//            return false;
//            
//        }
//        public boolean rmNote(String titulo){
//            return false;
//        }
//    
//        public boolean changePass(String oldpass, String newpass){
//            
//            
//            
//            return false;
//        }
//        public String toString(){
//            
//            String retorno = "" ; 
//            
//            for(Nota n : notas){
//                retorno += n.toString() + ","; // 
//            }
//            
//            return "User:" + username + "| Pass: " + password + "| Notas: [" + retorno + "]";
//        }
//     
//    }
//    
//    public static class SystemPrin  {
//        ArrayList<Usuario> usuarios;
//        Usuario logado = null;
//            
//        public SystemPrin(){
//            this.usuarios = new ArrayList();
//        }
//        public boolean addUser(Usuario user){
//            if(user.equals(null)){
//                return false;
//            }
//            /*for(Usuario us : usuarios){
//                if(user.getUsername() == us.getUsername()){
//                    System.out.println("Fail: User igual");
//                    return false;
//                    //throw new RuntimeException("fail: username já existe");
//                }
//            }
//            */
//            this.usuarios.add(user);
//            return true;
//        }
//        
//        public boolean Logar(String usuario, String senha){
//            for(Usuario us : usuarios){
//                if(us.getUsername().equals(usuario)){
//                    if(us.getPassword().equals(senha)){
//                        System.out.println("done");
//                        logado = us;
//                        return true;
//                    }else{
//                        System.out.println("Fail:Senha errada");
//                        return false;
//                    }
//                }
//            }
//            System.out.println("Fail:Username não existe");
//            return false;
//        }
//        
//        public boolean Deslogar(){
//            if(logado != null){
//                logado = null;
//                return true;
//            }else{
//                return false;
//            }
//        }
//        
//        public void showUsers(){
//             String retorno = "";
//            for(Usuario us : usuarios){
//                retorno += us.getUsername() + "\n";
//            }
//            System.out.println("Logado:"+logado+"\n"+ retorno);
//        }
//        
//        public String toString(){
//            String retorno = "";
//            for(Usuario us : usuarios){
//                retorno += us.toString() + "\n";
//            }
//            return retorno;
//        }
//        
//    }
//    
//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        SystemPrin s = new SystemPrin();
//       
//        while(true){
//         //   String entrada =  JOptionPane.showInputDialog("Digite o comando: ");
//         Scanner sc = new Scanner(System.in);
//         String entrada = sc.nextLine();
//         String comando[] = entrada.split(" ");
//
//            switch(comando[0]){
//
//                case "addUser":
//                    Usuario us = new Usuario(comando[1],comando[2]);
//                    s.addUser(us); 
//                break;
//
//                case "showUsers":
//                    s.showUsers();
//                break;
//                           
//                case "logar":
//                    s.Logar(comando[1],comando[2]); 
//                break;
//
//            }
//        }
//    }
//}
