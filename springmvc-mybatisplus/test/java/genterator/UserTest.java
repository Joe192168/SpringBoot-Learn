package genterator;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
<<<<<<< HEAD
import com.joe.entity.TUsers;
import com.joe.service.ITUsersService;
=======
import com.joe.entity.User;
import com.joe.service.IUserService;
>>>>>>> origin/master
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CDHong on 2018/4/6.
 */
@ContextConfiguration("classpath:spring/spring-mybatis-plus.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Autowired
<<<<<<< HEAD
    private ITUsersService iUserService;
=======
    private IUserService iUserService;
>>>>>>> origin/master

    /**
     * 自增生成器
     */
    private final static AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 在做保存的时候，可以自己手动添加主键，也可以使用序列来完成,
     * 相应的配置需要在spring-mybatis-plus.xml配置文件中找到以下两句配置
     * <property name="idType" value="2"/>
     *  <property name="keyGenerator" ref="keyGenerator"/>
     * 如果是手动添加主键，则idType的value值改为1，keyGenerator不需要配置
     * 如果是使用序列，则使用默认配置，除此之外需要在对应的实体类上添加如下注解：
     * @KeySequence(value = "seq_dept",clazz = Integer.class)
     * value:是要使用的序列名称  clazz是接受的值类型，默认是Long类型，这个结合自己实体字段类型来更改*/

    @Test
    public void testSave(){
        //id 从1开始生成
        Integer id = atomicInteger.incrementAndGet();

<<<<<<< HEAD
        TUsers user = new TUsers();
        user.setId(id);
=======
        User user = new User();
        user.setId(id+"");
>>>>>>> origin/master
        user.setUsername("小明");
        user.setPassword("123");
        boolean flg = iUserService.insert(user);
        System.out.println(flg);
    }

    @Test
    public void testUpdate(){
        //先查询用户，再根据需求修改
<<<<<<< HEAD
        TUsers user = iUserService.selectById(2);
=======
        User user = iUserService.selectById(2);
>>>>>>> origin/master
        //设置要修改的信息
        user.setUsername("小张");
        boolean flg = iUserService.updateById(user);
        System.out.println(flg);
    }

    @Test
    public void testFindAll(){
<<<<<<< HEAD
        List<TUsers> userList = iUserService.selectList(null);
        for(TUsers user:userList){
            System.out.println(user);
=======
        List<User> deptList = iUserService.selectList(null);
        for(User dept:deptList){
            System.out.println(dept);
>>>>>>> origin/master
        }
    }

    @Test
    public void testDel(){
        boolean flg = iUserService.deleteById(1);
        System.out.println(flg);
    }
    
     @Test
    public void testPageInfo(){
        Page page = new Page(1,2);
        //查询条件实体
<<<<<<< HEAD
        EntityWrapper<TUsers> ew = new EntityWrapper<>();
        ew.like("username","小").orderBy("id");
        Page<TUsers> deptPage = iUserService.selectPage(page, ew);
=======
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.like("username","小").orderBy("id");
        Page<User> deptPage = iUserService.selectPage(page, ew);
>>>>>>> origin/master
        System.out.println("总页数："+deptPage.getTotal());
        System.out.println("当前页码："+deptPage.getCurrent());
        System.out.println("每页显示条数："+deptPage.getSize());
        System.out.println("当前页数据："+deptPage.getRecords());
    }

}