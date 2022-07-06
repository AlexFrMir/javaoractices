
package negocio;

import com.google.gson.*;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLEncoder;

public class Client{
    //static Gson j = new GsonBuilder().registerTypeAdapter(byte[].class,new AdaptadorGsonBase64()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    static void alta_usuario(Usuario usuario) throws Exception{
        URL url = new URL("http://20.120.99.92:8080/Servicio/rest/ws/alta_usuario");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream os=conexion.getOutputStream();
        Gson j = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        System.out.print("\n\tIngrese: \n\n");  
        System.out.print(" Email: ");  
        usuario.email=sc.nextLine();

        System.out.print("\n Nombre: ");  
        usuario.nombre=sc.nextLine();

        System.out.print("\n Apellido materno: ");  
        usuario.apellido_materno=sc.nextLine();
            
        System.out.print("\n Apellido paterno: ");  
        usuario.apellido_paterno=sc.nextLine();
                
        System.out.print("\n Fech de nacimiento: ");  
        usuario.fecha_nacimiento=sc.nextLine();
         
        System.out.print("\n Telefono: ");  
        usuario.telefono=sc.nextLine();
              
        System.out.print("\n Genero: ");  
        usuario.genero=sc.nextLine();

        String foto= "foto=null" ;     
        os.write(foto.getBytes());
        GsonBuilder builder = new GsonBuilder();
        builder.serialization();
        Gson gson = builder.create();
        String UsuarioJSON = gson.toJson(usuario);

        os.flush();
        if(conexion.getResponseCode() == 200){
            BufferedReader br=  new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String respuesta;
            while((respuesta=br.readLine())!=null) System.out.println(respuesta);
        }else{
            BufferedReader br=  new BufferedReader(new InputStreamReader(conexion.getErrorStream()));
            String respuesta;
            while((respuesta=br.readLine())!=null) System.out.println(respuesta);

        }
        conexion.disconnect();
    }
    static void consulta_usuario(Usuario usuario) throws Exception{
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        URL url = new URL("http://20.120.99.92:8080/Servicio/rest/ws/consulta_usuario");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream os=conexion.getOutputStream();
        System.out.print("\n\tIngrese: \n\n");  
        System.out.print(" Email: ");  
        String email= "email=" + URLEncoder.encode(sc.nextLine(), "UTF-8");              
        os.write(email.getBytes());
        os.flush();
        if(conexion.getResponseCode() == 200){
            BufferedReader br=  new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String respuesta;
            while((respuesta=br.readLine())!=null) System.out.println(respuesta);
        }else{
            BufferedReader br=  new BufferedReader(new InputStreamReader(conexion.getErrorStream()));
            String respuesta;
            while((respuesta=br.readLine())!=null) System.out.println(respuesta);

        }
        conexion.disconnect();
    }

    static void borrar_usuario(Usuario usuario){
        System.out.println("borrar usuario");

    }

    static void menu() throws Exception{
        Usuario usuario = new Usuario();
        boolean quit = false;
        while(!quit){
            Scanner sc = new Scanner(System.in);   
            System.out.println("\n\tMENU\n\na) Alta Usuario \n"+
                "b) Consulta Usiario\n" +
                "c) Borrar Usuario\n" +
                "d) Salir      \n"
            );
            System.out.print("Opcion: ");
            char c = sc.next().charAt(0);  
            switch (c) {
                case 'a':
                    alta_usuario(usuario);
                    break;
                case 'b':
                    consulta_usuario(usuario);
                    break;
                case 'c':
                    borrar_usuario(usuario);
                    break;
                case 'd':
                    quit = true;
                    System.out.println("closing program");
                    break;
            
                default:
                    System.out.println("wrong option, pls try again\n");
                    break;
            }
        }
    }


    public static void main(String[] args) throws Exception{

        menu();
    }
}
