import java.rmi.Naming;

public class ServerRMI {
    
    public static void main(String[] args) throws Exception{
        String url = "rmi://localhost/nodo1";
        //String url = "rmi://localhost/nodo2";
        //String url = "rmi://localhost/nodo3";
        ClassRMI obj = new ClassRMI();
        Naming.rebind(url, obj);
    }
}
