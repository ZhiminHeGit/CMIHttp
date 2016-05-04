import java.util.HashMap;

/**
 * Created by zhimin on 5/3/16.
 */
public class TestContainerCopy {
    static class Value {
        int a;
        int b;
    }

    public static void main(String[] args) {
        Value v = new Value();
        v.a = 1;
        v.b = 1;

        HashMap<String, Value> map = new HashMap<>();
        map.put("1", v);
        v.a = 2;
        System.out.print(map.get("1").a);
        map.get("1").a = 3;
        System.out.print(v.a);

    }
}
