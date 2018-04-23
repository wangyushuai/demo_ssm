package com.wys.service;

import com.wys.pojo.Test;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/4/19.
 */

public interface TestService {
    List<Test> getAllList();
}

