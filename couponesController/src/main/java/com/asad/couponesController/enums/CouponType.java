package com.asad.couponesController.enums;

public enum CouponType {
RESTURANS("resturans"),
ELECTRICITY("electricity"),
FOOD("food"),
HEALTH("health"),
SPORTS("sports"),
CAMPING("camping"),
TRAVELLING("travelling");

private final String code;

CouponType(String code) {
    this.code = code;
}

public String getCode() {
    return code;
}
}
