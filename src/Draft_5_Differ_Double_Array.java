import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.util.Arrays.*;

public class Draft_5_Differ_Double_Array {
    //        random double array {5.3, 12.46, 28.123, 1.15, 88.888}
    //        или  {3.,5.,7.,12.}
    public static void main(String[] args) {
//    Создаем массив ArrayList, куда записываем в качестве элементов курс на текущую дату.
        Double[] array = {3., 5., 7., 12.};
        List<Double> list = new ArrayList<>(Arrays.asList(array));
        List<Double> diffArr = new ArrayList<>();

        System.out.println(list);
        System.out.println(list.get(1));

//        Инициализируем исходные переменные: от которой начинаем считать.
        double diff;
        double bestDiff;

//        Инициализируем переменную: длина (или размер) массива.
        int n = list.size();

//Ищем разность.
//Берем пример отсюда  https://ru.stackoverflow.com/questions/605367/%D0%9D%D0%B0%D0%B9%D1%82%D0%B8-%D1%80%D0%B0%D0%B7%D0%BD%D0%BE%D1%81%D1%82%D1%8C-%D0%B4%D0%B2%D1%83%D1%85-%D0%BD%D0%B0%D0%B8%D0%B1%D0%BE%D0%BB%D0%B5%D0%B5-%D0%B1%D0%BB%D0%B8%D0%B7%D0%BA%D0%B8%D1%85-%D0%B7%D0%BD%D0%B0%D1%87%D0%B5%D0%BD%D0%B8%D0%B9-%D0%BC%D0%B0%D1%81%D1%81%D0%B8%D0%B2%D0%B0?ysclid=lgnyn4q13a195995730
        for (int i = 0; i < n - 1; i++) {
//            diff = list(i + 1) - list[i];
//            if (bestDiff > diff) { /* что делаем тут? */ } }

        }
    }
}
