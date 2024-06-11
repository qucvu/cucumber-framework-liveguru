package stepsDefinition;

import java.util.HashMap;
import java.util.Map;

public class SharedState {
    private final Map<String, Object> dataContext;

    public SharedState() {
        dataContext = new HashMap<String, Object>();
    }

    public void setDataContext(String key, Object value) {
        dataContext.put(key, value);
    }

    public Object getDataContext(String key) {
        return dataContext.get(key);
    }


}
