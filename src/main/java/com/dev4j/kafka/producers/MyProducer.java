package com.dev4j.kafka.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MyProducer {

    public static final Logger log = LoggerFactory.getLogger(MyProducer.class);

    public static void main(String[] args) {
        Properties props=new Properties();
        props.put("bootstrap.servers","localhost:9092"); //Broker a conectar
        props.put("acks","1"); //Acknowledge estamos pidiendo un reconocimiento de que el mensaje si llegó almenos a un broker, en caso de tener más de 1 broker
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer"); //Serializamos a String porque vamos a enviar strings como mensajes (Key)
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer"); //Serializamos a String porque vamos a enviar strings como mensajes (Value)
        props.put("linger.ms","10");
        try(Producer<String, String>producer=new KafkaProducer<>(props);) {

            for(int i = 0; i<10000;i++){
                producer.send(new ProducerRecord<String,String>("devs4j-topic",String.valueOf(i),"devs4j-value"));
            }
            producer.flush();
        }
    }
}
