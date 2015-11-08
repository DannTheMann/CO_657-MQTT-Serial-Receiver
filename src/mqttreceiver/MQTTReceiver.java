/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttreceiver;

import java.awt.Robot;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

/**
 *
 * @author Daniel
 */
public class MQTTReceiver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
        
            MQTT mqtt = new MQTT(); 
            mqtt.setHost("tcp://m20.cloudmqtt.com:15385");
            mqtt.setUserName("peljuavl");
            mqtt.setPassword("OMP7oa36i7HZ");
        
            Robot robot = new Robot();
            
            BlockingConnection connection = mqtt.blockingConnection();
            connection.connect();
            
            Topic[] topics = {new Topic("Pokemon", QoS.AT_LEAST_ONCE)};
            
            while(true){
            
                System.out.println("Awaiting input!");   
                
                Message message = connection.receive();
                System.out.println(message.getTopic());
                byte[] payload = message.getPayload();
                // process the message then:
                
                String input = new String(payload, "UTF-8");
                
                switch(input){
                    
                    case "up":
                        robot.keyPress(38);
                        Thread.sleep(100);
                        robot.keyRelease(38);
                        break;
                    case "down":
                        robot.keyPress(40);
                        Thread.sleep(100);
                        robot.keyRelease(40);
                        break;
                    case "left":
                        robot.keyPress(37);
                        Thread.sleep(100);
                        robot.keyRelease(37);                        
                        break;
                    case "right":
                        robot.keyPress(39);
                        Thread.sleep(100);
                        robot.keyRelease(39);                        
                        break;
                    case "a":
                        robot.keyPress(65);
                        Thread.sleep(100);
                        robot.keyRelease(65);                        
                        break;       
                    case "b":
                        robot.keyPress(66);
                        Thread.sleep(100);
                        robot.keyRelease(66);                        
                        break;     
                    case "c":
                        robot.keyPress(67);
                        Thread.sleep(100);
                        robot.keyRelease(67);                        
                        break;                            
                
                }
                
                System.out.println("Ack " + input);
                
                message.ack();
            
            }
            
        }catch(Exception e){
        
        }
    }
    
}
