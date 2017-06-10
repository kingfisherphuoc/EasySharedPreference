# EasySharedPreference
an easy to use SharedPreference library. You can read/write your object to SharedPreference easily with few lines of code.
### Installing
Use Gradle:
```compile 'com.kingfisherphuoc:easy_sharedpreference_library:1.0'```
### How to use?
Before you do anything with `SharedPreference`, you have to initialize the library first. You can do it in your `Application` class or in your first `Activity's OnCreate`: 
```
/**
     * You have to initialize all your preference when open app.
     *
     * @param context
     * @param createDefaultPreference : true if we want to create default {@link SharedPreferences}
     * @param names                   : list of custom {@link SharedPreferences}
     */
    public static synchronized void init(Context context, boolean createDefaultPreference, String... names)
```

### What's in the next version?

### License
Copyright 2017 Doan Hong Phuoc

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
