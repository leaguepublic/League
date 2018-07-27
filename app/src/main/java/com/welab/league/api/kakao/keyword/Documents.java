package com.welab.league.api.kakao.keyword;

public class Documents {
    public String place_name;
    public String distance;
    public String place_url;
    public String category_name;
    public String address_name;
    public String road_address_name;
    public String id;
    public String phone;
    public String category_group_code;
    public String category_group_name;
    public String x;
    public String y;

    @Override
    public String toString() {
        StringBuilder logBuff = new StringBuilder();
        logBuff.append("\nDocuments >>");
        logBuff.append("\nplace_name : " + place_name);
        logBuff.append("\ndistance : " + distance);
        logBuff.append("\nplace_url : " + place_url);
        logBuff.append("\ncategory_name : " + category_name);
        logBuff.append("\naddress_name : " + address_name);
        logBuff.append("\nroad_address_name : " + road_address_name);
        logBuff.append("\nid : " + id);
        logBuff.append("\nphone : " + phone);
        logBuff.append("\ncategory_group_code : " + category_group_code);
        logBuff.append("\ncategory_group_name : " + category_group_name);
        logBuff.append("\nx : " + x);
        logBuff.append("\ny : " + y);
        logBuff.append("\nDocuments <<");

        return logBuff.toString();
    }

//    "place_name": "카카오프렌즈 코엑스점",
//    "distance": "418",
//    "place_url": "http://place.map.daum.net/26338954",
//    "category_name": "가정,생활 > 문구,사무용품 > 디자인문구 > 카카오프렌즈",
//    "address_name": "서울 강남구 삼성동 159",
//    "road_address_name": "서울 강남구 영동대로 513",
//    "id": "26338954",
//    "phone": "02-6002-1880",
//    "category_group_code": "",
//    "category_group_name": "",
//    "x": "127.05902969025047",
//    "y": "37.51207412593136"


}
