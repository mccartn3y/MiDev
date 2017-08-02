import java.util.ArrayList;
import java.util.Map;

public class Project extends Task{

    private String description;

    public Project(String name, String inDescription){
        super(name,0);
        description = inDescription;
    }

    public void print(){
        System.out.println(String.format("Project name: \t %s",this.getName()));
        System.out.println(String.format("Description: \t %s", description));
        int[] XP = this.updateXP();
        System.out.println(String.format("Current progress: \t %d/%d", XP[0], XP[1]));
        System.out.println("----------------------------------------------------------------");

        if(!this.getSubTasks().isEmpty()) {
            for (Map.Entry<String, Task> entry : this.getSubTasks().entrySet()) {
                entry.getValue().print();
            }
        }
    }

}
