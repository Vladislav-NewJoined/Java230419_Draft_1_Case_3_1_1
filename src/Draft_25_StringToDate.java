import java.text.SimpleDateFormat;
import java.util.Date;

// инфо здесь: https://www.javatpoint.com/java-string-to-date
public class Draft_25_StringToDate {
    public static void main(String[] args)throws Exception {

//        String dtStr = "31/03/2023";

            String sDate1="31/12/1998";
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            System.out.println(sDate1+"\t"+date1);
        }
    }