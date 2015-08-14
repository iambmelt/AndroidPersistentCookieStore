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
```java
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

<table>
    <tr>
        <td width="150" align="center">
            <a title="McGraw-Hill Education Texas" href="https://play.google.com/store/apps/details?id=com.mheducation.cedmobile&hl=en" rel="nofollow"><img src="http://i.imgur.com/o0qKBK4.png" width="80" height="80"></a>
            <br>
            McGraw-Hill Education Texas
        </td>
    </tr>
</table>

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

Credits
--
commons-io-2.4.jar

Apache Commons IO
Copyright 2002-2012 The Apache Software Foundation

This product includes software developed by 
The Apache Software Foundation (http://www.apache.org/).
