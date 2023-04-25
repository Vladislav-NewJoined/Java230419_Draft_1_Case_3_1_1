import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Draft_2_2_Test_add_to_testArray {
    public static void main(String[] args) {

//        List<String> testArray = new ArrayList<>();
//        testArray.add("списек");
//        testArray.add("инвентарских");
//        testArray.add("предметов");
//        testArray.add("в");
//        testArray.add("поездяльнике");

//        System.out.println(testArray.size());
//            System.out.println(testArray.toString());

//        for (int i = 0; i < testArray.size(); i++) {
//            System.out.print(testArray.get(i) + " ");
//        }

        // либо

//        for (String s : testArray) {
//            System.out.print(s + " ");
//        }
//        List<String> testArray = new ArrayList<>();
//        testArray.add("111");
//        testArray.add("112");
//        testArray.add("113");
//        System.out.println(testArray.toString() + " ");


//        List<String> list = new ArrayList<>();
//        List<String> anotherList = Arrays.asList("5", "12", "9", "3", "15", "88");
//        List<String> anotherList = Arrays.asList("01/03/2023", "02/03/2023", "03/03/2023", "04/03/2023");
//        list.addAll(anotherList);
//        System.out.println(list);

//        Самый ПРОСТОЙ СПОСОБ РАСПЕЧАТАТЬ ArrayList — использовать toString

//        List<String> a=new ArrayList<>();
//        a.add("111");
//        a.add("112");
//        a.add("113");
//        System.out.println(a.toString());



        List<String> testArray = new ArrayList<>();
        testArray.add("списек");
        testArray.add("инвентарских");
        testArray.add("предметов");
        testArray.add("в");
        testArray.add("поездяльнике");

        for(int i = 0; i < testArray.size(); i++) {
            System.out.print((testArray.get(i)).toString() + " ");
        }

    }
}