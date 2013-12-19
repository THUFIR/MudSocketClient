package mudsocketclient;

import java.util.Map;

public class Monitor {

    private Monitor() {
    }

    Monitor(Map<String, Ratio> map) {
        String attribute = null;
        Ratio ratio = null;
        for (Map.Entry<String, Ratio> e : map.entrySet()) {
            attribute = e.getKey();
            ratio = e.getValue();
            foo(attribute, ratio);
        }
    }

    private void foo(String attribute, Ratio ratio) {
        
    }
}
