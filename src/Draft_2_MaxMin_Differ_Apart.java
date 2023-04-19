import java.io.IOException;
import java.text.ParseException;
import java.util.*;
public class Draft_2_MaxMin_Differ_Apart {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    // Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
    // https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
    public static void main(String[] args) throws IOException, ParseException {
//        03/2023 ""cv
//        [74.8932, 75.2513, 75.4729, 75.4592, 75.4728, 75.4577, 75.9028, 75.9406, 75.4609, 75.1927, 75.7457, 76.4095, 76.6044, 77.2422, 76.8373, 76.9561, 76.3072, 76.4479, 76.5662, 76.5939, 76.9781, 77.0863]
//        random array [5.3, 12.46, 28.123, 1.15, 88.888]

        Double[] array = {5.3, 12.46, 28.123, 1.15, 88.888};
        List<Double> listDiff = new ArrayList<>(Arrays.asList(array));

        System.out.println(listDiff);
        System.out.println(listDiff.get(1));

//        Нахождение наибольшего и наименьшего значений.
//        Инициализируем исходную переменную: от которой начинаем считать.
        Double min = listDiff.get(0);
        Double max = listDiff.get(0);
//        Задаем переменную: длина (или размер) массива.
        int n = listDiff.size();

//        Задаем цикл перебора массива для поиска наибольшего и наименьшего значений.
        for (int i = 1; i < n; i++) {
            if (listDiff.get(i) > max) {
                max = listDiff.get(i);
            }
        }

        for (int i = 1; i < n; i++) {
            if (listDiff.get(i) < min) {
                min = listDiff.get(i);
            }
        }
        System.out.println("Наибольшее значение курса : " + max);
        System.out.println("Наименьшее значение курса : " + min);


    }
}

