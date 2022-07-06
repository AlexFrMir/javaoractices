
import java.rmi.*;
import java.rmi.server.*;


public class ServicioEcoImpl extends UnicastRemoteObject implements ServicioEco {
    public ServicioEcoImpl() throws RemoteException {
        super();
    }
    public String eco(String s) throws RemoteException {
        return s.toUpperCase();
    }
}
