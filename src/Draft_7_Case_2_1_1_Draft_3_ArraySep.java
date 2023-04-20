import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Draft_7_Case_2_1_1_Draft_3_ArraySep {
    public static void main(String[] args) {
//        {74.8932, 75.2513, 75.4729, 75.4592, 75.4728, 75.4577, 75.9028, 75.9406, 75.4609, 75.1927, 75.7457, 76.4095, 76.6044, 77.2422, 76.8373, 76.9561, 76.3072, 76.4479, 76.5662, 76.5939, 76.9781, 77.0863}
//        или {3., 5., 7., 12.}

        Double[] array = {3., 5., 7., 12.};
        List<Double> list = new ArrayList<>(Arrays.asList(array));

//        Задаем исходную переменную: от которой начинаем считать.
        Double max = list.get(0);

//        Задаем переменную: длина (или размер) массива.
        int n = list.size();

//        Задаем цикл перебора массива для поиска наибольшего и наименьшего значений.
        for (int i = 1; i < n; i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        System.out.println("Наибольшее значение курса : " + max);
    }
}
