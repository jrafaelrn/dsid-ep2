import java.rmi.RemoteException;

public class ClientSimulation {

    private static DNS dns;

    public static void main(String[] args) throws RemoteException {
        
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

        // Festa será na agência 1
        invite_to_party(agency1, agent1);
        invite_to_party(agency1, agent2);
        invite_to_party(agency1, agent3);
        invite_to_party(agency1, agent4);
        invite_to_party(agency1, agent5);

        // Festa será na agência 3
        invite_to_party(agency3, agent1);
        invite_to_party(agency3, agent2);
        invite_to_party(agency3, agent3);
        invite_to_party(agency3, agent4);
        invite_to_party(agency3, agent5);

    }


    private static void invite_to_party(Agency agency, Agent agent){

        try {
            Agency actual_agency = dns.get_agency_by_agent(agent);
            actual_agency.send_agent(agent, agency);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    
}
