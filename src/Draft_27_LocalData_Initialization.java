import java.time.LocalDate;

public class Draft_27_LocalData_Initialization {
    public static void main(String[] args) {
        String dtStr = "2023-03-31";

        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2023, 03, 31);
        LocalDate date3 = LocalDate.parse("2023-03-31");
        LocalDate date4 = LocalDate.parse(dtStr);
        System.out.println(date4);
    }
}
