import java.io.File;
import java.io.IOException;

//инфо здесь: https://translated.turbopages.org/proxy_u/en-ru.ru.864dbb2f-64483f5e-2bfc6fd5-74722d776562/https/stackoverflow.com/questions/17111526/writing-to-multiple-files-without-duplicating-code-java
//и здесь: https://www.golinuxcloud.com/java-create-write-to-file-examples/
public class Draft_28_3_Write_Multiple_Files_ExampleFrom_codeproject_com {
    public static void main( String[] args ) throws IOException
    {
        try
        {
            String[] Names = new String[20];
            Names[0] = "Android";
            Names[1] = "java";
            Names[2] = "computerscience";
            Names[3] = "satellite";
            Names[4] = "communication";
            Names[5] = "navigator";
            Names[6] = "science";
            Names[7] = "a";
            Names[8] = "b";
            Names[9] = "c";
            Names[10] = "d";
            Names[11] = "e";
            Names[12] = "f";
            Names[13] = "g";
            Names[14] = "h";
            Names[15] = "i";
            Names[16] = "j";
            Names[17] = "l";
            Names[18] = "m";
            Names[19] = "n";

            for(int j = 0; j<Names.length; j++)
            {
                File file = new File("C:\\Users\\PC\\IdeaProjectsDrafts\\Java230419_Draft_1_Case_3_1_1" + Names[j] + ".txt");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
        }
    }
}