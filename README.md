# EasySharedPreference
an easy to use SharedPreference library. You can read/write your object to SharedPreference easily with few lines of code.
### Installing
Use Gradle:
```gradle
compile 'com.kingfisherphuoc:easy_sharedpreference_library:1.2'
```
### How to use?
Before you do anything with `SharedPreference`, you have to initialize the library first. You can do it in your `Application` class or in your first `Activity's OnCreate`: 
```java
/**
 * You have to initialize all your preference when open app.
 *
 * @param context
 * @param createDefaultPreference : true if we want to create default {@link SharedPreferences}
 * @param names                   : list of custom {@link SharedPreferences}
 */
SharedPreferencesManager.init(this, true, "Pre1","Pre2"); 
// or using default sharedpreference name only
SharedPreferencesManager.init(this, true);
```
After doing first config, you can use the `SharedPreferencesManager` at anywhere you want. You can check the sample project for more detail:
```java
// Primitive type
SharedPreferencesManager.getInstance().putValue("Test", 1); // save to default sharedPreference 
SharedPreferencesManager.getInstance().getValue("Test", Integer.class);// get from default
// Save your custom object:
SharedPreferencesManager.getInstance().putValue("testData", testGun);
Gun gun = SharedPreferencesManager.getInstance().getValue("testData", Gun.class); 
// Save list object to another SharedPreference
SharedPreferencesManager.getInstance(AnotherPreferenceName).putValue("guns", guns);
List<Gun> datas = SharedPreferencesManager.getInstance(AnotherPreferenceName).getValues("guns", Gun[].class); // get list object
// remove/clear value from sharedPreferences:
SharedPreferencesManager.getInstance().clear(); //SharedPreferencesManager.getInstance().remove("test");
// Print the log of all saved value from SharedPreferences:
SharedPreferencesManager.getInstance().printAllKeyValues();
```

### What's in the next version?
What are the things you want to have in this library? Contact me or create a new issue for it.

### License
Copyright 2017 Doan Hong Phuoc

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
