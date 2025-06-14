package settings;

import java.io.IOException;
import java.util.Map;

public interface ISettings {
    Map<String, String> getSettings() throws IOException;
}
