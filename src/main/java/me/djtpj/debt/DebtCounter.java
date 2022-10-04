package me.djtpj.debt;

import io.github.djtpj.JsonPrimitive;
import io.github.djtpj.JsonSave;
import io.github.djtpj.JsonStreamer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
public class DebtCounter implements JsonStreamer {
    public final static File debtFile = new File("debt.json");

    @JsonSave(value = JsonPrimitive.INT)
    private int debt;

     // Is a set so that no duplicate triggers can be in the registry
    @Getter(AccessLevel.NONE)
    private HashSet<String> usedTriggers;

    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();

        JSONArray array = new JSONArray();
        array.addAll(usedTriggers);

        object.put("usedTriggers", array);

        return object;
    }

    @Override
    public void deserialize(JSONObject jsonObject) {
        JSONArray array = (JSONArray) jsonObject.get("usedTriggers");
        if (array != null) {
            usedTriggers.clear();
            usedTriggers.addAll(array);
        }
    }

    private static DebtCounter instance;

    private DebtCounter() {
        usedTriggers = new HashSet<>();

        read(debtFile);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DebtCounter.getInstance().write(debtFile)));
    }

    public void addUsedTrigger(String trigger) {
        usedTriggers.add(trigger);
    }

    public String[] getUsedTriggers() {
        return usedTriggers.toArray(new String[0]);
    }

    public static DebtCounter getInstance() {
        if (instance == null) {
            instance = new DebtCounter();
        }
        return instance;
    }

    public void debt(int amount) {
        debt += amount;
    }
}
