package org.poem.service;

import org.poem.jooq.tables.records.TUserRecord;
import org.poem.service.vo.UserVO;

import java.util.List;

public interface UserService {

    /**
     * 插入
     * @param tUserRecordList
     */
    public void insertInto();


    /**
     * 查询
     * @return
     */
    public List<UserVO> getUser();
}
