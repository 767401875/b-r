public class GenericTest {
    public static void main(String[] args){
        Box<String> name = new Box<String>("corn");
        Box<Integer> age = new Box<Integer>(7465);
        Box<Number> number = new Box<Number>(314);
        getData(name);
        getData(age);
        getData(number);
        System.out.println(name.getClass() == age.getClass());
        System.out.println(age.getClass() == number.getClass());
//        getUpperNumberData(name);
        getUpperNumberData(age);
        getUpperNumberData(number);
    }
    public static void getData(Box<?> data){
        System.out.println("data:" +data.getData());
    }
    public static void getUpperNumberData(Box<? extends  Number> data){
        System.out.println(data.getData());
    }

}
class Box<T>{
    private T data;
    public Box(){

    }
    public Box(T data){
        this.data = data;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}