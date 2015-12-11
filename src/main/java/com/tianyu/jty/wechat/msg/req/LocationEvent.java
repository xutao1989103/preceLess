package com.tianyu.jty.wechat.msg.req;

import com.tianyu.jty.wechat.util.MessageBuilder;

/**
 * Created by xtao on 2015/11/24.
 */
public class LocationEvent extends BaseEvent {
    private double latitude;// 地理位置纬度
    private double longitude;// 地理位置经度
    private double precision;// 地理位置精度

    /**
     * 得到地理位置纬度
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 得到地理位置经度
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 得到地理位置精度
     */
    public double getPrecision() {
        return precision;
    }

    public LocationEvent(double latitude, double longitude, double precision) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addTag("Latitude", String.valueOf(latitude));
        mb.addTag("Longitude", String.valueOf(longitude));
        mb.addTag("Precision", String.valueOf(precision));
        mb.surroundWith("xml");
        return mb.toString();
    }

    @Override
    public String toString() {
        return "LocationEvent [latitude=" + latitude + ", longitude="
                + longitude + ", precision=" + precision + ", toUserName="
                + toUserName + ", fromUserName=" + fromUserName
                + ", createTime=" + createTime + ", msgType=" + msgType + "]";
    }
}
