package com.tianyu.jty.content.entity;

import java.util.List;

/**
 * 封装list数据
 * @author xtao
 * @date 2015-11-2
 */
public class ListData<E> {
    List<E> datas;
    int count;

    public ListData(List<E> list){
        this.datas = list;
        this.count = list.size();
    }
    public List<E> getDatas() {
        return datas;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "datas=" + datas +
                ", count=" + count +
                '}';
    }
}
