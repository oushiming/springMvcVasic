package com.imooc.object;

import com.imooc.common.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geely on 2015/11/22.
 */
public class UserListForm {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "UserListForm{" +
                "users=" + users +
                '}';
    }

    public static void main(String[] args) {
       /* List<User> users = new ArrayList<User>();
        for(int i = 0; i < 2; i++){
            User user = new User();
            user.setName("tom" + i);
            user.setAge(i);
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setAddress("guangzhou" + i);
            contactInfo.setPhone("1380000000" + i);
            user.setContactInfo(contactInfo);
            users.add(user);
        }
        UserListForm userListForm = new UserListForm();
        userListForm.setUsers(users);
        System.out.println(JsonUtil.objToString(userListForm));*/
    }
}
