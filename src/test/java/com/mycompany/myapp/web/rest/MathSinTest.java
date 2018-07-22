package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create by wys on 2018/7/22
 */
public class MathSinTest {

    public static void main(String args[]) {


        double n = 3;
        double angle = 30;
        double radians_angle = Math.toRadians(angle);
        System.out.println("test sin angle: "+Math.sin(radians_angle));


//
//        double a = 60;
//        double Radians_a = Math.toRadians(a);  //角度转换为弧度
//        System.out.println(Radians_a);
//        double sinValue = Math.sin(Radians_a);  //计算正弦函数值，参数为弧度值
//        System.out.println(sinValue);
//
//

    }

}
