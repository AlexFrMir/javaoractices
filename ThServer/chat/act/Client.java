import java.util.Scanner;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.*;

public class Client{
    static Gson j = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    //static Gson j = new GsonBuilder().registerTypeAdapter(byte[].class,new AdaptadorGsonBase64()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    static void alta_usuario(Usuario usuario) throws Exception{
        URL url = new URL("http://20.120.99.92:8080/Servicio/rest/ws/alta_usuario");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

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

        usuario.foto=null;

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();
        String body = gson.toJson(usuario);
        String parametros = "usuario="+ URLEncoder.encode(body, "UTF-8");
        OutputStream os=conexion.getOutputStream();
        os.write(parametros.getBytes());
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

    static void modificar_usuario(Usuario usuario) throws Exception{
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        URL url = new URL("http://20.120.99.92:8080/Servicio/rest/ws/modifica_usuario");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        System.out.print("\n\tIngrese: \n\n");  
        System.out.print("Email " + usuario.email  + ":");  
        String email=sc.nextLine();
        if(email!=""){
            usuario.email=email;
        }
        System.out.print("\n Nombre " + usuario.nombre  + ":");  
        String nombre=sc.nextLine();
        if(nombre!=""){
            usuario.nombre=nombre;
        }
        System.out.print("\n Apellido materno: " + usuario.apellido_materno + ":");  
        String apellido_materno=sc.nextLine();
        if(apellido_materno!=""){
            usuario.apellido_materno=apellido_materno;
        }    
        System.out.print("\n Apellido paterno "+ usuario.apellido_paterno + ":");  
        String apellido_paterno=sc.nextLine();
        if(apellido_paterno!=""){
            usuario.apellido_paterno=apellido_paterno;
        }
        System.out.print("\n Fech de nacimiento "+ usuario.fecha_nacimiento + ":");  
        String fecha=sc.nextLine();
        if(fecha!=""){
            usuario.fecha_nacimiento=fecha;
        } 
        System.out.print("\n Telefono " + usuario.telefono  + ":");  
        String telefono=sc.nextLine();
        if(telefono!=""){
            usuario.telefono=telefono;
        }      

        System.out.print("\n Genero " + usuario.genero  + ":");  
        String genero=sc.nextLine();
        if(genero!=""){
            usuario.genero=genero;
        }
        usuario.foto=null;

        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();
        String body = gson.toJson(usuario);
        String parametros = "usuario="+ URLEncoder.encode(body, "UTF-8");
        OutputStream os=conexion.getOutputStream();
        os.write(parametros.getBytes());
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
        System.out.print(" id: ");  
        String id= "id_usuario=" + sc.nextLine();              
        os.write(id.getBytes());
        os.flush();

        Usuario usuario1 = null;
        if(conexion.getResponseCode() == 200){
            BufferedReader br=  new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String respuesta;
            while((respuesta=br.readLine())!=null){
                usuario1 = j.fromJson(respuesta,Usuario.class);
                System.out.println("\t. ID:\t"+ usuario1.id_usuario);
                System.out.println("\t. CORREO:\t"+ usuario1.email);
                System.out.println("\t. NOMBRE:\t"+ usuario1.nombre);
                System.out.println("\t. A. PATERNO:\t"+ usuario1.apellido_paterno);
                System.out.println("\t. A.MATERNO:\t"+ usuario1.apellido_paterno);
                System.out.println("\t. FECHA DE NACIMIENTO:\t"+ usuario1.fecha_nacimiento);
                System.out.println("\t. TELEFONO:\t"+ usuario1.telefono);
                System.out.println("\t. GENERO:\t"+ usuario1.genero);
                
                System.out.println("Modificara los datos?\n a) si\n b) no\n");
                System.out.print("Opcion: ");
                char c = sc.next().charAt(0);  
                if(c=='a'){
                    modificar_usuario(usuario1);
                }
            }
        }else{
            BufferedReader br=  new BufferedReader(new InputStreamReader(conexion.getErrorStream()));
            String respuesta;
            while((respuesta=br.readLine())!=null) System.out.println(respuesta);

        }
        conexion.disconnect();
    }

    static void borrar_usuario(Usuario usuario) throws Exception{
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        URL url = new URL("http://20.120.99.92:8080/Servicio/rest/ws/borra_usuario");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStream os=conexion.getOutputStream();
        System.out.print("\n\tIngrese: \n\n");  
        System.out.print(" id: ");  
        String id= "id_usuario=" + sc.nextLine();              
        os.write(id.getBytes());
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
