import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Взято отсюда: https://russianblogs.com/article/9901498089/
//Здесь мы добавляем максимальную разницу.
public class Draft_14_Adding_max_Double {

    /**
     * Тема: Найти минимальное абсолютное значение разницы между двумя элементами в массиве.
     solution 1.sort the data array.Find the min difference between two adjacent element.
     solution 2.
     Пусть этот целочисленный массив будет a1, a2, ..., an
     Построить массив B = (b1, b2, ..., bn-1)
     b1 = a1-a2,
     b2 = a2-a3,
     b3 = a3-a4,
     ...
     bn-1 = an-1 - an

     Тогда в исходном массиве разница между любыми двумя целыми числами ai-aj (1 <= i, j <= n) может быть выражена как
     Непрерывное суммирование i-го по j-1-го элементов в B

     Например, b2 + b3 + b4 = (a2-a3) + (a3-a4) + (a4-a5) = a2-a5

     После того, как O (n) строит последовательность B

     Используйте аналогичный алгоритм «максимальная сумма подсекции», чтобы найти «минимальную абсолютную сумму подсекции»

     Однако, как найти «минимальную абсолютную сумму подсекции»? Я не понял это. , ,
     */
    public static void main(String[] args) {

        Double[] sourceArray ={12.,8., 1., 3., 18.};
        List<Double> data = new ArrayList<>(Arrays.asList(sourceArray));
        double min=minDifference(data);
        double max=maxDifference(data);
        System.out.println(min);
        System.out.println(max);
    }

    public static double minDifference(List<Double> data){
        if(data==null||data.size()==0){
            return Double.MIN_VALUE;
        }
//        sort(data,0,data.size()-1);
        int len=data.size();
        double[] diff= new double[len - 1];
        for(int i=0;i<len-1;i++){
            diff[i]= data.get(i + 1) - data.get(i);
        }
        //System.out.println(Arrays.toString(diff));
        return min(diff);
    }
    public static double min(double[] diff){
        if(diff==null||diff.length==0){
            return Double.MIN_VALUE;
        }
        double min=diff[0];
        for(int i=0,len=diff.length;i<len;i++){
            //not necessary,since 'int[] data' is sorted,so 'int[] diff' is progressively increased.
            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
            if(min>diff[i]){
                min=diff[i];
            }
        }
        return min;
    }

//Теперь максимальную разницу находим.
    public static double maxDifference(List<Double> data){
        if(data==null||data.size()==0){
            return Double.MAX_VALUE;
        }
        int len=data.size();
        double[] diff= new double[len - 1];
        for(int i=0;i<len-1;i++){
            diff[i]= data.get(i + 1) - data.get(i);
        }
        //System.out.println(Arrays.toString(diff));
        return max(diff);
    }
    public static double max(double[] diff){
        if(diff==null||diff.length==0){
            return Double.MAX_VALUE;
        }
        double max=diff[0];
        for(int i=0,len=diff.length;i<len;i++){
            //not necessary,since 'int[] data' is sorted,so 'int[] diff' is progressively increased.
            //int tmp=diff[i]>0?diff[i]:(-diff[i]);
            if(max<diff[i]){
                max=diff[i];
            }
        }
        return max;
    }
}
