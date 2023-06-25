import java.rmi.RemoteException;

public class Client {

    private static DNS dns;

    public static void main(String[] args) throws RemoteException {
        
        System.out.println("Starting Client...");
        inicializar_objetos();
        
    }
    
    
    private static void inicializar_objetos() throws RemoteException{
    
        Agency agency1 = new Agency("localhost", 1234);
        Agency agency2 = new Agency("localhost", 1235);
    
        dns = new DNS();
        dns.add_agency(agency1);
        dns.add_agency(agency2);

    }



    private void executar_agente() {

        // Cria um agente
        Agent agent = new Agent();
        
        // Sorteia uma agência aleatoriamente
        Agency agency = dns.get_random_agency();

        agency.run_agent(agent);

    }
    
}
