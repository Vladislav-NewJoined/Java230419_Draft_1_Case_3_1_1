//Взято отсюда: https://progi.pro/raznica-mezhdu-elementami-v-odnom-massive-v-java-3000719

public class Draft_8_2_Progi_pro {
//Создайте одну переменную экземпляра maxDiff и обновите, если в цикле вы найдете diff, превышающий текущий diff diff. как
//maxDiff = diff > maxDiff ? diff : maxDiff;
    public static void main(String[] args) {
    }

    public static int MaxDiff(int[] array){


        //create a variable to hold maxDiff;
        int maxDiff = 0;
        int diff = 0;



        if (array.length == 0){   // Checks for empty array
            return -1;
        }



        //Change from <= to < (otherwise you will get ArrayIndexOutOfBound exception
        for (int i = 0; i < array.length; ++i){



            if(i == array.length -1){
                diff = Math.abs(array[i] - array[0]);
            } else {
                diff = Math.abs(array[i] - array[i+1]); // Mistake. Can be
                // bigger than array
            }
            //check for max
            maxDiff = diff > maxDiff ? diff : maxDiff;
        }



        return maxDiff;
    }
}
