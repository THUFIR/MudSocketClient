package reports;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class Monitor {

    private static Logger log = Logger.getLogger(Monitor.class.getName());
    private Map<Attribs, Ratio> mapOfAttributes = new HashMap<>();
    private String enemy = null;

    private Monitor() {
    }

    public Monitor(Map<String, Ratio> mapStringToRatio) {
        init(mapStringToRatio);
    }

    private void init(Map<String, Ratio> mapStringToRatio) {
        SimpleEntry<Attribs, Ratio> attribsEntry = null;
        for (Entry<String, Ratio> stringEntry : mapStringToRatio.entrySet()) {
            attribsEntry = null;
            attribsEntry = convertToEnumEntry(stringEntry);
            if (attribsEntry != null) {
                mapOfAttributes.put(attribsEntry.getKey(), attribsEntry.getValue());
            } else {
                enemy = stringEntry.getKey();     //assumes key is enemy value
                mapOfAttributes.put(Attribs.ENEMY, stringEntry.getValue());
            }
        }
    }

    private SimpleEntry<Attribs, Ratio> convertToEnumEntry(Entry<String, Ratio> stringEntry) {
        Ratio ratio = stringEntry.getValue();
        SimpleEntry<Attribs, Ratio> attribEntry = null;
        for (Attribs attribute : Attribs.values()) {
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
        for (Map.Entry<Attribs, Ratio> e : mapOfAttributes.entrySet()) {
            sb.append("\n");
            sb.append(e.getKey().name());
            sb.append("\t");
            sb.append(e.getValue().toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
