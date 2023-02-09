package com.example.demo1.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // TODO: 2023/1/20 正則表達式匹配信箱格式是否正確
        return true;
    }
}
