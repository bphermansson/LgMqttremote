# LgMqttremote
Sends LG TV remote codes via Mqtt

This is an Android app that sends LG TV remote codes via Mqtt. It's used with an Esp8266-based Mqtt-receiver/Ir-sender. 
The app sends codes to a Mqtt broker with the subject "irsender". The Esp listens for this topic, and sends the
corresponding code via IR to control the TV. 
The Esp-project is found at https://github.com/bphermansson/espMqttIrblaster

Codes:
//code="2,20DF48B7,32,1";
2 is for LG TV
Button 2 is 20DF48B7
32 is code length
1 is how many times to send code


Codes can be found at http://lirc.sourceforge.net/remotes/lg/AKB69680403
Use the code in the second column, without the "0x". If you want to send a 2,
set the code to 48B7. If you want to send two codes, type them both separated by a ,.
To send a 2(48B7) and a 1(8877), set code to 48B7,8877.