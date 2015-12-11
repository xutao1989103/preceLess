package com.tianyu.jty.permission.service;

import com.google.common.collect.Lists;
import com.tianyu.jty.permission.entity.*;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xtao on 2015/11/23.
 */
@Service("lbsFilterService")
public class LbsFilterService extends AbstractFilterService {

    private LbsCheck lbsCheck;

    @Override
    public CheckResult checkParams() {
        if(location.isEmpty() ){
            return new CheckResult(false, "Location Empty");
        }
        if(CollectionUtils.isEmpty(urlFilters)){
            return new CheckResult(false, "Filter Empty");
        }
        return new CheckResult(true, "OK");
    }

    @Override
    public CheckResult checkRequest() {
        List<CheckResult> checkResults = Lists.newArrayList();
        for(UrlFilter urlFilter: urlFilters) {
            lbsCheck = CheckerFactory.getChecker(LbsType.valueOf(urlFilter.getLbstype()));
            checkResults.add(lbsCheck.check(location, urlFilter));
        }
        return reduceCheckResult(checkResults);
    }

    private CheckResult reduceCheckResult(List<CheckResult> checkResults) {
        CheckResult checkResult = new CheckResult(false, "");
        StringBuilder sb = new StringBuilder();
        for (CheckResult cr: checkResults){
             if(cr.isPass()){
                 checkResult.setPass(true);
                 checkResult.setMsg("ok");
                 return checkResult;
             }
            sb.append(cr.getMsg() + ", ");
        }
        checkResult.setMsg(sb.toString());
        return checkResult;
    }
}
