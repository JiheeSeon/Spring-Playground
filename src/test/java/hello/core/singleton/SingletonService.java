package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    //자바가 뜨면서 static 영역 초기화, 생성한거 가지고 있음

    public static SingletonService getInstance(){ return instance; }

    private SingletonService(){ } //외부 생성 막아놓기

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
