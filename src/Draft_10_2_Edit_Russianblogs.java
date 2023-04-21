public class Draft_10_2_Edit_Russianblogs {

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

        Double[] data={3., 5., 8., 12.};;
        double min=minDifference(data);
        System.out.println(min);
    }

    public static double minDifference(Double[] data){
        if(data==null||data.length==0){
            return Double.MIN_VALUE;
        }
        sort(data,0,data.length-1);
        int len=data.length;
        double[] diff= new double[len - 1];
        for(int i=0;i<len-1;i++){
            diff[i]=data[i+1]-data[i];
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

    //QuickSort.Of course we can use Arrays.sort(),but I write it for practice.
    public static void sort(Double[] x, int s, int e){
        if(s>=e){
            return;
        }
        int i=s;
        int j=e;
        boolean flag=false;
        while(i!=j){
            if(x[i]>x[j]){
                swap(x,i,j);
                flag=!flag;
            }
            if(flag){
                i++;
            }else{
                j--;
            }
        }
        sort(x,s,i-1);
        sort(x,j+1,e);
    }

    public static void swap(Double[] x, int i, int j){
        Double tmp=x[i];
        x[i]=x[j];
        x[j]=tmp;
    }
}
