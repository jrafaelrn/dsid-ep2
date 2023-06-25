import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Agency {

    private Registry registry;
    private String total_address;
    private String address;
    private int port;
    private ArrayList<Agent> agents = new ArrayList<Agent>();


    public Agency(String address, int port) throws RemoteException {
        
        this.address = address;
        this.port = port;        
        this.registry = LocateRegistry.createRegistry(port);

        this.total_address = "rmi://" + this.address + ":" + this.port + "/agency";

        //registry.rebind(this.total_address, this);

    }

    public void add_agent(Agent agent) {
        this.agents.add(agent);
    }
    

    public void run_agent(Agent agent){

        // Cria uma thread para o agente
        Thread thread = new Thread(agent);

        // Adiciona o agente na lista de agentes da agência
        this.add_agent(agent);

        // Inicia a thread
        thread.start();
        
    }
}
