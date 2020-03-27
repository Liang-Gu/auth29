package com.wbst.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wbst.domain.Person;
import com.wbst.domain.UserDto;
import com.wbst.dto.WarningDataDto;
import com.wbst.mapper.authMapper.PersonMapper;
import com.wbst.query.PersonQuery;
import com.wbst.service.PersonService;
import com.wbst.util.PageList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(timeout = 3000)
@org.springframework.stereotype.Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS,transactionManager = "authTransactionManager")
public class PersonServiceImpl implements PersonService {


    @Resource
    private PersonMapper personMapper;


    @Override
    public List<Person> getAllPerson() {
        return personMapper.getAllPerson();
    }

    @Override
    @Transactional(transactionManager = "authTransactionManager")
    public void addPerson(Person person) {
        person.setDelFlag(0);
        personMapper.addPerson(person);

    }

    @Override
    @Transactional(transactionManager = "authTransactionManager")
    public void delPerson(Integer id) {
        personMapper.updatePersonDf(id);
    }

    @Override
    @Transactional(transactionManager = "authTransactionManager")
    public void updatePerson(Person person) {
        personMapper.updatePerson(person);
    }

    @Override
    public PageList<Person> findAll(PersonQuery personQuery) {
        Page<Person> page= PageHelper.startPage(personQuery.getCurrentPage(),personQuery.getPageSize());
        List<Person> persons=personMapper.findAll(personQuery);
        return new PageList<Person>(page.getTotal(),page.getPages(),persons);
    }

    @Override
    public Person queryByName(String name) {
        return personMapper.queryByName(name);
    }

    @Override
    public PageList<Person> queryPersonLikeAndDept(PersonQuery personQuery) {
        Page<Person> page= PageHelper.startPage(personQuery.getCurrentPage(),personQuery.getPageSize());
        List<Person> persons=personMapper.queryPersonLikeAndDept(personQuery);
        return new PageList<Person>(page.getTotal(),page.getPages(),persons);
    }

    @Override
    public UserDto queryPersonBySn(String personSn) {
        return personMapper.queryPersonBySn(personSn);
    }

    //根据员工编号修改数据
    @Override
    @Transactional(transactionManager = "authTransactionManager")
    public void updateByPerSn(String personCode, Date time) {
        personMapper.updateByPerSn(personCode,time);
    }



    //根据员工授权编号（芯片卡号）修改数据
    @Override
    @Transactional(transactionManager = "authTransactionManager")
    public void updateByPerAuthSn(String cardNum, String format) {
        personMapper.updateByPerAuthSn(cardNum,format);
    }

    //获取周期内符合条件的数据
    @Override
    public List<WarningDataDto> findWarningData(Date date, Integer abnormalDuration) {
        return personMapper.findWarningData(date,abnormalDuration);
    }

    @Override
    public Person findByCardNum(String cardNum) {
     return    personMapper.findByCardNum(cardNum);
    }




}
