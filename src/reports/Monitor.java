package reports;

import java.util.AbstractMap.SimpleEntry;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class Monitor {

    private static Logger log = Logger.getLogger(Monitor.class.getName());
    private String enemy = null;
    Map<String, String> stringMap = new HashMap<>();
    EnumMap<ATTRIBUTES, String> eMap = new EnumMap<>(ATTRIBUTES.class);

    private Monitor() {
    }

    public Monitor(Map<String, Ratio> mapStringToRatio) {
        init(mapStringToRatio);
    }

    //Attribs attr = Attribs.valueOf(stringValue.toUpperCase());
    private void init(Map<String, Ratio> mapStringToRatio) {
        SimpleEntry<ATTRIBUTES, Ratio> attribsEntry = null;
        Ratio r;
        for (Entry<String, Ratio> stringEntry : mapStringToRatio.entrySet()) {
            ATTRIBUTES attribute = ATTRIBUTES.valueOf(stringEntry.getKey().toUpperCase());
            r = stringEntry.getValue();
            eMap.put(attribute, enemy);
        }
    }

    private SimpleEntry<ATTRIBUTES, Ratio> convertToEnumEntry(Entry<String, Ratio> stringEntry) {
        Ratio ratio = stringEntry.getValue();
        SimpleEntry<ATTRIBUTES, Ratio> attribEntry = null;
        for (ATTRIBUTES attribute : ATTRIBUTES.values()) {
            if (stringEntry.getKey().equalsIgnoreCase(attribute.name())) {
                attribEntry = new HashMap.SimpleEntry<>(attribute, ratio);
            }
        }
        return attribEntry;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfighting\t\t" + enemy + "\n");

        for (Map.Entry<?, ?> e : eMap.entrySet()) {
            sb.append("\n");
            sb.append(e.getKey().toString());
            sb.append("\t");
            sb.append(e.getValue().toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
