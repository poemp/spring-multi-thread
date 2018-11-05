package org.poem.service.impl;

import org.poem.dao.UserDao;
import org.poem.jooq.tables.records.TUserRecord;
import org.poem.service.IDWork;
import org.poem.service.UserService;
import org.poem.service.vo.UserVO;
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
        List<TUserRecord> tUserRecordList = new ArrayList<>();
        for(int i = 0 ; i < 100 ; i++){
            TUserRecord tUserRecord = new TUserRecord();
            tUserRecord.setId(idWork.getId());
            tUserRecord.setName(UUID.randomUUID().toString() + "@我靠" );
            tUserRecordList.add(tUserRecord);
        }

        userDao.save(tUserRecordList);
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