package com.wbst.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wbst.domain.Person;
import com.wbst.domain.UserDto;
import com.wbst.query.PersonQuery;
import com.wbst.service.PersonService;
import com.wbst.util.AjaxResult;
import com.wbst.util.PageList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/person")
public class PersonController {

    @Reference
    private PersonService personService;

    //查询所有不分页
    @RequestMapping(value = "/getAll",method = RequestMethod.POST)
    public AjaxResult getAllPerson(){
        System.out.println("进来了！！！！！！！！！");
        try {
            List<Person> persons=personService.getAllPerson();
            return new AjaxResult(0,"操作成功",persons);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }

    //添加一个员工
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxResult addPerson(@RequestBody Person person){

        try {
            personService.addPerson(person);
            return new AjaxResult(0,"操作成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }

    //删除一个员工
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public AjaxResult delPerson(@RequestParam Integer id){
        try {
            personService.delPerson(id);
            return new AjaxResult(0,"操作成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }

    //修改一个员工
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public AjaxResult updatePerson(@RequestBody Person person){

        try {
            personService.updatePerson(person);
            return new AjaxResult(0,"操作成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }

    //查询所有员工（分页）
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public AjaxResult findAll(@RequestBody PersonQuery personQuery){

        try {
            PageList<Person> pageList=personService.findAll(personQuery);
            return new AjaxResult(0,"操作成功",pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }

    //查询所有员工（分页,员工姓名编号授权码模糊查询，部门精确）
    @RequestMapping(value = "/queryPersonLikeAndDept",method = RequestMethod.POST)
    public AjaxResult queryPersonLikeAndDept(@RequestBody PersonQuery personQuery){

        try {
            PageList<Person> pageList=personService.queryPersonLikeAndDept(personQuery);
            return new AjaxResult(0,"操作成功",pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }

    //通过编号查询员工
    @RequestMapping(value = "/queryPersonBySn",method = RequestMethod.GET)
    public AjaxResult queryPersonBySn(@RequestParam String personSn){

        try {
            UserDto userDto=personService.queryPersonBySn(personSn);
            return new AjaxResult(0,"操作成功",userDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(1,"操作失败"+e.getMessage(),null);
        }
    }
}
