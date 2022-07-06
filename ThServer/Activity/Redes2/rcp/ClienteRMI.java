import java.rmi.Naming;

public class ClienteRMI {
    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            System.out.println("ERROR: Write hostname;" +"\n"+ "example: java app 10.0.1.1");
        }else{
            String url = "rmi://"+args[0]+"/prueba";
            InterfaceRMI r = (InterfaceRMI)Naming.lookup(url);
            System.out.println(r.mayusculas("Hola, conectado al host: "  + args[0]));
            System.out.println("Suma=" + r.suma(10,20));
            System.out.println("Resta=" + r.resta(10,20));
            System.out.println("Multiplicación=" + r.multiplicacion(10,20));
            System.out.println("División=" + r.division(10,20));
            int[][] m = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
            System.out.println("checksum=" + r.checsum(m));    
        }
    /*
        
    */
    }
}
