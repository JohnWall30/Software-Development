import java.io.File;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {

        try {
            System.out.print("Enter the file name with extension : ");

            Scanner input = new Scanner(System.in);

            File file = new File(input.nextLine());
            
            input = new Scanner(file);
            int count = 0;

            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            
                for(int i = 0; i<line.length();i++)
                {
                    if(line.charAt(i) != ' ')
                    {
                        count++;
                    }
                }
            }
            System.out.println("Count:" + count);
            if(count >500)
            {
                System.out.println("error!!!");
                System.exit(0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try{
            System.out.print("Enter the image name with the extension");
        }
        catch(Exception ex){
            ex.printStackTrace();

        }
    }

}