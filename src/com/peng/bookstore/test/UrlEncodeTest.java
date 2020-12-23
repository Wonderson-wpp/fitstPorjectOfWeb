package com.peng.bookstore.test;

import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncodeTest {

    @Test
    public void test() {
        String s = URLEncoder.encode("来自网上书城的激活邮件", StandardCharsets.UTF_8);
        String s2 = URLEncoder.encode("点击激活", StandardCharsets.UTF_8);

        System.out.println(s);
        System.out.println(s2);
    }
}
