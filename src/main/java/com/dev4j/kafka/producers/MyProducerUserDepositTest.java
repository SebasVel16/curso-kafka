package com.dev4j.kafka.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MyProducerUserDepositTest {
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
        try(Producer<String, String> producer=new KafkaProducer<>(props);) {

            producer.send(new ProducerRecord<String,String>("devs4j-topic","User-1020","{'Action' : 'Deposit', 'Amount' : 200, 'Timestamp' : 2020-08-25T00:00}"));
            producer.send(new ProducerRecord<String,String>("devs4j-topic","User-1020","{'Action' : 'Deposit', 'Amount' : 100, 'Timestamp' : 2020-08-25T01:00}"));
            producer.send(new ProducerRecord<String,String>("devs4j-topic","User-1020","{'Action' : 'Deposit', 'Amount' : 200, 'Timestamp' : 2020-08-25T02:00}"));
            producer.send(new ProducerRecord<String,String>("devs4j-topic","User-1020","{'Action' : 'Withdraw', 'Amount' : 300, 'Timestamp' : 2020-08-25T03:00}"));
            producer.send(new ProducerRecord<String,String>("devs4j-topic","User-1021","{'Action' : 'Deposit', 'Amount' : 200, 'Timestamp' : 2020-08-25T00:00}"));
            producer.send(new ProducerRecord<String,String>("devs4j-topic","User-1021","{'Action' : 'Deposit', 'Amount' : 200, 'Timestamp' : 2020-08-25T01:00}"));

            producer.flush();
        }
    }
}
