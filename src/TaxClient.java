import Server.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import Server.Taxes;

/**
 * Client for the application
 */
public class TaxClient {

    static Taxes helloImpl;

    /**
     * Makes a request to the server to calculate tax on a salary
     * @param args
     */
    public static void main(String args[])
    {
        try{

            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Taxes";
            helloImpl = TaxesHelper.narrow(ncRef.resolve_str(name));

            System.out.println(helloImpl.calculateTax(513551.46, 0.04));

        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }
}
