package com.art.web.controller;

import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousPortrait;
import com.art.beans.famous.Result;
import com.art.util.SearchConstans;
import com.art.web.component.famous.SearchFamousComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 世界名人Controller
 * @author lixiaoliang
 */
@RestController
@RequestMapping(value = "/worldFamous")
public class FamousController {
    @Autowired
    private SearchFamousComponent searchFamousComponent;
    private static final Logger logger = Logger.getLogger(FamousController.class);

    /**
     * 世界名人：条件查询
     * @param page
     * @param limit
     */
    @RequestMapping(value = "/getWorldFamous", method = RequestMethod.POST)
    public Result getWorldFamous(int page, int limit) {
        Result result = new Result();
        List<FamousPortrait> famousList;

        try {
            SearchResult searchResult = searchFamousComponent.searchFamousInfo(new HashMap<>(),page,limit);
            famousList = map2Bean(searchResult.getDocuments());
            result.setBeans(famousList);
            result.setCount(searchResult.getTotalCount());
            result.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            result.setReturnMessage("查询成功");
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            result.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            result.setReturnMessage("查询失败");
        }
        return result;
    }

    /**
     * Map转bean
     *
     * @param documents
     * @return
     */
    private List<FamousPortrait> map2Bean(List<Map<String, Object>> documents) {

        List<FamousPortrait> famousList = new ArrayList<>();
        if (null == documents && documents.size() == 0) {
            return famousList;
        }
        for (Map<String, Object> document : documents) {
            String portraitId = String.valueOf(document.get("portraitId"));
            String portraitName = String.valueOf(document.get("portraitName"));
            String relativeLocation = String.valueOf(document.get("relativeLocation"));
            String famousId = String.valueOf(document.get("famousId"));
            String chineseName = String.valueOf(document.get("chineseName"));
            String englishName = String.valueOf(document.get("englishName"));
            String sex = String.valueOf(document.get("sex"));
            String career = String.valueOf(document.get("career"));
            String achievement = String.valueOf(document.get("achievement"));
            String honor = String.valueOf(document.get("honor"));
            String country = String.valueOf(document.get("country"));
            String birthYear = String.valueOf(document.get("birthYear"));
            FamousPortrait famousPortrait = new FamousPortrait(Long.valueOf(portraitId), portraitName, relativeLocation, Long.valueOf(famousId), chineseName, englishName, sex, career, achievement, honor, country, birthYear);
            famousList.add(famousPortrait);
        }
        return famousList;

    }

}