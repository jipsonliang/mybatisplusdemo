package com.jipson.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jipson.mp.mapper.UserMapper;
import com.jipson.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;//Autowired报错，可以在mapper类中添加@Component

    //插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setMail("123123@ww.com");
        user.setAge(31);
        user.setUserName("caocao");
        user.setName("曹操1");
        user.setPassword("123456");

        int result = userMapper.insert(user);
        System.out.println("result =>"+ result);//数据库受影响的行数

        //允许后id将自动设置为一个long值
        //Parameters: 1236585222489845761(Long), caocao(String), 123456(String), 曹操(String), 30(Integer), 123@ww.com(String)
        //如果需要将id设置为一个小的值，可以更改id的自动生成策略,
        // 在pojo User类的id上面添加@TableId(type = IdType.AUTO)
        //id会自动回填到user对象
        System.out.println("id =>"+ user.getId());

    }

    //查询
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);

        System.out.println("user =>" + user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2L, 3L));
        for (User user:users) {
            System.out.println("user =>" + user);
        }
    }

    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi");//查询条件
        User user = userMapper.selectOne(wrapper);//如果查询到多个数据会报错

        System.out.println("user =>" + user);
    }

    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20);//查询条件
        Integer integer = userMapper.selectCount(wrapper);//如果查询到多个数据会报错

        System.out.println("result =>" + integer);
    }

    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.gt("age",20);//查询条件1
        wrapper.like("email", "ww.com");//查询条件2
        List<User> users = userMapper.selectList(wrapper);//如果查询到多个数据会报错
        for(User user: users){
            System.out.println("result =>" + user);
        }
    }

    //分页查询
    //1.编写配置类MybatisPlusConfig
    //2.编写测试方法
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(2,2);

        //查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", ".com");

        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
//        IPage<User> userIPage = userMapper.selectPage(page, null);//可以没有条件

        System.out.println("数据总条数： "+ userIPage.getTotal());
        System.out.println("数据总页数： "+ userIPage.getPages());
        System.out.println("当前页： "+ userIPage.getCurrent());

        //拿到分页数据
        List<User> records = userIPage.getRecords();
        for (User user:
                records ) {
            System.out.println("result =>" + user);
        }

    }


    //更新
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1L);
        user.setAge(19);
        user.setPassword("666666");
        int i = userMapper.updateById(user);
        System.out.println("result =>" + i);

    }

    //条件更新1 QueryWrapper
    @Test
    public void testUpdate(){
        User user = new User();
        user.setAge(20);
        user.setPassword("888888");

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","zhangsan");//第一个参数是数据库字段名

        int update = userMapper.update(user, wrapper);

        System.out.println("result =>"+ update);
    }

    //条件更新2 UpdateWrapper，较更新1更加方便
    @Test
    public void testUpdate2(){

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",21).set("password","999999")//更新的字段
                .eq("user_name", "zhangsan"); //更新的条件

        int update = userMapper.update(null, wrapper);

        System.out.println("result =>"+ update);
    }

    //删除
    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(8L);
        System.out.println("result =>"+ i);
    }

    @Test
    public void testDeleteByMapper(){
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "caocao1");
        map.put("password","123456");
        //测试通过map构造的参数删除，参数之间用and连接
        //如果没有查找到同时满足参数条件的记录，则Update：0
        int i = userMapper.deleteByMap(map);
        System.out.println("result =>"+ i);
    }

    //根据包装条件删除
    @Test
    public void testDelete(){
        //用法一
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name","caocao")
//                .eq("password", "123456");


        //用法二,推荐使用
        User user = new User();
        user.setUserName("caocao");
        user.setPassword("123456");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int delete = userMapper.delete(wrapper);
        System.out.println("result =>"+ delete);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(10L, 11L));
        System.out.println("result =>"+ i);
    }

    //测试自定义Mapper方法
    @Test
    public void testFindById(){
        User user = userMapper.findById(2L);
        System.out.println(user);
    }

}
