import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

    private static DNS dns;

    public static void main(String[] args) throws RemoteException {
        
        System.out.println("Starting Client...");
        inicializar_objetos();
        execute();
        
    }
    
    
    private static void inicializar_objetos() throws RemoteException{

        System.out.println("\nInitializing objects...");
    
        Agency agency_waiting = new Agency("localhost", 1234);
    
        dns = new DNS();
        dns.add_agency(agency_waiting);

        System.out.println("...Objects initialized!\n");

    }


    private static void execute(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o número de alunos que deseja simular: ");
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            executar_agente("Agent" + (i+1));
        }

        sc.close();

    }



    private static void executar_agente(String name) {

        // Cria um agente
        Agent agent = new Agent(name);
        
        // Coloca o aluno na sala de espera
        Agency agency = dns.

    }
    
}
