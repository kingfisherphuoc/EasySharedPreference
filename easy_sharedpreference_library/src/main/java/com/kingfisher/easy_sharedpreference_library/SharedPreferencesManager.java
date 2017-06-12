package com.kingfisher.easy_sharedpreference_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kingfisher on 6/10/17.
 */

public class SharedPreferencesManager {

    private static final String DEFAULT_SHARED_PREFERENCE = "MySharedPreference";
    private static HashMap<String, SharedPreferencesManager> preferenceManagerHashMap = new HashMap<>();
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    private Gson gson;

    private static SharedPreferencesManager getSharedPreferenceManager(Context context, String sharedPreferenceName) {
        if (preferenceManagerHashMap == null) preferenceManagerHashMap = new HashMap<>();
        // get or create new sharedPreferenceManager
        String name = TextUtils.isEmpty(sharedPreferenceName) ? DEFAULT_SHARED_PREFERENCE : sharedPreferenceName;
        SharedPreferencesManager sharedPreferencesManager = preferenceManagerHashMap.get(name);
        if (sharedPreferencesManager == null) {
            sharedPreferencesManager = new SharedPreferencesManager(context, name);
            preferenceManagerHashMap.put(name, sharedPreferencesManager);
        }
        return sharedPreferencesManager;
    }


    private SharedPreferencesManager(Context context, String name) {
        sharedPreference = context.getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        gson = new Gson();
    }

    /**
     * You have to initialize all your preference when open app.
     *
     * @param context
     * @param createDefaultPreference : true if we want to create default {@link SharedPreferences}
     * @param names                   : list of custom {@link SharedPreferences}
     */
    public static synchronized void init(Context context, boolean createDefaultPreference, String... names) {
        if (createDefaultPreference) {
            getSharedPreferenceManager(context, DEFAULT_SHARED_PREFERENCE);
        }
        if (names == null || names.length == 0) return;
        for (String name : names) {
            getSharedPreferenceManager(context, name);
        }
    }

    /**
     * Get {@link SharedPreferences} with name: name in {@link Context#MODE_PRIVATE}.
     *
     * @param name
     * @return
     */
    public static synchronized SharedPreferencesManager getInstance(String name) {
        SharedPreferencesManager sharedPreferencesManager = preferenceManagerHashMap.get(name);
        if (sharedPreferencesManager == null)
            throw new IllegalStateException("The share preference: " + name + " is not initialized before. You have to initialize it first by calling init(Context, boolean, String...) function");
        return sharedPreferencesManager;
    }

    /**
     * Get default sharedPreference name: {@link #DEFAULT_SHARED_PREFERENCE}
     *
     * @return
     */
    public static synchronized SharedPreferencesManager getInstance() {
        SharedPreferencesManager sharedPreferencesManager = preferenceManagerHashMap.get(DEFAULT_SHARED_PREFERENCE);
        if (sharedPreferencesManager == null)
            throw new IllegalStateException("The default share preference is not initialized before. You have to initialize it first by calling init(Context, boolean, String...) function");
        return sharedPreferencesManager;
    }

    /**
     * Easy to save your value here.
     *
     * @param key   : the key you want to save the object with
     * @param value : can be any kind of object (int, long, boolean, string, float, YourConvertableObject)
     * @return
     */
    public SharedPreferencesManager putValue(String key, Object value) {

        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            String json = gson.toJson(value);
            editor.putString(key, json);
        }
        editor.commit();
        return this;
    }

    /**
     * Get value which is saved
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getValue(String key, Class<T> type) {
        if (type == Integer.class) {
            Integer value = sharedPreference.getInt(key, 0);
            return (T) value;
        } else if (type == Boolean.class) {
            Boolean value = sharedPreference.getBoolean(key, false);
            return (T) value;
        } else if (type == Float.class) {
            Float value = sharedPreference.getFloat(key, 0);
            return (T) value;
        } else if (type == String.class) {
            String value = sharedPreference.getString(key, "");
            return (T) value;
        } else if (type == Long.class) {
            Long value = sharedPreference.getLong(key, 0);
            return (T) value;
        } else {
            String json = sharedPreference.getString(key, "");
            if (TextUtils.isEmpty(json)) {
                return null;
            } else {
                T value = gson.fromJson(json, type);
                return value;
            }
        }
    }

    /**
     * Get List of object which is saved in {@link SharedPreferences}
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getValues(String key, Class<T[]> clazz) {
        String json = sharedPreference.getString(key, "");
        if (TextUtils.isEmpty(json)) return null;
        T[] values = gson.fromJson(json, clazz);
        List<T> list = new ArrayList<T>();
        list.addAll(Arrays.asList(values));
        return list;
    }


    /**
     * remove a key/value pair from shared preference
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key).commit();
    }

    /**
     * Clear all the {@link SharedPreferences}
     */
    public void clear() {
        editor.clear().commit();
    }

    /**
     * Log all the key/value pairs which are stored in the {@link SharedPreferences}
     */
    public void printAllKeyValues() {
        Map<String, ?> allEntries = sharedPreference.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry == null || entry.getValue() == null) continue;
            Log.e("SharedPreferenceManager", entry.getKey() + ": " + entry.getValue().toString());
        }
    }


}
