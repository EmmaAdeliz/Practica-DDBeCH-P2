/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cliente_servidor;

import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.xmlrpc.XmlRpcClient;

public class Cliente {

    public static void main(String[] args) {
        //Declaramos dos variables de tipo strin ya que los recibe
        String x="", y="";
        
        //Creamos un objeto donde almacenara los resultados de la infección 
        Object resultado;
        
        //Creamos nuestra conexion y haremos referencia a la IP
        try{
           
            //Establecemos las conexión en la 8080
            XmlRpcClient cliente = new XmlRpcClient ("http:192.168.1.71:8080");
            //Agregamos un vector ya que recibe los parametros
            Vector<String> parametros = new Vector<String>();
            
            //Confirmamos si nos conectamos con el servidor a traves de un servidor
            
            JOptionPane.showMessageDialog(null,"El cliente se ha conectado");
            
            while (true){
                String menu = JOptionPane.showInputDialog(null,"Infección de 4 computadoras"
                +"\n1. Comenzar infección \n"
                +"\n2. Salir", "Infección \n", JOptionPane.DEFAULT_OPTION);
                
                switch(menu){
                    
                    case "1":
                         //Solicitar al cliente que ingrese el ID del nodo a infectar
                         String idNodo = JOptionPane.showInputDialog(null, "Ingrese el ID del nodo a infectar:", "Iniciar Infección", JOptionPane.QUESTION_MESSAGE);
                         //Verificamos si ingreso un ID valido
                         if (idNodo != null && !idNodo.isEmpty()) {
                            try {
                             //Agregamos el ID del nodo como parámetro
                             parametros.add(idNodo);
                             //Llamamos al método en el servidor para iniciar la infección
                             resultado = cliente.execute("Miservidor", parametros);
                             //Mostramos el mensaje de confirmación
                             JOptionPane.showMessageDialog(null, "Infección iniciada en el nodo " + idNodo, "Iniciar Infección", JOptionPane.INFORMATION_MESSAGE);
                             } catch (Exception ex) {
                              //Mostramos un mensaje de error si ocurre una excepción
                             JOptionPane.showMessageDialog(null, "Error al iniciar la infección: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                         } else {
                          //Mostramos un mensaje si se cancela o no se ingresa un ID válido
                         JOptionPane.showMessageDialog(null, "No se ingresó un ID válido.", "Iniciar Infección", JOptionPane.WARNING_MESSAGE);
                         }
                         break;

                    case "2":
                        JOptionPane.showMessageDialog(null, " Saliendo",null, JOptionPane.WARNING_MESSAGE);
                         //Salimos del programa
                        System.exit(0);
                        break;
                }
            }
            
        } catch(Exception e) {
            //En caso de problema
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            
        }
    }
}
