import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestProject {

    static boolean stay = false;

    public static void main(String[] args){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(stay){
            System.out.println("What would you like to do? ");
            try {
                String inString = br.readLine();

                if(inString.toLowerCase() == "help"){
                    System.out.println("The following commands can be used: \n"
                                        + "exit \t  Exits application."
                                        + "new P \t Create new project ");

                }else if(inString.toLowerCase() == "exit"){
                    stay = true;
                }
            }catch(Exception e){
                stay = true;
            }
        }
    }
}
