import java.util.ArrayList;

public class Project {

    int currentXP;
    int maxXP;
    ArrayList<Task> taskList = new ArrayList<Task>();

    public void updateXP(){
        int tempCurrXP = 0;
        int tempMaxXP = 0;

        for (Task task : taskList){
            int[] tempXP = task.updateXP();
            tempCurrXP =+ tempXP[0];
            tempMaxXP =+ tempXP[1];
        }
        currentXP = tempCurrXP;
        maxXP = tempMaxXP;
    }


}
