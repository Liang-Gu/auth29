package com.wbst;

import com.wbst.producer.Topic_Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TopicTest {
    @Autowired
    private Topic_Producer producer;

    @Test
    public void testtt(){
        producer.sendMessage("sss");
    }
}
