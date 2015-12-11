package com.tianyu.jty.collector.service;

import com.tianyu.jty.collector.entity.LocationForConvert;
import com.tianyu.jty.collector.entity.nuomi.GroupOnInfo;
import com.tianyu.jty.collector.service.convert.ListToLocalConvert;
import com.tianyu.jty.collector.service.convert.ListToStringConvert;
import com.tianyu.jty.collector.service.lbs.GroupOnNear;
import com.tianyu.jty.permission.entity.AddressResult;
import com.tianyu.jty.permission.entity.Location;
import com.tianyu.jty.permission.utils.BaiduMapUtils;
import com.tianyu.jty.wechat.entity.LocalModel;
import com.tianyu.jty.wechat.msg.req.WechatReqMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by xtao on 2015/11/27.
 */
public class MessageService {

    @Autowired
    private ListToStringConvert listToStringConvert;

    @Autowired
    private ListToLocalConvert listToLocalConvert;

    @Autowired
    private ExtractorService extractorService;

    @Autowired
    private GroupOnNear groupOnNear;

    public String getMessageByReqMsg(WechatReqMsg msg, LocationForConvert location){
        String result = "";
        try {
            if(msg.getTextReqMsg()!=null && msg.getLocationReqMsg() != null){

            }else if(msg.getTextReqMsg() != null){
                List list = extractorService.doExtract(msg.getType(), msg.getParams());

                result = convertListToString(list, location);
            }else if(msg.getLocationReqMsg() != null) {
                List<GroupOnInfo> infos = groupOnNear.getNear(location);
                result = convertNearsInfoToString(infos);
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String convertListToString(List<Map<String,Object>> list, LocationForConvert location){
        return listToStringConvert.convert(list, location);
    }

    public String convertNearsInfoToString(List<GroupOnInfo> groupOnInfos) {
        StringBuilder sb = new StringBuilder("附近您可能感兴趣的地方：\n");
        for(GroupOnInfo info: groupOnInfos){
            sb.append(info.getGroupName()).append(":").append(info.getPrice()/100).append("/").append(info.getOriginPrice()/100)
                    .append(";").append(info.getDiscription()).append("\n");
        }
        return sb.toString();
    }

    public List<LocalModel> convertListToLocals(List<Map<String,Object>> list, LocationForConvert location, Integer num) {
       return listToLocalConvert.convert(list,location,num);
    }

    public String getAddressByLocation(LocationForConvert locationForConvert) {
        AddressResult result = BaiduMapUtils.locationToAddress(new Location(locationForConvert.getLng(), locationForConvert.getLat()));
        String address = "";
        if(result!=null) {
            try {
                if(!StringUtils.isEmpty(result.getAddressComponent().getStreet())) return result.getAddressComponent().getStreet();
                if(!StringUtils.isEmpty(result.getBusiness())) {
                    String[] businesses = result.getBusiness().split("，");
                    return businesses[0];
                }
                if(!StringUtils.isEmpty(result.getFormatted_address())) return result.getFormatted_address();
                address = result.getAddressComponent().getDistirct(); return address;
            }catch (Exception e){
                e.printStackTrace();
            }
            address = result.getAddressComponent().getCity();
        }
        return address;
    }
}
