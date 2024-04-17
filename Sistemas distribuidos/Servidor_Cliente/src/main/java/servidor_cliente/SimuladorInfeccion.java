/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor_cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author jonas
 */

class Nodo
{
    int id;//Almacenara nuestro identificador unico para cada nodo 
    boolean infectado; //Declararemos una variable de instancia esto para indicar si el 
                      //nodo esta infectado o no (true - false )
    List<Nodo> vecinos;//Declaramos otra variable instancida para almacenar las listas de nodos
                       //vecinos conectados a este nodo,se especifica que solo puede conoectar
                       //objetos de tipo 'Nodo'
    Nodo(int id){//Establecemos el constructor de la clase 'Nodo' tomando el argumento 'id'
        this.id=id;//Asignamos el valor parametro 'id' al campo 'id' del nodo 
        this.infectado=false;//inicializamos el nodo en falso significando que aun no esta infectado
        this.vecinos= new ArrayList<>();//Le asignamos que inicialice la lista de vecinos como una nueva
                                        //instancia
    }    
}
public class SimuladorInfeccion {
    //Este metodo nos permitira tomar dos parametros de una lista de  nodos
       static void simularInfeccion(List<Nodo> nodos, String enfoque) {
        Random rand = new Random();//se instanc
        Nodo infectado = nodos.get(rand.nextInt(nodos.size()));
        infectado.infectado = true; 
        List<Nodo> infectados = new ArrayList<>();
        infectados.add(infectado);

        while (!infectados.isEmpty()) {
            Nodo nodoActual = null;
            if (enfoque.equals("push") || enfoque.equals("pull-push")) {
                nodoActual = infectados.remove(0);
            } else if (enfoque.equals("pull")) {
                nodoActual = infectados.remove(infectados.size() - 1);
            }

            assert nodoActual != null;
             
            JOptionPane.showMessageDialog(null,"Nodo"+nodoActual.id+"infectado.");
            //System.out.println("Nodo " + nodoActual.id + " infectado.");

            for (Nodo vecino : nodoActual.vecinos) {
                if (!vecino.infectado) {
                    vecino.infectado = true;
                    infectados.add(vecino);
                }
            }
            if (enfoque.equals("pull-push")) {
                for (Nodo vecino : nodoActual.vecinos) {
                    if (!vecino.infectado) {
                        vecino.infectado = true;
                        infectados.add(vecino);
                    }
                }
            }
        }

        int totalInfectados = 0;
        for (Nodo nodo : nodos) {
            if (nodo.infectado) {
                totalInfectados++;
            }
        }
 
        JOptionPane.showMessageDialog(null,"Enfoque"+enfoque+"- Nodos infectados: "+totalInfectados);
        //System.out.println("Enfoque: " + enfoque + " - Nodos infectados: " + totalInfectados);
    
       }
}
