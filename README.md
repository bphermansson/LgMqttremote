# LgMqttremote
Sends LG TV remotes via Mqtt

This is an Android app that sends LB TV remotes via Mqtt. Its used with an Esp8266-based Mqtt-receiver/Ir-sender. 
The app sends codes to a Mqtt broker with the subject "irsender". The Esp listens for this topic, and sends the
corresponding code via IR to control the TV. 
The Esp-project is found https://github.com/bphermansson/espMqttIrblaster/blob/master/Lightmeter_irsender/Lightmeter_irsender.ino.
