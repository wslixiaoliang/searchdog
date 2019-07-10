package com.art.dao.famous;

import com.art.beans.famous.CommentInformation;

import java.util.List;
import java.util.Map;

/**
 * 评论信息DAO
 * @author wslixiaoliang
 */
public interface CommentDAO
{
    /**
     * 新增：评论信息
     * @param map
     */
    void addCommentInformation(Map map);

    /**
     * 查询：评论信息
     * @param map
     * @return
     */
    List<CommentInformation> getCommentInformations(Map map);

    /**
     * 删除：评论信息
     * @param map
     */
    void deleteCommentInfoById(Map map);


}
