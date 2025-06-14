package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesSettings extends AbsSettings implements ISettings {

    public PropertiesSettings(String fileName) {
        super(fileName);
    }

    @Override
    public Map<String, String> getSettings() throws IOException {
        Properties settingsProperties = new Properties();

        settingsProperties.load(new FileInputStream(propertyFile));

        Map<String, String> settings = new HashMap<>();

        for (Map.Entry entry : settingsProperties.entrySet()) {
            settings.put((String) entry.getKey(), (String) entry.getValue());
        }
        return settings;
    }
}
