import java.util.ArrayList;

public class DNS {

    private ArrayList<Agency> agencies;
    
    public DNS() {
        System.out.println("Starting DNS...");
        this.agencies = new ArrayList<Agency>();
    }


    public void add_agency(Agency agency) {
        this.agencies.add(agency);
    }


    public Agency get_agency(Agent agent){
        
        for(Agency agency : this.agencies){
            if(agency.get_agent(agent) != null){
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
    

}
