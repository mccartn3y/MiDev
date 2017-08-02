import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Task {
    /*
    The Task class hold the name and XP of a given task. It can also have aubtasks assigned to it.
    If subtasks are assigned then the XP is calculated as the sum of the subtask XP.
    */

    private String name;
    private int XP;
    private int maxXP;
    private ArrayList<Task> subTasks;
    private boolean completed = false;
    private Date compDate;

    public Task(String inName, int inXP){
        name = inName;
        maxXP = inXP;
    }

    public int getCurrXP(){
        int currXP = 0;
        if(subTasks.isEmpty()){
            if(completed){
                return XP;
            }else{
                return 0;
            }
        }else{
            for (Task item : subTasks) currXP += item.getCurrXP();
        }
        return currXP;
    }

    public int getMaxXP(){
        int tempXP = 0;
        if(subTasks.isEmpty()){
            return maxXP;
        }else{
            for (Task item : subTasks) tempXP += item.getMaxXP();
        }
        return tempXP;
    }

    //Sub takss can be added individually or by passing in a map of strings and ints
    public void addSubTask(String inName, int inXP){
        subTasks.add(new Task(inName, inXP));
        this.updateXP();
    }

    public void addSubTask(Map<String, Integer> taskList){
        for (Map.Entry<String, Integer> entry : taskList.entrySet()){
            this.addSubTask(entry.getKey(), entry.getValue());
        }
        this.updateXP();
    }

    //XP can be updated by checking subtasks for XP values or by passing in a new XP value.
    public int[] updateXP(){
        maxXP = this.getMaxXP();
        XP = this.getCurrXP();
        return new int[] {XP, maxXP};
    }

    public int[] updateXP(int newXP){
        maxXP = newXP;
        return this.updateXP();
    }

    public void printTask() {
        if (subTasks.isEmpty()) {
            if (completed) {
                System.out.println(String.format("Task: %s XP: %d Status: \u2713 (%tc)", name, XP, compDate));
            } else {
                System.out.println(String.format("Task: %s XP: %d Status: \u274C", name, XP));
            }
        } else {
            for (Task item : subTasks) {
                item.printTask();
            }
        }
    }

    public void completeTask(){
        if(subTasks.isEmpty()){
            completed = true;
            compDate = new Date();
            XP = maxXP;
        }else{
            for(Task item : subTasks){
                item.completeTask();
            }
        }
    }

}
