package commons;

import java.io.File;
import java.nio.file.Paths;

public class GlobalConstants {
    public final static String PROJECT_PATH = System.getProperty("user.dir");
    public static final boolean HEADLESS = false;
    public static final String DATA_RECORD = PROJECT_PATH + File.separator + "dataRecord" + File.separator;
    public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
    private final static String USER_PROFILE = System.getProperty("user.home");
    public final static String OS_NAME = System.getProperty("os.name");
    public final static int LONG_TIMEOUT = 30;
    public final static int SHORT_TIMEOUT = 3;
    public final static String OPERA_LAUNCHER_EXE_LOCATION = Paths.get(USER_PROFILE, "AppData", "Local", "Programs", "Opera", "opera.exe").toString();
    public final static String UPLOAD_FILE_FOLDER = PROJECT_PATH + "/uploadFiles";

}
