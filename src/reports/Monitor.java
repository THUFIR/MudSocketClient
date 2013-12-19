package reports;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class Monitor {

    private static Logger log = Logger.getLogger(Monitor.class.getName());
    private Map<Attribs, Ratio> atrMap = new HashMap<>();
    private String enemy = null;
    boolean fighting = false;

    private Monitor() {
    }

    public Monitor(Map<String, Ratio> map) {
        init(map);
    }

    private void init(Map<String, Ratio> map) {
        SimpleEntry<Attribs, Ratio> simpleEntry = null;
        for (Map.Entry<String, Ratio> entry : map.entrySet()) {
            simpleEntry = convertToEnumEntry(entry);
            if (simpleEntry != null) {
                atrMap.put(simpleEntry.getKey(), simpleEntry.getValue());
            } else {
                enemy = entry.getKey();
                atrMap.put(Attribs.ENEMY, entry.getValue());
            }
        }
    }

    private SimpleEntry<Attribs, Ratio> convertToEnumEntry(Entry<String, Ratio> stringEntry) {
        Ratio ratio = stringEntry.getValue();
        SimpleEntry<Attribs, Ratio> attribEntry = null;
        for (Attribs atribVal : Attribs.values()) {
            if (stringEntry.getKey().equalsIgnoreCase(atribVal.name())) {
                attribEntry = new HashMap.SimpleEntry<>(atribVal, ratio);
            }
        }
        return attribEntry;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfighting\t\t" + enemy + "\n");
        for (Map.Entry<Attribs, Ratio> e : atrMap.entrySet()) {
            sb.append("\n");
            sb.append(e.getKey().name());
            sb.append("\t");
            sb.append(e.getValue().toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
