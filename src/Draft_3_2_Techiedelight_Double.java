public class Draft_3_2_Techiedelight_Double {
    // Кейс «Анализатор курса валют».
    // 3. Очень сложное:
    //- Найти самые сильные скачки в этот промежуток, дни, когда курс сильно вырос или упал. Автоматически скачать текст статьи из википедии, отвечающей за факты на эту дату
    // Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
    // https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
    //Инфо здесь возможна ТекиДелайт https://www.techiedelight.com/ru/find-maximum-difference-between-two-elements-array/

    // Наивная функция для нахождения максимальной разницы между двумя элементами в
    // массив, в котором меньший элемент располагается перед большим элементом
    public static int getMaxDiff(double[] A) {
        double diff = Double.MIN_VALUE;

        int n = A.length;
        if (n == 0) {
            return (int) diff;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (A[j] > A[i]) {
                    diff = Double.max(diff, A[j] - A[i]);
                }
            }
        }

        return (int) diff;
    }

    public static void main(String[] args) {
        double[] A = {2., 7., 9., 5., 1., 3., 5.};

        int diff = getMaxDiff(A);
        if (diff != Double.MIN_VALUE) {
            System.out.print("The maximum difference is " + diff);
        }
    }
}