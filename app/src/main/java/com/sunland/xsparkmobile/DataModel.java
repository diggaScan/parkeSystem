package com.sunland.xsparkmobile;

public class DataModel {

    public static final String[] FUNCTION_NAMES = {"个人车辆处理记录", "我的申请", "待我审批", "单号查询", "二维码扫描", "设置"};

    public static final int[] FUNTION_ICONS = {R.drawable.ic_car, R.drawable.ic_request_ac_function
            , R.drawable.ic_approval_ac_function, R.drawable.ic_archive_search, R.drawable.ic_qrcode_3, R.drawable.ic_setting};

    public static final String[] STATUS = {"待拖", "拖移中", "停入", "转出申请", "待定转出申请"
            , "待定转出审批", "待定转出移交",
            "待定转出接收", "报废审批", "事故车报废归档",
            "转出审批", "已转出", "回收接收", "为发车报废归档",
            "修改申请", "申报申请", "放行审批", "放车", "离去"};

    public static final int[] STATUS_COLORS = {R.color.one, R.color.two, R.color.three, R.color.four, R.color.five, R.color.six,
            R.color.seven, R.color.eight, R.color.nine, R.color.ten, R.color.ele, R.color.twe, R.color.thir, R.color.fourt, R.color.fift, R.color.sixt
            , R.color.sevt, R.color.eighte};

    public static final String[] TYPES = {"违停", "交通肇事", "超载", "超高", "超重", "无证驾驶"};
}
