package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.BicycleAdmin;
import com.mycompany.myapp.repository.BicycleAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by wys on 2018/7/23
 */

@Service
public class BicycleAdminService {

    /*
       state登录状态提示
       login_success
       username_password_error
       no_user
     */

    @Autowired
    BicycleAdminRepository bicycleAdminRepository;

    String state = "";

    public String login(String username,String password) {

        BicycleAdmin bicycleAdmin = bicycleAdminRepository
            .findBicycleAdminByUsernameAndPassword(username,password);

//            .findBicycleAdminByUsername(username);
//        .findBicycleAdminById(1);
//        System.out.println("33 "+bicycleAdmin);

        if(bicycleAdmin == null) {
            state = "no_user";
        }else if(bicycleAdmin.getUsername().equals(username) && bicycleAdmin.getPassword().equals(password)) {
            state = "login_success";
        }else {
            state = "username_password_err";
        }

        return state;
    }

}
