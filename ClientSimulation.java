import java.rmi.RemoteException;

public class ClientSimulation {

    private static DNS dns;

    public static void main(String[] args) throws RemoteException, InterruptedException {
        
        System.out.println("Starting Client...");

        dns = new DNS();

        Agent agent1 = new Agent("Agent 1");
        Agent agent2 = new Agent("Agent 2");
        Agent agent3 = new Agent("Agent 3");
        Agent agent4 = new Agent("Agent 4");
        Agent agent5 = new Agent("Agent 5");

        Agency agency1 = new Agency("localhost", 1234, 1);
        Agency agency2 = new Agency("localhost", 1235, 2);
        Agency agency3 = new Agency("localhost", 1236, 3);

        dns.add_agency(agency1);
        dns.add_agency(agency2);
        dns.add_agency(agency3);     

        // Distribui os agentes nas agências de forma aleatória
        agency1.add_agent(agent1);
        agency2.add_agent(agent2);
        agency3.add_agent(agent3);
        agency3.add_agent(agent4);
        agency3.add_agent(agent5);

        Thread.sleep(1000);
        dns.print_status();

        // Festa será na agência 1
        agency2.send_all_agents(agency1);
        agency3.send_all_agents(agency1);

        Thread.sleep(1000);
        dns.print_status();

        // Festa será na agência 3
        agency1.send_all_agents(agency3);
        agency2.send_all_agents(agency3);

        Thread.sleep(1000);
        dns.print_status();

    }



    
}
