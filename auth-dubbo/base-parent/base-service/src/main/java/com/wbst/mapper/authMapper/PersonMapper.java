package com.wbst.mapper.authMapper;

import com.wbst.domain.Person;
import com.wbst.domain.UserDto;
import com.wbst.dto.WarningDataDto;
import com.wbst.query.PersonQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PersonMapper {

    List<Person> getAllPerson();

    void addPerson(Person person);

    void delPerson(Integer id);

    void updatePerson(Person person);

    List<Person> findAll(PersonQuery personQuery);

    Person queryByName(String name);

    List<Person> queryPersonLikeAndDept(PersonQuery personQuery);

    UserDto queryPersonBySn(String personSn);

    void updatePersonDf(Integer id);

    //根据员工编号修改数据
    void updateByPerSn(@Param("personCode") String personCode,@Param("time") Date time);

    //根据员工授权编号（芯片卡号）修改数据
    void updateByPerAuthSn(@Param("cardNum") String cardNum, @Param("format") String format);

    //查询当前周期不符合条件的数据
    List<WarningDataDto> findWarningData(@Param("date") Date date, @Param("abnormalDuration") Integer abnormalDuration);


    Person findByCardNum(String cardNum);
}
