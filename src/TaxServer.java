import Server.ServerImpl;
import Server.Taxes;
import Server.TaxesHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * Server for the application
 */
public class TaxServer {
    public static void main(String[] args) {
        try{
            ORB orb = ORB.init(args, null);

            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            ServerImpl serverImpl = new ServerImpl();

            serverImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(serverImpl);
            Taxes tref = TaxesHelper.narrow(ref);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Taxes";

            NameComponent path[] = ncRef.to_name(name);

            ncRef.rebind(path, tref);

            orb.run();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
