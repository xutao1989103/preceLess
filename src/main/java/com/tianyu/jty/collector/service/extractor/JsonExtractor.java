package com.tianyu.jty.collector.service.extractor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.tianyu.jty.collector.entity.Site;
import com.tianyu.jty.collector.entity.nuomi.Deal;
import com.tianyu.jty.collector.entity.nuomi.DealResult;
import com.tianyu.jty.collector.service.outputs.AbstractOutput;
import com.tianyu.jty.collector.utils.BaiduApiUtils;
import com.tianyu.jty.collector.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/8.
 */
@Service
public class JsonExtractor extends Extractor {

    private List<String> keys;
    private Gson gson ;

    public JsonExtractor() {
    }

    public JsonExtractor(Site site, AbstractOutput output) {
        super(site);
        this.outputers.add(output);
    }

    @Override
    public void init(Site site) {
        this.site = site;
        this.keys = site.getJsons();
        this.gson = new Gson();
    }

    @Override
    public void extract() throws Exception {
        site.getResult().put("website",site.getWebsite());
        String jsonstr = BaiduApiUtils.searchDeals(site.getKeyword(),site.getLocation());
        DealResult deal = gson.fromJson(jsonstr, DealResult.class);
        doExtract(deal);
    }

    private void doExtract(DealResult dealResult) {
        if(dealResult == null || dealResult.getData()==null || dealResult.getData().getTotal() == 0) return;
        List details = Lists.newArrayList();
        List<Deal> deals = dealResult.getData().getDeals();
        for(Deal deal: deals){
            Map<String ,String > item = Maps.newHashMap();
            item.put("shopName",deal.getTitle());
            item.put("price", deal.getCurrent_price()/100 + "/" + deal.getMarket_price()/100);
            item.put("pic", deal.getImage());
            item.put("url", deal.getDeal_url());
            details.add(item);
        }
        site.getResult().put("details", details);
    }

}
