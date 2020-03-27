package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.wbst.domain.MessageTitle;
import com.wbst.domain.SwipeCardEvent;
import com.wbst.mapper.MessageTitleMapper;
import com.wbst.mapper.SwipeCardEventMapper;
import com.wbst.service.SwipeCardEventService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(timeout =3000 )
@org.springframework.stereotype.Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class SwipeCardEventServiceImpl implements SwipeCardEventService {

    @Resource
    private SwipeCardEventMapper swipeCardEventMapper;



    @Override
    @Transactional
    public void saveswipeCardEvent(SwipeCardEvent swipeCardEvent) {
        swipeCardEventMapper.saveswipeCardEvent(swipeCardEvent);
    }
}
