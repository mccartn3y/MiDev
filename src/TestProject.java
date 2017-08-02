import java.io.*;

public class TestProject{

    static boolean stay = true;
    static Project mainProj;
    static Task currTask;
    static Task currSubTask;

    public static void main(String[] args){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(stay){
            System.out.println("What would you like to do? ");
            try {
                String commStr = br.readLine();
                switch(commStr.toLowerCase()){
                    case "help":
                        System.out.println("The following commands can be used: \n"
                                        + "exit        \t Exits application\n"
                                        + "new P       \t Create new project\n"
                                        + "print P     \t Prints project details\n"
                                        + "add T       \t Adds new task to project\n"
                                        + "add subT    \t Adds new sub-task to selected task\n"
                                        + "select T    \t Selects task from project\n"
                                        + "select subT \t Selects sub-task from task\n"
                                        + "comp T      \t Completes selected task\n"
                                        + "comp subT   \t Complete selected sub-task\n"
                                        + "del T       \t Deletes selected task\n"
                                        + "del subT    \t Deletes selected sub-task\n"
                                        + "save p      \t Saves project\n"
                                        + "load p      \t Loads project\n");
                        break;

                    case "new p":
                        System.out.println("What is the Project name?");
                        String projName = br.readLine();
                        System.out.println(String.format("What is the description for Project: \"%s\"?", projName));
                        String projDes = br.readLine();
                        mainProj = new Project(projName, projDes);
                        break;

                    case "print p":
                        mainProj.print();
                        break;

                    case "add t":
                        System.out.println("What is the task name?");
                        String taskName = br.readLine();
                        System.out.println("What is the task XP?");
                        int XP = Integer.parseInt(br.readLine());
                        mainProj.addSubTask(taskName, XP);
                        System.out.println(String.format("Task \"%s\" added to \"%s\".", taskName, mainProj.getName()));
                        break;

                    case "add subt":
                        if(currTask == null){
                            System.out.println("Please select a task (select T).");
                        }else {
                            System.out.println("What is the task name?");
                            String taskName1 = br.readLine();
                            System.out.println("What is the task XP?");
                            int XP1 = Integer.parseInt(br.readLine());
                            currTask.addSubTask(taskName1, XP1);
                            System.out.println(String.format("Sub-Task \"%s\" added to \"%s\".", taskName1, currTask.getName()));
                        }
                        break;

                    case "select t":
                        mainProj.print();
                        System.out.println("--");
                        System.out.println("Select a task...");
                        String taskN = br.readLine();
                        try{
                            currTask = mainProj.getSubTasks().get(taskN);
                            System.out.println("Selected Task:");
                            currTask.print();
                            currSubTask = null;
                        }catch(Exception e){
                            System.out.println("Invalid task name");
                        }
                        break;

                    case "select subt":
                        currTask.print();
                        System.out.println("--");
                        System.out.println("Select a task...");
                        String tN = br.readLine();
                        try{
                            currSubTask = currTask.getSubTasks().get(tN);
                            System.out.println("Selected Task:");
                            currSubTask.print();
                        }catch(Exception e){
                            System.out.println("Invalid task name");
                        }
                        break;

                    case "comp t":
                        if(currTask == null){
                            System.out.println("Please select a task (select T).");
                        }else{
                            currTask.completeTask();
                        }
                        break;

                    case "comp subt":
                        if(currSubTask == null){
                            System.out.println("Please select a sub-task (select subT).");
                        }else{
                            currSubTask.completeTask();
                        }
                        break;

                    case "del t":
                        if(currTask == null){
                            System.out.println("Please select a task (select T).");
                        }else{
                            mainProj.removeSubTask(currTask.getName());
                        }
                        break;

                    case "del subt":
                        if(currSubTask == null){
                            System.out.println("Please select a task (select T).");
                        }else{
                            currTask.removeSubTask(currSubTask.getName());
                        }
                        break;

                    case "save p":
                        FileOutputStream fileStream = new FileOutputStream("MainProj.ser");
                        ObjectOutputStream os = new ObjectOutputStream(fileStream);
                        os.writeObject(mainProj);
                        os.close();
                        break;

                    case "load p":
                        try {
                            FileInputStream fs = new FileInputStream("MainProj.ser");
                            ObjectInputStream oS = new ObjectInputStream(fs);
                            Object one = oS.readObject();
                            mainProj = (Project) one;
                            oS.close();
                        }catch(ClassNotFoundException e){
                            System.out.println("No saved file found.");
                        }
                        break;

                    case "exit":
                        stay = false;
                        break;
                    default:
                         throw new IOException();
                }
            }catch(IOException e){
                System.out.println("Invalid input. Type \"help\" for a list of valid commands");
            }
        }
    }
}
