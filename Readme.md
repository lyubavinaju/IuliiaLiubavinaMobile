How to run tests: <br>
pow.xml contains  <code><ts.appium>http://localhost:4723/wd/hub </ts.appium> </code> path to appium hub. <br>
nativeTNG.xml and webTNG.xml contains parameters, deviceName parameter can be changed. <br>
**mvn test -P native** - run tests for native application<br>
**mvn test -P web** - run tests for web application<br>
