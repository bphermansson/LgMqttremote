# LgMqttremote
Sends LG TV remote codes via Mqtt

This is an Android app that sends LG TV remote codes via Mqtt. It's used with an Esp8266-based Mqtt-receiver/Ir-sender. 
The app sends codes to a Mqtt broker with the subject "irsender". The Esp listens for this topic, and sends the
corresponding code via IR to control the TV. 
The Esp-project is found at https://github.com/bphermansson/espMqttIrblaster

The codes for Power, Source, Program + and - are hardcoded and cant be changed.
The codes for Preset 1, 2, 3 and Volume + and - can be changed in the apps settings. The labels
on the preset buttons can also be changed.
The presets can for example be set to your favorite channels.
The volume button codes can be changed if you use an external amplifier.

Codes can be found at http://lirc.sourceforge.net/remotes/lg/AKB69680403
Use the code from the second column, without the "0x". If you want to send a 2,
set the code to 48B7. If you want to send two codes, type them both separated by a ,.
To send a 2(48B7) and a 1(8877), set code to 48B7,8877.

Some codes:<br>
1 - 8877<br>
2 - 48B7<br>
3 - C837<br>
4 - 28D7<br>

If you want to use an external amplifier (only Yamaha supported by now), check use external amp.
