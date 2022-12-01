
package BankBanescoClass;


public class Lista {
   
    private NodoTransaccion l;
    private NodoTransaccion actual;
    private NodoTransaccion aux;
    private int tamano;
    
    
    public Lista(){
    actual = null;
    aux = null;
    tamano = 0;
}
    
    public NodoTransaccion ObtenerAux(){
        return aux;
    }
    
    public boolean EsVacia(){
        return l == null;
    }
    
    public int Tamano(){
        return tamano;
    }
    
    public void Enlistar(NodoTransaccion nodo){
        if(actual == null){ 
            actual = aux = l = nodo;
        }
        else{
            aux = actual;
            aux.siguiente = nodo;
            actual = nodo;
            
        }
        tamano++;
    }
    
    public NodoTransaccion Deslistar(){
       
       if (EsVacia()){
           return null;
       }
       
       else{
        aux = l;
        l = l.siguiente;
        tamano--;
       }
       
       if (tamano == 0) l = null;
       return aux;
              
    }
}