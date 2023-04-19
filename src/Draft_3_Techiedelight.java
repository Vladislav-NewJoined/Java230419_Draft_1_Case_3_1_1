public class Draft_3_Techiedelight {
    // Кейс «Анализатор курса валют».
    // 3. Очень сложное:
    //- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал. Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
    // Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
    // https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
    //Инфо здесь возможна ТекиДелайт https://www.techiedelight.com/ru/find-maximum-difference-between-two-elements-array/

    // Наивная функция для нахождения максимальной разницы между двумя элементами в
    // массив, в котором меньший элемент располагается перед большим элементом
    public static void main(String[] args) {
        int[] A = { 2, 7, 9, 5, 1, 3, 5 };

        int diff = getMaxDiff(A);
        if (diff != Integer.MIN_VALUE) {
            System.out.print("The maximum difference is " + diff);
        }
    }

    public static int getMaxDiff(int[] A) {
        int diff = Integer.MIN_VALUE;

        int n = A.length;
        if (n == 0) {
            return diff;
        }

        for (int i = 0; i < n - 1; i++)
        {
            for (int j = i + 1; j < n; j++) {
                if (A[j] > A[i]) {
                    diff = Integer.max(diff, A[j] - A[i]);
                }
            }
        }

        return diff;
    }
}
