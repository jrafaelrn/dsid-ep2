class Agent implements Runnable{

    public String name;

    public Agent(String name){
        this.name = name;
    }

    @Override
    public void run() {        
        System.out.println("\n\t\t" + this.name + " chegou na festa !!!\n");
    }

}