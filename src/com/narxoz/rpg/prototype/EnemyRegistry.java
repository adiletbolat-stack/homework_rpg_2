package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class EnemyRegistry {

    private final Map<String, Enemy> templates = new LinkedHashMap<>();

    public void registerTemplate(String key, Enemy prototype) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Template key must not be null/blank");
        }
        if (prototype == null) {
            throw new IllegalArgumentException("Prototype must not be null");
        }
        templates.put(key, prototype);
    }

    public Enemy createFromTemplate(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Template key must not be null/blank");
        }

        Enemy template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("Unknown template key: " + key);
        }

        return template.clone(); 
    }


    public Set<String> listTemplates() {
        return Collections.unmodifiableSet(templates.keySet());
    }

   
    public boolean hasTemplate(String key) {
        return templates.containsKey(key);
    }

 
    public int size() {
        return templates.size();
    }

    public void clear() {
        templates.clear();
    }

    public Map<String, Enemy> getTemplatesView() {
        return Collections.unmodifiableMap(templates);
    }
}
