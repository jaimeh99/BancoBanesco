
package BankBanescoClass;

public class Pila {
    
   private NodoTransaccion l;
   private NodoTransaccion aux;
   private int tamano;
   
   public void pila(){
       l = null;
       aux = null;
       tamano = 0;
   }
   
   public boolean EstaVacia(){
       return l == null;
   }
   
   public int Tamano(){
       return tamano;
   }
   
   public void apilar(NodoTransaccion nodo){
       
        if (EstaVacia()){
           l = nodo;    
       }
        else{
           nodo.siguiente = l;
           l = nodo;
       }
       tamano++;      
   }
   
   public NodoTransaccion desapilar(){
      if (EstaVacia()){
          return null;
          
      }else{
          aux = l;
          l = l.siguiente;
          tamano--;
      }
      
      if (tamano == 0) l = null;
      return aux;
   }
   
   
}