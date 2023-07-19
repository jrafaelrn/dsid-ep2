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
    private ArrayList<Agent> agents = new ArrayList<Agent>();


    public Agency(String address, int port) throws RemoteException {
        
        this.address = address;
        this.port = port;        
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


    public void send_agent(Agent agent, Agency agency) throws RemoteException {

        if(this.toString() == agency.toString()){
            System.out.println("O agente [" + agent.name + "] já está na agência [" + agency.toString() + "]!");
            return;
        }
        
        // Remove o agente da lista de agentes da agência
        this.agents.remove(agent);

        // Envia o agente para a agência
        agency.add_agent(agent);

    }

    

    public void run_agent(Agent agent){

        System.out.println("Running " + agent.name + " on server: " + this.total_address + "...");

        // Cria uma thread para o agente
        Thread thread = new Thread(agent);

        // Inicia a thread
        thread.start();
        
    }

    
    public void compilar(String actual_addres) {
        
        String command = "javac " + actual_addres + "/Agent.java";
        System.out.println("\tCompilando " + command + "...");

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao compilar o agente!");
        }

        System.out.println("\t...Compilado com sucesso!");

    }


    public void executar(String actual_addres){

        String command = "java " + actual_addres + "/Agent";
        System.out.println("\tExecutando agente [" + this.name + "]... Comando: " + command);
        
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Exception e) {
            System.out.println("Erro ao executar o agente!");
        }
    }

    public String toString(){
        return this.total_address;
    }
}
