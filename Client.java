import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    
    private static DNS dns;
    
    public static void main(String[] args) throws RemoteException, InterruptedException {
        
        System.out.println("Starting Client...");
        
        dns = new DNS();
        
        Scanner sc = new Scanner(System.in);

        System.out.println("\nDigite o número de agencias que deseja simular: ");
        int num_agencias = sc.nextInt();
        
        for(int i = 0; i < num_agencias; i++){
            int start_port = 1234;
            criar_agencia(start_port + i, i+1);
        }


        System.out.println("\nDigite o número de agentes que deseja simular: ");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            criar_agente("Agent" + (i+1));
        }


        
        while (true) {
            
            Thread.sleep(1000);
            System.out.println("\nDigite o ID da agência que deseja convidar para a festa: (ou X para sair)");
            String opcao = sc.next().toUpperCase();

            if(opcao.equals("X")){
                sc.close();        
                System.out.println("Saindo...");
                System.exit(0);
            }
            
            int id_agencia = Integer.parseInt(opcao);
            Agency agency = dns.get_agency_by_id(id_agencia);

            for(Agency a : dns.agencies){
                if(a.id != id_agencia){
                    a.send_all_agents(agency);
                }
            }

        }

    }


    private static void criar_agencia(int port, int id) throws RemoteException{

        Agency agency = new Agency("localhost", port, id);
        dns.add_agency(agency);

    }


    private static void criar_agente(String name) {

        Agent agent = new Agent(name);
        Agency random_agency = dns.get_random_agency();
        random_agency.add_agent(agent);        

    }
    
}
