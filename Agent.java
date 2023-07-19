class Agent implements Runnable{

    public String name;

    public Agent(String name){
        this.name = name;
    }

    @Override
    public void run() {        
        System.out.println("\t" + this.name + " chegou !!!");
    }


    @Override
    public String toString(){
        return this.name;
    }

}