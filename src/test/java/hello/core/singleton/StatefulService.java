package hello.core.singleton;

public class StatefulService {
    //private int price;

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        //this.price = price; //이부분에서 문제가 발생한다.
        return price;   // 상태유지를 하면 안된다.
    }

//    public int getPrice(){
//        return price;
//    }
}
