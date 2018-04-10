
package Contato_Telefonico;

import java.util.ArrayList;
import java.util.Scanner;

class Telefone{
    String foneid;
    int numero;

//Esse é o CONSTRUTOR e é ele que instancia ( coloca valores ) a classe;
  public Telefone(String foneid, int numero){
       this.foneid = foneid;
       this.numero = numero;
}
 // classe toString usada apenas para retornar as informações da classe de maneira
 // organizada e facilitar futuros prints;
   
 @Override
   public String toString(){
      return "[ " + foneid + ":" +numero+"]";
  }
   
}
// criando a classe contato;
 class Contato{
     
    String nome;
     ArrayList<Telefone> telefones;
    
    public Contato (String nome){
         this.telefones = new ArrayList<>();
         this.nome = nome;
     }
    public boolean addFone(Telefone telefone){
        for(Telefone tel :this.telefones){
            if(tel.foneid.equals(telefone.foneid)){
   /*obs*/      return false;
            }
        }
        // se não, add o contato;
        this.telefones.add(telefone);//aqui vc poderia usar o mesmo telefone passado como parâmetro        
        //this.telefones.add(new Telefone(telefone.foneid ,telefone.numero));
        return true;
    }

   public boolean rmFone (String foneid){
       //para remover, preciso saber do índice do elemento, .size();
/*obs*/ for(Telefone tel: this.telefones){
 /*obs*/   if(tel.foneid.equals(foneid)){
               telefones.remove(tel);
               return true;
            }
       }
       return false;
   }
 
   @Override
   public String toString(){
       return "Nome: " + this.nome + " " + this.telefones;
   }
    
}
/**
 *
 * @author clerton filho
    */

//utilizando a classe controller e incializando a pessoa com null
class Controller{
    Contato contato;
    
    public Controller(){
        contato = new Contato(null);
    }
// utilizando a função oráculo que "comunica" com o usuário, onde ele coloca e recebe
//informações;
    public String oracle(String line){
        String ui[] = line.split(" ");
    
        if(ui[0].equals("help")){
            return "show,init_nome, addFone_id_number,rmFone_id_number";
        }
        else 
        if(ui[0].equals("init")){
            contato = new Contato(ui[1]);
         //inicializando o contato;}
        }   
        else
        if(ui[0].equals("show")){
            return " " + contato;
        }
        else
        if(ui[0].equals("addFone")){
            contato.addFone(new Telefone(ui[1],Integer.parseInt(ui[2])));
        }
        else
        if(ui[0].equals("rmFone")){
            contato.rmFone(ui[1]);
        }
        else{
            return "Comando inválido ";
        }
    return "done";
    }
}
 public class Contato_Telefonico {
    //Cria um objeto para ler as informações do teclado;
    static Scanner scan = new Scanner(System.in);
    
    //Aplica um tab e retorna o texto tabulado com dois espaços;
    static String tab(String text){
        return " " + String.join("\n ", text.split ("\n"));
    }
      public static void main(String[] args){
         Controller cont = new Controller();
         System.out.println("Digite um comando ou help: ");
         while(true){
             String line = scan.nextLine();
             try{
                 //Se não houver problema, faz a pergunta e mostra a resposta;
              System.out.println(tab(cont.oracle(line)));
             
             }
             catch(Exception e){
                 //Se houver o problema, mostrar o erro
                 System.out.println(tab(e.getMessage()));
                 }
             }
         }
 }


// HAVIA TENTADO FAZER DESSE JEITO MAS NÃO DEU CERTO...ENTÃO VOU FAZER UTILIZANDO O MÉTODO CONTROLLER;

//    public static void main(String[] args) {
//       // Instanciando a classe scanner para possibilitar a leitura no teclado;
//        Scanner sc = new Scanner(System.in);
//        //criando o contato, so que ele ainda não tem nenhuma
//        //informação, ou seja, ainda não foi iniciado (instanciado)
//        contato c = null;
//        boolean continuar = true;
//        
//    while(continuar){
//        //lendo a entrada digitada pelo usuário;
//        //comando nextline
///*obs*/ String entrada = sc.nextLine();
//        //Quebrando a string e armazenando em um vetor
//        // de strings;
///*obs*/ String comandos[] = entrada.split(" ");
//        
//        //switch para diferenciar os comandos
//        // que o usuário digitar;
//        
//        switch(comandos[0]){
//            case "help":
//                System.out.println("init,show,addFone e rmFone");
//                break;
//            case "init":
//                c = new contato(comandos[1]);
//                System.out.println("done");
//                break;
//            case "show":
//                if(c == null){
//                    System.out.println("Fail: nenhum contato encontrado");
//                }
//                else{
//                    //caso exista um contato
//                    // ele chama o toString para mostrar o nome e todos
//                    // os telefones desse contato;
//                    System.out.println(c.toString());
//                }
//                break;
//            case "addFone":
//                //para adicionar um novo telefone é necessário
//                //passar um objeto do tipo telefone;
//                //logo, vou criar um t do tipo telefone e passando os 
//                //parametros que o usuário digitou;
//    /*obs*/     Telefone t = new Telefone(comandos[1],Integer.parseInt(comandos[2]));
//                if(c.addFone(t) == true ){
//                    System.out.println("done");
//                }else{
//                    System.out.println("Fail: contato já existente");
//                }
//                break;
//            case "rmFone":
//                if( c.rmFone(comandos[1]) == true){
//                    System.out.println("done");
//                }else{
//                    System.out.println("Fail: fone não encontrado");
//                }
//                break;
//            case "exit":
//                continuar = false;
//                break;
//                
//            default:
//                System.out.println("Fail: comando não encontrado");
//            }
//        
//    
//        }
//    }
//    
//}
