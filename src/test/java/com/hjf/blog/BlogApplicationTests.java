package com.hjf.blog;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Test
    public void contextLoads() {
        String str = "1,as,2";
        str = test(str);
        System.out.println(str);
    }

    private String test(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strings = s.split(",");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("as")) {
                strings[i] = String.valueOf(3);
            }
            if (i != strings.length-1) {
                sb.append(strings[i]+",");
            } else {
                sb.append(strings[i]);
            }
        }
        return sb.toString();
    }

}
