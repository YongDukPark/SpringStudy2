package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 최초 jvm이 실행할수 있게 한다.
    private static final SingletonService instanc = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instanc;
    }

    // 3. 생성자를 private로 선언하여 외부에서 new 키워드를 사용하면 객체 생성을 못하게 막는다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
