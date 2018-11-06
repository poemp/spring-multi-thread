package org.poem.service;

import org.poem.vo.UserVO;

import java.util.List;

/**
 * @author poem
 */
public interface UserService{

    /**
     * 插入
     */
    public void insertInto();


    /**
     * 查询
     * @return
     */
    public List<UserVO> getUser();
}
