package hello.core.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //이부분에서 문제가 발생한다.
    }

    public int getPrice(){
        return price;
    }
}
