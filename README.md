AndroidPersistentCookieStore
=========

A customizable thread-safe java.net.* based cookie persistence toolkit.

Key features
--
  - Swappable components
    * You can choose which java.net.CookieStore implementation you want to use to manage in-memory Cookies.
    * BYO-Persistence (if you want!)
    * Supply your own exception handler
  - Thread safe
    * Synchronized constructive & destructive transactions
    * Non-blocking read operations
  - Transparency
    * Logs silently, verbosely, or on errors.
    * Open-source!

Version
--
0.1

Release notes
--
Consider this project in alpha. Tests, build-automation, Travis CI all coming soon. YOU CAN HELP!

Get going!
--
To start using APCS, in your app's android.app.Application subclass type:
```
CookieManager cookieManager = 
    new CookieManager(new DefaultPersistentCookieStore(new File(
                        getExternalFilesDir(null)
                        + File.separator
                        + ".cookies.dat")),
                        CookiePolicy.ACCEPT_ORIGINAL_SERVER);

CookieHandler.setDefault(cookieManager);
```

Who's using APCS?
--
Shoot me an email! I'll link your project or app here.

License
--
   Copyright 2014 Brian Melton

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

