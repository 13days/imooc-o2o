package com.imooc.o2o.service;

import com.imooc.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 21:37  2018/12/6
 * @Annotation:
 */
public interface HeadLineService {

    /**
     * 根据传入的调教返回指定头条
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
