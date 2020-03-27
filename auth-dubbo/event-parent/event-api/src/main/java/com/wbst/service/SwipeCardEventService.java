package com.wbst.service;

import com.wbst.domain.MessageTitle;
import com.wbst.domain.SwipeCardEvent;

public interface SwipeCardEventService {
    //保存刷卡事件数据
    void saveswipeCardEvent(SwipeCardEvent swipeCardEvent);
}
