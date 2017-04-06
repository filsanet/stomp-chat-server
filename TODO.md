TODO
----

* remove stampy/stomp stuff. done.
* use raw websockets.
* create a YapFrame class
* Implement Handler for WebSocket

YAPCHAT - Yet Another Protocol for CHAT
---

Made of Frames

- Command
- Headers
- Body ^@

COMMANDS

client-commands:
  * CONNECT nick/login/password
  * DISCONNECT
  * SUBSCRIBE
  * UNSUBSCRIBE
  * SEND destination=#ch|@user
  * PONG

server-commands:
  * MESSAGE
  * ERROR
  * PING
  * DATA(needed?)
