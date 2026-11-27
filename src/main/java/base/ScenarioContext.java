package base;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, Object> scenarioContext = new HashMap<String, Object>();

    public void setScenarioContext(String key, Object value){
        scenarioContext.put(key, value);
    }

    public Object getScenarioContext(String key){
        return scenarioContext.get(key);
    }

    public void clearScenarioContext(){scenarioContext.clear();}

    public boolean containsKey(String key) {

        return scenarioContext.containsKey(key);
    }
}