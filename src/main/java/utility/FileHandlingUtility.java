package utility;

import java.io.InputStream;

public class FileHandlingUtility {
    public InputStream getFileFromResources(String fileName) throws IllegalArgumentException {

        ClassLoader classLoader = getClass().getClassLoader();

        InputStream resource = classLoader.getResourceAsStream(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("-----The file name you provided, is not found!--------");
        } else {
            return resource;
        }

    }
}
