import java.io.IOException;
import java.text.ParseException;
import java.util.*;
public class Draft_2_MaxMin_Differ_Apart {
    // Кейс «Анализатор курса валют».
    // 3. Очень сложное:
    //- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал. Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
    // Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
    // https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
    //Инфо здесь возможна ТекиДелайт https://www.techiedelight.com/ru/find-maximum-difference-between-two-elements-array/
    public static void main(String[] args) throws IOException, ParseException {
    //        03/2023 ""cv
    //        [74.8932, 75.2513, 75.4729, 75.4592, 75.4728, 75.4577, 75.9028, 75.9406, 75.4609, 75.1927, 75.7457, 76.4095, 76.6044, 77.2422, 76.8373, 76.9561, 76.3072, 76.4479, 76.5662, 76.5939, 76.9781, 77.0863]
    //        random array [5.3, 12.46, 28.123, 1.15, 88.888]
    //        одномерный array из Integer [5.3, 12.46, 28.123, 1.15, 88.888]


        Double[] array = {5.3, 12.46, 28.123, 1.15, 88.888};
        List<Double> listDiff = new ArrayList<>(Arrays.asList(array));

        System.out.println(listDiff);
        System.out.println(listDiff.get(1));

//        Нахождение наибольшего и наименьшего значений.
//        Инициализируем исходную переменную: от которой начинаем считать.
        Double minDiff = listDiff.get(0);
        Double maxDiff = listDiff.get(0);
//        Задаем переменную: длина (или размер) массива.
        int n = listDiff.size();

//        Задаем цикл перебора массива для поиска наибольшего и наименьшего значений.
        for (int i = 1; i < n; i++) {
            if (listDiff.get(i) > maxDiff) {
                maxDiff = listDiff.get(i);
            }
        }

        for (int i = 1; i < n; i++) {
            if (listDiff.get(i) < minDiff) {
                minDiff = listDiff.get(i);
            }
        }

//Ищем разность.
//Берем пример отсюда  https://ru.stackoverflow.com/questions/605367/%D0%9D%D0%B0%D0%B9%D1%82%D0%B8-%D1%80%D0%B0%D0%B7%D0%BD%D0%BE%D1%81%D1%82%D1%8C-%D0%B4%D0%B2%D1%83%D1%85-%D0%BD%D0%B0%D0%B8%D0%B1%D0%BE%D0%BB%D0%B5%D0%B5-%D0%B1%D0%BB%D0%B8%D0%B7%D0%BA%D0%B8%D1%85-%D0%B7%D0%BD%D0%B0%D1%87%D0%B5%D0%BD%D0%B8%D0%B9-%D0%BC%D0%B0%D1%81%D1%81%D0%B8%D0%B2%D0%B0?ysclid=lgnyn4q13a195995730
//        for (int i = 0; i < list.size() - 1; i++) { int diff = list[i + 1] - list[i]; if (bestDiff > diff) { /* что делаем тут? */ } }

        System.out.println("Наибольшее значение курса : " + maxDiff);
        System.out.println("Наименьшее значение курса : " + minDiff);


    }
}

