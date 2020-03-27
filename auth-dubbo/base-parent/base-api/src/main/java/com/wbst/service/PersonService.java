package com.wbst.service;

import com.wbst.domain.Person;
import com.wbst.domain.UserDto;
import com.wbst.dto.WarningDataDto;
import com.wbst.query.PersonQuery;
import com.wbst.util.PageList;

import java.util.Date;
import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();

    void addPerson(Person person);

    void delPerson(Integer id);

    void updatePerson(Person person);

    PageList<Person> findAll(PersonQuery personQuery);

    Person queryByName(String name);

    PageList<Person> queryPersonLikeAndDept(PersonQuery personQuery);

    UserDto queryPersonBySn(String personSn);

    //根据员工编号修改数据
    void updateByPerSn(String personCode, Date time);

    //根据员工授权编号（芯片卡号）修改数据
    void updateByPerAuthSn(String cardNum, String format);

    //查询出当前周期未刷卡数据
    List<WarningDataDto> findWarningData(Date date, Integer abnormalDuration);



    Person findByCardNum(String cardNum);
}
