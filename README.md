chatathon
=========
hackathon chat


TODO
----

* add data structure for users
* add data structure for channels
* implement ping/pong
* add web ui client

* add logging (system, chat, web-access)
* add tests





Components
----

* Chat Server
* Web Client
* Info pages
* Bot registration
* Logging
* config
  * server name
  * server url and port
  * disclaimer
 

Dependencies
------------

Java 
* Stampy: https://github.com/mrstampy/Stampy
* sparkjava framework 

Javascript:
* http://jmesnil.net/stomp-websocket/doc/
* https://github.com/jmesnil/stomp-websocket
* https://github.com/JSteunou/webstomp-client



Notes
-----

* Initial checkin based on 
  https://sparktutorials.github.io/2015/11/08/spark-websocket-chat.html

* For web access logging, we should use logback-access.
  * add Maven dependency
  * logback access for JETTY: https://logback.qos.ch/access.html
  * Config JETTY via java: http://www.eclipse.org/jetty/documentation/current/embedding-jetty.html#_like_jetty_xml
  
