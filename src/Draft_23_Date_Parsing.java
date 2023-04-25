import java.text.ParseException;
import java.time.LocalDate;

public class Draft_23_Date_Parsing {
    public static void main(String[] args) throws ParseException {

        String dtS = "31/03/2023";
// Делаем парсинг введённой строки методом Split.
        String[] items = dtS.split("/");
        String dat = items[0];
        String mon = items[1];
        String yea = items[2];


        int datI = Integer.parseInt(dat);
        int monI = Integer.parseInt(mon);
        int yeaI = Integer.parseInt(yea);


//        LocalDate dmy = LocalDate.of(datI, monI, yeaI);
//        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
//        String dtStr = dmy.format(f);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
//        Calendar c = Calendar.getInstance();
//        c.setTime(sdf.parse(String.valueOf(dtStr)));

//        System.out.println(dmy);


    }
}
