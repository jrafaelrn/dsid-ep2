import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Agency {

    private Registry registry;
    private String actual_addres = System.getProperty("user.dir");
    private String total_address;
    private String address;
    private int port;
    public int id;
    private ArrayList<Agent> agents = new ArrayList<Agent>();


    public Agency(String address, int port, int id) throws RemoteException {
        
        this.address = address;
        this.port = port;        
        this.id = id;
        this.registry = LocateRegistry.createRegistry(port);

        this.total_address = "rmi://" + this.address + ":" + this.port + "/agency";

        //registry.rebind(this.total_address, this);

        System.out.println("Agency created on server: " + this.total_address + "...");

    }

    public void add_agent(Agent agent) {
        this.agents.add(agent);
        this.run_agent(agent);
    }


    public Agent get_agent(Agent agent){

        for (Agent a : this.agents) {
            if(a.name.equals(agent.name)){
                return a;
            }
        }
        return null;

    }


    public void send_all_agents(Agency agency) throws RemoteException {

        for (Agent agent : this.agents) {

            // Envia o agente para outra agência
            this.send_agent(agent, agency);
        }

        this.agents.clear();

    }


    public void send_agent(Agent agent, Agency agency) throws RemoteException {

        if(this.toString() == agency.toString()){
            System.out.println("O agente [" + agent.name + "] já está na agência [" + agency.toString() + "]!");
            return;
        }
        
        // Envia o agente para a agência
        agency.add_agent(agent);

    }

    

    public void run_agent(Agent agent){

        // Cria uma thread para o agente
        Thread thread = new Thread(agent);

        // Inicia a thread
        thread.start();
        
    }

    
    @Override
    public String toString(){
        return this.total_address;
    }
}
