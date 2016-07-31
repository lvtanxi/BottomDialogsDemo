package com.lv.bottomdialogsdemo.selectpop;

/**
 * User: 吕勇
 * Date: 2016-02-18
 * Time: 12:22
 * Description:默认的选择项模式
 */
public class DefExtendItem implements ExtendItem {

    private int id;
    private String val;

    public DefExtendItem(int id, String val) {
        this.id = id;
        this.val = val;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public String getValue() {
        return val;
    }


}
