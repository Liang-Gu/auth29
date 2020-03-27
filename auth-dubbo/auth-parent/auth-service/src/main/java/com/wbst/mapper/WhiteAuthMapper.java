package com.wbst.mapper;

import com.wbst.domain.AccessLevel;
import com.wbst.domain.Auth;
import com.wbst.query.AccessLevelQuery;
import com.wbst.util.AuthUtil;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WhiteAuthMapper {
    //根据员工芯片卡号和通行权限编号查询数据库中是否有数据
    Auth findByCardNumAndaccessLevelId(@Param("cardNum") String cardNum, @Param("accessLevelId") Integer accessLevelId);


    //修改通行权限
    void update(@Param("cardNum") String cardNum,
                @Param("accessLevelId") Integer accessLevelId,
                @Param("effectiveTime_start") Date effectiveTime_start,
                @Param("effectiveTime_end") Date effectiveTime_end,
                @Param("accessLevelStatus")Integer accessLevelStatus);

    //新增通行权限
    void insert(@Param("cardNum") String cardNum,
                @Param("accessLevelId") Integer accessLevelId,
                @Param("effectiveTime_start") Date effectiveTime_start,
                @Param("effectiveTime_end") Date effectiveTime_end, @Param("accessLevelStatus")Integer accessLevelStatus
    );


    //查询所有通行级别
    List<AccessLevel> findAllAccessLevel(AccessLevelQuery accessLevelQuery);


    //查询共有通行权限
    List<AccessLevel> findCommon(@Param("cardNums") List<String> cardNums, @Param("size") int size);



    //批量修改
    void batchUpdate(@Param("cardNums") List<String> cardNums,@Param("accessLevelId") Integer accessLevelId, @Param("effectiveTimeStart") Date effectiveTimeStart, @Param("effectiveTimeEnd") Date effectiveTimeEnd, @Param("i") int i);

    //批量删除
    void batchDelete(@Param("cardNum") String cardNum,@Param("authUtils") List<AuthUtil> authUtils);

    //修改为永久权限
    void updateForever(@Param("cardNum") String cardNum, @Param("accessLevelId") Integer accessLevelId);

}
