
package BankBanescoClass;

import BankBanescoClass.Cola;
import BankBanescoClass.Lista;
import BankBanescoClass.Nodo;
import BankBanescoClass.Pila;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class BancoBanesco {
    
    static int horario = 1000;
    static Cola cola = new Cola();
    static Cola colaPrioritario = new Cola(); 
    static Lista lista = new Lista();
    static Pila pila = new Pila();
    static PrintWriter pf;
    static FileReader fr;
    static BufferedReader registro = new BufferedReader(new InputStreamReader(System.in));
          
    static void CargarClientes(String archivo) throws IOException {
        File file = new File("Archivos\\" +archivo);
        if(file.exists()){
              
                fr = new FileReader("Archivos\\" +archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                int cont = 0;
                
                while ((linea = br.readLine()) != null) {

                    String[] datos = new String[5];
                    datos = linea.split(",");
                    Nodo nodo = new Nodo();
                    nodo.cedula = datos[0];
                    nodo.Nombre = datos[1];
                    nodo.TipoDeTransaccion = Integer.valueOf(datos[2]);
                    nodo.monto = Double.valueOf(datos[3]);
                    nodo.EsPreferencial = datos[4];
                    if("S".equals(nodo.EsPreferencial)){
                        colaPrioritario.Encolar(nodo);
                    }else{
                    cola.Encolar(nodo);
                    }
                    
                      
                }
                fr.close();        
        }
    }
    
    static String NombreTransaccion(int codigoTransaccion){
        String result = "";
        switch (String.valueOf(codigoTransaccion)){
            case "1": result = "Retiro de fondos";break;
            case "2": result = "Deposito de fondos";break;
            case "3": result = "Consulta de movimientos";break;
            case "4": result = "Actualizacion de libreta";break;
            case "5": result = "Pago de servicios";break;
        }
        return result;
    }
    
    static int TiempoTransaccion(int tiempoTransaccion){
        int result = 0;
        switch (String.valueOf(tiempoTransaccion)){
            case "1": result = 70;break;
            case "2": result = 70;break;
            case "3": result = 50;break;
            case "4": result = 60;break;
            case "5": result = 40;break;
        }
        return result;
    }
    
    static boolean TiempoDisponible(int tiempoTransaccion){
        boolean result;
        if((horario - tiempoTransaccion) >= 0 ){
            result = true;
        }
        else{
            result = false;
        }
        return result;
    }
    
    static void GuardarPendientes(String nombreArchivo, Nodo nodo) throws IOException {
    
        pf = new PrintWriter(new FileWriter(nombreArchivo, true));
        String datos;
        datos = nodo.cedula + ","+nodo.Nombre + ","+ String.valueOf(nodo.TipoDeTransaccion)+ ","+ String.valueOf(nodo.monto)+ ","+ String.valueOf(nodo.EsPreferencial);
        pf.println(datos);
        pf.close();      
    }       
    
    static void GuardarTransacciones(String nombreArchivo, NodoTransaccion nodo) throws IOException {

        pf = new PrintWriter(new FileWriter(nombreArchivo, true));
        
        String datos;
        datos = nodo.cedula + ","+nodo.Nombre +","+ nodo.TipoDeTransaccion +","+ String.valueOf(nodo.monto); 
        pf.println(datos);
        pf.close();
    }
    
    static void ClientesPendientes(String archivo) throws IOException {
        File file = new File(archivo);
        if(file.exists()){
              
                fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;

                while ((linea = br.readLine()) != null) {


                    String[] datos = new String[4];
                    datos = linea.split(",");
                    Nodo nodo = new Nodo();
                    nodo.cedula = datos[0];
                    nodo.Nombre = datos[1];
                    nodo.TipoDeTransaccion = Integer.valueOf(datos[2]);
                    nodo.monto = Double.valueOf(datos[3]);
                    nodo.EsPreferencial = datos[4];
                    cola.Encolar(nodo);

                }
                fr.close();
                try{
                if(null!=fr){
                }

                file.delete();
                }
                catch(Exception e)
                {
                System.err.println("Error en el cierre del archivo de clientes pendientes");
            }
        }
    }    
    
    static void TransaccionesHistorico(String archivo) throws IOException {
        File file = new File("Archivostaq\\" +archivo);
        if(file.exists()){
              
                fr = new FileReader("Archivostaq\\" +archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;

                while ((linea = br.readLine()) != null) {


                    String[] datos = new String[4];
                    datos = linea.split(",");
                    NodoTransaccion nodo = new NodoTransaccion();
                    nodo.cedula = datos[0];
                    nodo.Nombre = datos[1];
                    nodo.TipoDeTransaccion = datos[2];
                    nodo.monto = Double.valueOf(datos[3]);
                    pila.apilar(nodo);

                }
                fr.close();
                try{
                if(null!=fr){
                }

                file.delete();
                }
                catch(Exception e)
                {
                System.err.println("Error en el cierre del archivo de clientes pendientes");
                }
            
                 while(!pila.EstaVacia()){
                    NodoTransaccion nodo = pila.desapilar();
                    GuardarTransacciones("taquila.log", nodo);

                }
        }
    }
    public static void main(String[] args) throws IOException {
        
       
       ClientesPendientes("Clientes_Pendientes.txt");
       CargarClientes("Clientes.txt");
       TransaccionesHistorico("Taquila.log");
       System.out.println("*********Bienvenidos al Banco Banesco*********");
       System.out.println("*********Empezando a atender*********");
        
        int cont = 4;
    
        do{
            Nodo nodoActual = null;
            if((colaPrioritario.EstaVacia() == false && cont == 4) || cola.EstaVacia() == true){
               nodoActual = colaPrioritario.Desencolar();
               cont = 0;
            }else{
                nodoActual = cola.Desencolar();
                cont++;
               
            }
            System.out.println("Atendiendo a: " +nodoActual.Nombre);
            System.out.println("Que transaccion realizar: "+NombreTransaccion(nodoActual.TipoDeTransaccion));
            if(nodoActual.monto != 0.0){
                System.out.println("Monto de la operacion: "+nodoActual.monto);
            }
            System.out.println("Tiempo en que realizo la operacion: "+TiempoTransaccion(nodoActual.TipoDeTransaccion));
            horario = horario - TiempoTransaccion(nodoActual.TipoDeTransaccion);
            System.out.println("----------------------------------");
            
                                  
            NodoTransaccion nodotra = new NodoTransaccion();
            nodotra.cedula = nodoActual.cedula;
            nodotra.Nombre = nodoActual.Nombre;
            nodotra.TipoDeTransaccion = NombreTransaccion(nodoActual.TipoDeTransaccion);
            nodotra.monto = nodoActual.monto;
            lista.Enlistar(nodotra);
            
            System.out.println("Personas en cola para atender: "+cola.Tamaño());
        }    
            while(cola.Tamaño()!= 0 && TiempoDisponible(TiempoTransaccion(cola.Cabecera().TipoDeTransaccion)));
            
            if(cola.Tamaño()> 0){


                System.out.println("En cola " +cola.Tamaño() +" personas para ser atentidad el dia de manana");
                do{
                    GuardarPendientes("Clientes_Pendientes.txt", cola.Desencolar());            
                }while(cola.Tamaño() != 0);
            }
            
            else{
                System.out.println("El banco ha terminado las solicitudes del dia de hoy");
            }
                
            while(!lista.EsVacia()){
                NodoTransaccion nodo = lista.Deslistar();
                pila.apilar(nodo);
            }
            
            while(!pila.EstaVacia()){
                NodoTransaccion nodo = pila.desapilar();
                GuardarTransacciones("Taquilla.log", nodo);
            }     
    
    }
    
}
