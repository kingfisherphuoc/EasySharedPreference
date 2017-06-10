package com.kingfisher.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String AnotherPreferenceName = "my_test_preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferencesManager.init(this, true, AnotherPreferenceName);

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnLoadData).setOnClickListener(this);
    }

    private void loadData() {
        showLog("Get stored value: " + SharedPreferencesManager.getInstance().getValue("Test", Integer.class));
        showLog("Test first: " + SharedPreferencesManager.getInstance().getValue("testData", Gun.class));
        showLog("Person: " + SharedPreferencesManager.getInstance().getValue("person", Person.class));
        showLog("Fruits: " + SharedPreferencesManager.getInstance(AnotherPreferenceName).getValues("fruits", String[].class));
        List<Gun> datas = SharedPreferencesManager.getInstance(AnotherPreferenceName).getValues("guns", Gun[].class);
        if (datas == null) return;
        for (Gun gun1 : datas) {
            showLog(gun1.toString());
        }
    }

    private void saveData() {
        SharedPreferencesManager.getInstance().putValue("Test", 1);
        Gun testGun = new Gun("test first!", 15);
        Gun gun = new Gun("a boss with a gun!", 2);
        SharedPreferencesManager.getInstance().putValue("testData", testGun);
        SharedPreferencesManager.getInstance().putValue("person", new Person(testGun, true, "Hell Boy"));
        // using another shared preference
        SharedPreferencesManager.getInstance(AnotherPreferenceName).putValue("fruits",
                Arrays.asList(new String[]{"banana", "apple", "bean"}));
        List<Gun> guns = Arrays.asList(new Gun[]{gun, testGun});
        SharedPreferencesManager.getInstance(AnotherPreferenceName).putValue("guns", guns);

    }

    public void showLog(String mess) {
        Log.e("Main", mess);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                saveData();
                break;
            case R.id.btnLoadData:
                loadData();
                break;
        }
    }

    public static class Gun {
        public String des;
        public int id;

        public Gun(String des, int id) {
            this.des = des;
            this.id = id;
        }

        @Override
        public String toString() {
            return "TestData{" +
                    "des='" + des + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    public static class Person {
        public Gun gun;
        public boolean hasGun;
        public String name;

        public Person(Gun gun, boolean hasGun, String name) {
            this.gun = gun;
            this.hasGun = hasGun;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "testData=" + gun +
                    ", hasGun=" + hasGun +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
