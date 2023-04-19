import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Draft_5_Differ_Double_Array {
    //        random double array [5.3, 12.46, 28.123, 1.15, 88.888]
    public static void main(String[] args) {
//    Создаем массив ArrayList, куда записываем в качестве элементов курс на текущую дату.
        Double[] array = {5.3, 12.46, 28.123, 1.15, 88.888};
        List<Double> list = new ArrayList<>(Arrays.asList(array));

        System.out.println(list);

//        Инициализируем исходную переменную: от которой начинаем считать.
        double diff = list.get(0);
        double bestDiff = list.get(0);

//        Инициализируем переменную: длина (или размер) массива.
        int n = list.size();

//        Задаем цикл перебора массива для поиска разницы.
        for (double i = 0; i < n - 1; i++) {
//            diff = list[i + 1] - list[i]; if (bestDiff > diff) {
                /* что делаем тут? */

            }
        }
    }
