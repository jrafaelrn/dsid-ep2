class Agent implements Runnable{

    private String name;

    public Agent(String name){
        this.name = name;
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
    
    
    @Override
    public void run() {
        
        System.out.println("\tRunning Agent...");
        
        String actual_addres = System.getProperty("user.dir");

        compilar(actual_addres);
        executar(actual_addres);


    }


    public static void main(String[] args) {
        
        System.out.println("\n\t\t!!! Agent executed!\n");

    }

}