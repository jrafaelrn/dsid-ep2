import java.util.ArrayList;

public class DNS {

    public ArrayList<Agency> agencies;
    
    public DNS() {
        System.out.println("Starting DNS...");
        this.agencies = new ArrayList<Agency>();
    }


    public void add_agency(Agency agency) {
        this.agencies.add(agency);
    }


    public Agency get_agency_by_agent(Agent agent){
        
        for(Agency agency : this.agencies){
            if(agency.get_agent(agent) != null){
                return agency;
            }
        }
        return null;
    }


    public Agency get_agency_by_id(int id){
        
        for(Agency agency : this.agencies){
            if(agency.id == id){
                return agency;
            }
        }
        return null;
    }


    public Agency get_random_agency() {
        
        // Sorteia uma agência aleatoriamente
        int index = (int) (Math.random() * this.agencies.size());
        return this.agencies.get(index);

    }        
    

    public void print_status(){

        System.out.println("\n----- Status da rede -----");

        for(Agency agency : this.agencies){
            agency.print_status();
        }

        System.out.println("---------------------------\n");

    }

}
