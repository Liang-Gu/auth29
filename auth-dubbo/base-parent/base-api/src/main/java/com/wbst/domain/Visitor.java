package com.wbst.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 访客实体类
 */
public class Visitor implements Serializable {

    //主键
    private Long id;

    //头像存储地址
    private String headImage;

    //名字
    private String name;

    //性别
    private Integer sex;

    //证件类型  0表示身份证  1表示驾驶证
    private Integer certificatesType;

    //证件照片存储地址
    private String certificatesImage;

    //证件号
    private String certificatesNum;

    //单位
    private String company;

    //电话
    private String phone;

    //来访次数
    private Integer totalTimes;

    //来访信息明细
    private List<VisitorDetail> visitorDetails;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(Integer certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesImage() {
        return certificatesImage;
    }

    public void setCertificatesImage(String certificatesImage) {
        this.certificatesImage = certificatesImage;
    }

    public String getCertificatesNum() {
        return certificatesNum;
    }

    public void setCertificatesNum(String certificatesNum) {
        this.certificatesNum = certificatesNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    public List<VisitorDetail> getVisitorDetails() {
        return visitorDetails;
    }

    public void setVisitorDetails(List<VisitorDetail> visitorDetails) {
        this.visitorDetails = visitorDetails;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", headImage='" + headImage + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", certificatesType=" + certificatesType +
                ", certificatesImage='" + certificatesImage + '\'' +
                ", certificatesNum='" + certificatesNum + '\'' +
                ", company='" + company + '\'' +
                ", phone='" + phone + '\'' +
                ", totalTimes=" + totalTimes +

                '}';
    }
}
