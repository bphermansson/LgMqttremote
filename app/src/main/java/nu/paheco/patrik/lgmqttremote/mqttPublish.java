package nu.paheco.patrik.lgmqttremote;

/**
 * Created by patrik on 11/5/16.
 */

import android.content.Context;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import static android.content.Context.MODE_PRIVATE;


class SimpleCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("-------------------------------------------------");
        System.out.println("| Topic:" + topic);
        System.out.println("| Message: " + new String(message.getPayload()));
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete
    }
}


public class mqttPublish {
    public static void main(Context context, String mqttip, String mqttuser, String mqttpass, String mqtt_topic, String content){
        int qos = 2;
        String broker = "tcp://" + mqttip + ":1883";
        //Log.d("Broker",broker);
        //Log.d("Topic", mqtt_topic);

        String clientId = "LgMqttRemote";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(mqttuser);
            connOpts.setPassword(mqttpass.toCharArray());
            //System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            //sampleClient.subscribe("#", 1);
            //System.out.println("Connected");
            //System.out.println("Publish message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            //sampleClient.setCallback(new SimpleCallback());
            sampleClient.publish(mqtt_topic, message);
            //System.out.println("Message published");

            //Toast tea = Toast.makeText(context, "Send", Toast.LENGTH_LONG);
            //tea.show();

            /*try {
                Thread.sleep(5000);
                sampleClient.disconnect();
            } catch(Exception e) {
                e.printStackTrace();
            }*/
            //System.out.println("Disconnected");
            //System.exit(0);
        } catch(MqttException me){
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("except " + me);
            me.printStackTrace();
        }
    }
}

