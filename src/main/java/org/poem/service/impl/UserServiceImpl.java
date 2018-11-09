package org.poem.service.impl;

import org.poem.dao.UserDao;
import org.poem.jooq.tables.records.TUserRecord;
import org.poem.service.IDWork;
import org.poem.service.UserService;
import org.poem.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author poem
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private IDWork<Long> idWork;

    /**
     * 请
     */
    @Override
    public void insertInto()  {
        List<TUserRecord> tUserRecordList = new ArrayList<>();
        for(int i = 0 ; i < 11 ; i++){
            TUserRecord tUserRecord = new TUserRecord();
            tUserRecord.setId(idWork.getId());
            tUserRecord.setName( Thread.currentThread().getName() );
            tUserRecord.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
            tUserRecordList.add(tUserRecord);
            try {
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info(tUserRecordList.size() + "");
        userDao.save(tUserRecordList);
    }

    /**
     * 随便
     * @return
     */
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
