import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLEncoder;

public class A {
    public static void main(String[] args) throws Exception{
        URL url = new URL("http://sisdis.sytes.net:8080/Servicio/rest/ws/prueba");
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String parametros1 ="a=25" ; 
        //String parametros2 ="p2=" + URLEncoder.encode("64.7827", "UTF-8");
        //String parametros3 ="p3=" + URLEncoder.encode("52.0672", "UTF-8");
        //String parametros4 ="p4=" + URLEncoder.encode("87.9337", "UTF-8");
        OutputStream os=conexion.getOutputStream();
        os.write(parametros1.getBytes());
        //os.write(parametros2.getBytes());
        //os.write(parametros3.getBytes());
        //os.write(parametros4.getBytes());
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
}
