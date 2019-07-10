package com.art.service.famous;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.famous.CommentInformation;
import com.art.dao.famous.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class CommentInfoSVImpl implements ICommentInfoSV{

    @Autowired
    private CommentDAO dao;

    /**
     * 新增：评论信息
     * @param map
     */
    @Override
    public void addCommentInformation(Map map) {
        dao.addCommentInformation(map);
    }

    /**
     * 查询：评论信息
     * @param map
     * @return
     */
    @Override
    public List<CommentInformation> getCommentInformations(Map map) {
        return dao.getCommentInformations(map);
    }

    /**
     * 删除：评论信息
     * @param map
     */

    @Override
    public void deleteCommentInfoById(Map map) {
        dao.deleteCommentInfoById(map);
    }
}
