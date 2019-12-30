package com.joe.batch;


import com.joe.entity.UserEntity;
import com.joe.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

class UserSendThread implements Runnable {
    private List<UserEntity> listUser;

    public UserSendThread(List<UserEntity> listUser) {
        this.listUser = listUser;
    }

    public void run() {
        for (UserEntity userEntity : listUser) {
            System.out.println(Thread.currentThread().getName() + " " + userEntity.toString());
        }

    }
}

public class BatchSms {

    public static void main(String[] args) {

        // 1. 初始化数据
        List<UserEntity> list = initUser();

        // 2.定义每个线程分批发送大小
        int userCount = 2;

        // 3.计算每个线程需要分批跑的数据
        List<List<UserEntity>> splitList = ListUtils.splitList(initUser(), userCount);

        for (int i = 0; i < splitList.size(); i++) {
            List<UserEntity> list1 = splitList.get(i);
            UserSendThread userSendThread = new UserSendThread(list1);
            // 4.分批发送
            Thread thread = new Thread(userSendThread, "线程" + i);
            thread.start();
            System.out.println();
        }
    }

    private static List<UserEntity> initUser() {

        List<UserEntity> list = new ArrayList<UserEntity>();

        for (int i = 0; i < 10; i++) {
            list.add(new UserEntity("userid:" + i, "username" + i));
        }

        return list;
    }
}