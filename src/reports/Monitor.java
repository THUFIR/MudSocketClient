package reports;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class Monitor {

    private static Logger log = Logger.getLogger(Monitor.class.getName());
    private Map<String, Ratio> at = new HashMap<>();
    private String enemy = null;
    boolean fighting = false;

    private Monitor() {
    }

    public Monitor(Map<String, Ratio> map) {
        init(map);
    }

    private void init(Map<String, Ratio> map) {
        SimpleEntry<Attribs, Ratio> z = null;
        for (Map.Entry<String, Ratio> e : map.entrySet()) {
            z = convertToEnumEntry(e);
        }
    }

    private SimpleEntry<Attribs, Ratio> convertToEnumEntry(Entry<String, Ratio> stringEntry) {
        Ratio r = stringEntry.getValue();
        SimpleEntry<Attribs, Ratio> attribEntry = null;
        for (Attribs atribVal : Attribs.values()) {
            if (stringEntry.getKey().equalsIgnoreCase(atribVal.name())) {
                attribEntry = new HashMap.SimpleEntry<>(atribVal, r);
            }
        }
        return attribEntry;
    }

    private Entry<Attribs, Ratio> setEnemy(String attribute, Ratio ratio) {
        Map.Entry<Attribs, Ratio> entry = null;
        for (Attribs attrib : Attribs.values()) {
            if (attribute.equalsIgnoreCase(attribute)) {
            } else {
                enemy = attribute;
                fighting = true;
            }
            entry = new HashMap.SimpleEntry<>(attrib, ratio);
            return entry;
        }
        return entry;
    }

    public Map.Entry<String, Ratio> getAttribute(String s) {
        Entry<String, Ratio> attribute = null;
        String enemy = "enemy";
        for (Map.Entry<String, Ratio> e : at.entrySet()) {
            if (!s.equalsIgnoreCase(e.getKey())) {
                enemy = e.getKey();
            }
            attribute = e;
        }
        return attribute;
    }
}