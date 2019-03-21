import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String, String> modelMap = new HashMap<>();
        modelMap.put("aa", "aaa");
        modelMap.put("bb", "bbb");
        modelMap.put("cc", "ccc");
        modelMap.put("aa", "qsq");
        for(String key : modelMap.keySet()){
            System.out.println("key:" + key + ", value:" + modelMap.get(key));
        }
        if(modelMap.containsKey("aa")){
            System.out.println("key:true");
        }
        if(modelMap.containsValue("bbbb")){
            System.out.println("value:true");
        }
    }
}