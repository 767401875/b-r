import java.lang.reflect.Field;

public class ReflectTest {
    public static void main(String[] args) throws Exception{
        Class clz = Class.forName("Apple");
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }
    }
}
class Apple{


    private int price;
    public String name;
    public Apple(){

    }
    public Apple(String name, int price){
        this.name = name;
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}