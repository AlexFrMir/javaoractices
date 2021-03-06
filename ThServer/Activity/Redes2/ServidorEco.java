
import java.rmi.*;
import java.rmi.server.*;


public class ServidorEco  {
    static public void main (String args[]) throws Exception{
       if (args.length!=1) {
            System.err.println("Uso: ServidorEco numPuertoRegistro");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            ServicioEco srv = new ServicioEcoImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Eco", srv);
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorEco:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
