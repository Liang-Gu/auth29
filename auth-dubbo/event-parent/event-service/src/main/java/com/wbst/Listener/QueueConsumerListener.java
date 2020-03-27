package com.wbst.Listener;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbst.domain.MessageTitle;
import com.wbst.domain.Person;
import com.wbst.domain.SwipeCardEvent;
import com.wbst.service.PersonService;
import com.wbst.service.SwipeCardEventService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.Map;

//监听消息队列消息
@Component
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class QueueConsumerListener implements ApplicationRunner {

    @Autowired
    private SwipeCardEventService swipeCardEventService;

    @Reference
    private PersonService personService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    public void init() throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        activeMQConnectionFactory.setTrustAllPackages(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination dest = session.createTopic("LenelEventInfo");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(dest);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage=(TextMessage)message;
                    String text = textMessage.getText();
//                    System.out.println(text);
                    //讲读取到的消息转为JSON对象
                    JSONObject jsonObject = JSON.parseObject(text);
                    //获得事件分类编码
                    String messageType = (String)jsonObject.get("messageType");
//                    System.out.println("===================="+messageType);
//                    System.out.println(jsonObject.get("content"));

                    String content =  jsonObject.get("content").toString();

                    //刷卡事件
                    if(messageType.equals("1-1")){
                        //获得控制器编码和读卡器编码
                        String deviceOuterID = jsonObject.get("deviceOuterID").toString();
                        int indexOf = deviceOuterID.indexOf("-");
                        String controllerId = deviceOuterID.substring(0, indexOf);
                        String readerId = deviceOuterID.substring(indexOf+1, deviceOuterID.length());
//                        System.out.println(controllerId+"                    "+readerId);
                        SwipeCardEvent swipeCardEvent = JSONArray.parseObject(content, SwipeCardEvent.class);
                        swipeCardEvent.setControllerId(Integer.parseInt(controllerId));
                        swipeCardEvent.setReaderId(Integer.parseInt(readerId));
//                        System.out.println(swipeCardEvent.toString());
                        //保存刷卡事件
                        swipeCardEventService.saveswipeCardEvent(swipeCardEvent);

                       //根据员工编号修改数据
                        personService.updateByPerSn(swipeCardEvent.getPersonCode(),swipeCardEvent.getTime());

                    }else if(messageType.equals("2-1")){//持卡客人变更事件
                        //设备ID
                        String deviceID = jsonObject.get("deviceOuterID").toString();

                        // TODO: 2020/3/6  //与钱小豪对接插卡人变更事件，持卡人变更意味修改持卡人信息此操作需调用员工管理服务，即基础服务

                    }

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
