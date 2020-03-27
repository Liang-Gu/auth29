package com.wbst.producer;

import com.alibaba.fastjson.JSON;
import com.wbst.domain.MessageTitle;
import com.wbst.domain.SwipeCardEvent;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;

//消息生产者   测试用
@Service
public class Topic_Producer {
    public void sendMessage(String message){
        try {
            //创建连接工厂
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            activeMQConnectionFactory.setTrustAllPackages(true);
            activeMQConnectionFactory.setMaxThreadPoolSize(1);
            //连接到JMS提供者
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            //事务性会话，自动确认消息
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //消息的目的地
            Destination destination = session.createTopic("LenelEventInfo");
            //消息生产者
            MessageProducer producer = session.createProducer(destination);

            //对象消息
            MessageTitle messageTitle = new MessageTitle();
            messageTitle.setSender("消息发送者， 主要指发送该消息的适配器");
            messageTitle.setSourceSystem("消息来源系统，如Lenel系统");
            messageTitle.setMessageType("1-1");
            messageTitle.setDataType("数据类型");
            messageTitle.setActionName("操作类型");
            messageTitle.setDeviceOuterID("2222-3333");
            messageTitle.setMessageNo("消息流水号（sender+时间戳）");

            //{"accessResult":1,"cardNumber":"卡号","cardholder":"持卡人","personCode"-:"员工号","time":1583464808777}
            SwipeCardEvent swipeCardEvent = new SwipeCardEvent();
            swipeCardEvent.setCardNumber("卡号");
            swipeCardEvent.setPersonCode("员工号");
            swipeCardEvent.setCardholder("持卡人");
            swipeCardEvent.setTime(new Date());
            swipeCardEvent.setAccessResult(1);
            messageTitle.setContent(swipeCardEvent);

            String s = JSON.toJSONString(messageTitle);
            TextMessage textMessage = session.createTextMessage(s);
            producer.send(textMessage);
//            ObjectMessage objectMessage = session.createObjectMessage();
//            objectMessage.setObject(messageTitle);
//            producer.send(objectMessage);

            session.commit();
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}

