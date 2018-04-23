package com.wys.service;

import com.wys.mapper.TestMapper;
import com.wys.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/4/19.
 */

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper mapper;

    @Override
    public List<Test> getAllList() {
        return mapper.allList();
    }
}
