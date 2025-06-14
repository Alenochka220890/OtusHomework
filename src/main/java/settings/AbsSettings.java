package settings;

import java.io.File;

public abstract class AbsSettings {

    protected File propertyFile;

    public  AbsSettings(String fileName){
        String roatPath = System.getProperty("user.dir");
        propertyFile = new File(roatPath + "/src/main/java/resources/" + fileName);
    }
}
