package com.tianyu.jty.collector.service.lbs;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.nuomi.Deal;
import com.tianyu.jty.collector.entity.nuomi.GroupOnInfo;
import com.tianyu.jty.collector.entity.nuomi.Shop;
import com.tianyu.jty.collector.entity.nuomi.ShopsResult;
import com.tianyu.jty.collector.utils.BaiduApiUtils;
import com.tianyu.jty.permission.entity.AddressResult;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.entity.Poi;
import com.tianyu.jty.permission.utils.BaiduMapUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xtao on 2015/11/30.
 */
@Service
public class GroupOnNear implements Near<List<GroupOnInfo>> {

    @Override
    public List<GroupOnInfo> getNear(LocationForConvert location) {
        List<GroupOnInfo> groupOnInfos = Lists.newArrayList();
        String jsonStr = BaiduApiUtils.searchShops(null,location);
        Gson gson = new Gson();
        ShopsResult shopsResult = gson.fromJson(jsonStr, ShopsResult.class);
        if(shopsResult!=null && shopsResult.getData()!=null && shopsResult.getData().getShops()!=null){
            for(Shop shop: shopsResult.getData().getShops()){
                GroupOnInfo info = new GroupOnInfo();
                info.setGroupName(shop.getShop_name());
                if(shop.getDeals()!=null && shop.getDeals().size()>0){
                    Deal deal = shop.getDeals().get(0);
                    info.setDiscription(deal.getDescription());
                    info.setOriginPrice(deal.getMarket_price());
                    info.setPrice(deal.getCurrent_price());
                }
                groupOnInfos.add(info);
            }
        }
        return groupOnInfos;
    }
}
