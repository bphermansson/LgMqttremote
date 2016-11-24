# LgMqttremote
Sends LG TV remote codes via Mqtt

This is an Android app that sends LG TV remote codes via Mqtt. It's used with an Esp8266-based Mqtt-receiver/Ir-sender. 
The app sends codes to a Mqtt broker with the subject "irsender". The Esp listens for this topic, and sends the
corresponding code via IR to control the TV. 
The Esp-project is found at https://github.com/bphermansson/espMqttIrblaster