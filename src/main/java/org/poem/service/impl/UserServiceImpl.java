package org.poem.service.impl;

import org.poem.dao.UserDao;
import org.poem.jooq.tables.records.TUserRecord;
import org.poem.service.IDWork;
import org.poem.service.UserService;
import org.poem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author poem
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IDWork<Long> idWork;


    @Override
    public void insertInto() {

        for(int i = 0 ; i < 100 ; i++){
            Object s = new TUserRecord();
            System.err.println("sefvs");
        }

    }


    @Override
    public List<UserVO> getUser() {
        TUserRecord[] tUserRecords = this.userDao.select();
       return Stream.of(tUserRecords).map(t ->{
           UserVO userVO = new UserVO();
           userVO.setId(t.getId());
           userVO.setName(t.getName());
           return userVO;
        }).collect(Collectors.toList());
    }
}
