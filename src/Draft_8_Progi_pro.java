//Взято отсюда: https://progi.pro/raznica-mezhdu-elementami-v-odnom-massive-v-java-3000719

public class Draft_8_Progi_pro {
    public static void main(String[] args) {

        int[] array = new int[] {7,8,5,7,2};

    }
    public static int MaxDiff(int[] array){

//        int[] array {7,8,5,7,2};
        int diff = 0;
        if (array.length == 0){
            return -1;
        }

        int max = 0;
        for (int i = 0; i < array.length-1; ++i){

            diff = Math.abs(array[i] - array[i+1]);
            if(max < diff)
                max = diff;
        }
        return max;
    }

}
