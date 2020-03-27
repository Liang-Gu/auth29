package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.wbst.domain.Corn;
import com.wbst.dto.WarningDataDto;
import com.wbst.mapper.CornMapper;
import com.wbst.service.CornService;
import com.wbst.service.PersonService;
import com.wbst.service.WarningDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(timeout = 30000)
@org.springframework.stereotype.Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CornServiceImpl implements CornService {

    @Resource
    private CornMapper cornMapper;


    @Reference
    private PersonService personService;

    @Autowired
    private WarningDataService warningDataService;


    @Override
    @Transactional
    public void update(Integer abnormalDuration) {
//        Corn corn = cornMapper.findCorn();0 0 0 1/20 * ?
        String cron="0 0 0 1/"+abnormalDuration+" * ?";
        cornMapper.update(cron,abnormalDuration);

        //获取周期内符合条件的数据
        List<WarningDataDto> personList=personService.findWarningData(new Date(),abnormalDuration);
        //保存异常数据到异常表中
        warningDataService.save(personList);
    }

    @Override
    public Corn find() {
        return cornMapper.findCorn();
    }
}
