package com.joe.oauth.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joe.oauth.entity.User;
import com.joe.oauth.entity.vo.LayerJson;
import com.joe.oauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Joe
 * @since 2019-11-01
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService usersService;

    @RequestMapping(value = "/add-edit/{id}",method = RequestMethod.GET)
    public String addOrEdit(@PathVariable Integer id, Model model){
        if(id!=-1){
            User user = usersService.selectById(id);
            model.addAttribute("user",user);
        }
        return "user/add-edit";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String list(){
        return "user/list";
    }

    /**
     * 使用Layui table完成分页功能
     * @param pageIndex Layui table 默认提交当前页码的key 是page
     * @param pageSize Layui table 默认提交每页显示条数的key 是limit
     * @param search  查询条件
     * @return  返回自己组装符合Layui table格式的Json数据
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String list(@RequestParam(value = "page",defaultValue = "1") Integer pageIndex, @RequestParam(value = "limit" , defaultValue = "10") Integer pageSize, String search) throws JsonProcessingException {
        //查询页码和每页显示的条数
        Page page = new Page(pageIndex,pageSize);
        //查询条件
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.orderBy("id").or().like("id",search).or().like("username",search);

        //根据查询条件查询符合的数据LayerJson
        Page<User> mapPage = usersService.selectPage(page, ew);

        //组装JSON对象数据
        LayerJson layerJson = LayerJson.getInstance(mapPage.getRecords(),mapPage.getTotal());
        //转为JSON字符串
        return new ObjectMapper().writeValueAsString(layerJson);
    }

    @RequestMapping(value = "/add-edit",method = RequestMethod.POST)
    public ResponseEntity addOrEdit(User user){
        boolean flg = usersService.insertOrUpdate(user);
        if(flg){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    public ResponseEntity del(@PathVariable Integer id){
        boolean flg = usersService.deleteById(id);
        if(flg){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}

