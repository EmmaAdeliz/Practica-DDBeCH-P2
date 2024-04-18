/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor_cliente;

import javax.swing.JOptionPane;

import org.apache.xmlrpc.WebServer;

//import org.apache.xmlrcp.WebServer;

public class Servidor {
    
    public static void main (String[] args){
        //Capturamos si hay algun error
        try{
            //enviamos un mensaje
            JOptionPane.showMessageDialog(null, "Iniciando el servidor");
            
            //Creamos un objeto de tipo weserver
            WebServer server = new WebServer(8080);
            
            //Hacemos una relacion con nuestra parte simuladorInfección
            SimuladorInfeccion simuladorinfecion = new SimuladorInfeccion();
            
            //A que servidor se va conectar y creamos el servidor y enviamos al metodo
            server.addHandler("Miservidor", simuladorinfecion);
            
            //inicializamos el servidor
            server.start();
            
            //Enviamos otro mensaje para ver si fue exito el inicio
            JOptionPane.showMessageDialog(null, "Servidor en linea");
            
        } catch (Exception e){
            //En caso que no se conecte el servidor se envia un mensaje y lo concatenamos con el e
            JOptionPane.showMessageDialog(null, "Erro de conexión" + e.getMessage());
        }
    }
    
}
