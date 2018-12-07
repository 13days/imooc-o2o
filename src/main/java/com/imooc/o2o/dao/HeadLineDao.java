package com.imooc.o2o.dao;

import com.imooc.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 11:34  2018/12/6
 * @Annotation:
 */
public interface HeadLineDao {

    /**
     * 根据传入的查询条件(头条名查询头条)
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);

}
