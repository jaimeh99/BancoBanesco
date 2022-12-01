
package BankBanescoClass;

public class Cola  {
    private Nodo l;
    private Nodo cola;
    private int tamaño;
    private Nodo front;
    private Nodo rear;
    
  public Cola(){
      l = cola = null;
      tamaño = 0;
  }  
    
   public int Tamaño(){
       return tamaño;
   } 
   
   public Nodo Cabecera(){
       return l;
   }
   
   public boolean EstaVacia(){
       return (l == null);
   }
    
   public void Encolar(Nodo nodo){
       if (tamaño == 0) {
           l = nodo;
           cola = l;
       }
       else{
           cola.siguiente = nodo;
           cola = nodo;
       }
         tamaño++;  
   }
   
   public Nodo Desencolar(){
       Nodo aux;
       if (EstaVacia()) return null;
           
       aux = l;
       l = l.siguiente;
       tamaño--;
       
       if (tamaño == 0) cola = null;
       
       return aux;
              
   }
   
 /*   public Nodo DesencolarPrioritario(){
       Nodo aux;
       if (EstaVacia()) return null;
       
       aux = l;
       l = l.siguiente;
       if(aux.EsPreferencial == "S"){
         return aux;
       }else{
         aux = aux.siguiente;  
       }
       tamaño--;
    }*/
   
}
