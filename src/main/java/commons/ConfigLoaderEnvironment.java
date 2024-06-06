package commons;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigLoaderEnvironment {
    private static final String ENV_PROPERTY = "app.env";
    public static Config config;

    public static void getConfig() {
        String env = System.getProperty(ENV_PROPERTY, "production");
        String configFileName = env + ".conf";
        config = ConfigFactory.load(configFileName);
    }

}
