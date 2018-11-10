package org.poem.dao;

import org.jooq.DSLContext;
import org.poem.jooq.tables.TUser;
import org.poem.jooq.tables.records.TUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author poem
 */
@Repository
public class UserDao  {

    @Autowired
    private DSLContext dslContext;

    /**
     * 保存
     * @param tUserRecord
     */
    public void save(TUserRecord tUserRecord){
        dslContext.insertInto(TUser.T_USER).set(tUserRecord).execute();
    }


    /**
     * 保存
     * @param tUserRecords
     */
    public void save(List<TUserRecord> tUserRecords){
        dslContext.batchInsert(tUserRecords).execute();
    }


    /**
     * 查询全部
     * @return
     */
    public TUserRecord[] select(){
        return dslContext.selectFrom(TUser.T_USER).fetchArray();
    }

    /**
     * 删除全部
     */
    public void deleteAll() {
        dslContext.deleteFrom(TUser.T_USER).execute();
    }
}
