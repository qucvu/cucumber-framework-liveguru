package commons;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import enums.Environment;

public class ConfigLoaderEnvironment {
    private static final String ENV_PROPERTY = "app.env";
    public static Config config;

    public static void getConfig() {
        Environment env = Environment.valueOf(System.getProperty(ENV_PROPERTY, Environment.PRODUCTION.toString()).toUpperCase());
        String configFileName = env + ".conf";
        config = ConfigFactory.load(configFileName);
    }

}
