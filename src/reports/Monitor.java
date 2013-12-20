package reports;

import java.util.AbstractMap.SimpleEntry;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class Monitor {

    public enum Health {

        ADRENALINE, ENDORPHINE, BERSERK, ENEMY, HP, CP
    }
    private static Logger log = Logger.getLogger(Monitor.class.getName());
    private String enemy = null;
    EnumMap<Health, Ratio> map = new EnumMap<>(Health.class);

    private Monitor() {
    }

    public Monitor(Map<String, Ratio> mapStringToRatio) {
        init(mapStringToRatio);
    }

    private void init(Map<String, Ratio> mapStringToRatio) {
        SimpleEntry<Health, Ratio> attribsEntry = null;
        Ratio r;
        for (Entry<String, Ratio> stringEntry : mapStringToRatio.entrySet()) {
            Health hlth = Health.valueOf(stringEntry.getKey().toUpperCase());
            r = stringEntry.getValue();
            map.put(hlth, r);
        }
    }

    private SimpleEntry<Health, Ratio> convertToEnumEntry(Entry<String, Ratio> stringEntry) {
        Ratio ratio = stringEntry.getValue();
        SimpleEntry<Health, Ratio> attribEntry = null;
        for (Health attribute : Health.values()) {
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

        for (Map.Entry<?, ?> e : map.entrySet()) {
            sb.append("\n");
            sb.append(e.getKey().toString());
            sb.append("\t");
            sb.append(e.getValue().toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
