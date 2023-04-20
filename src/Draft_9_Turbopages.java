public class Draft_9_Turbopages {
//Взято отсюда:  https://translated.turbopages.org/proxy_u/en-ru.ru.4cc0039f-64419cfa-87bf90b3-74722d776562/https/stackoverflow.com/questions/3992228/get-smallest-difference-between-adjacent-array-elements-in-a-sorted-list
    public static void main(String[] args) {

}

    private double findNumerostyBinRangeConstant(double[] ratios) {
        double result = Double.MAX_VALUE;
        for (int i = 0; i<ratios.length-1; i++) {
            if (ratios[i+1]/ratios[i] < result){
                result = ratios[i+1]/ratios[i];
            }
        }
        return Math.sqrt(result);
    }

}
