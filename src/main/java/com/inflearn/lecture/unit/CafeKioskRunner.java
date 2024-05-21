package com.inflearn.lecture.unit;

import com.inflearn.lecture.unit.beverage.Americano;
import com.inflearn.lecture.unit.beverage.Latte;

class CafeKioskRunner {
    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk(); // 객체 생성
        cafeKiosk.add(new Americano()); // 아메리카노 추가
        System.out.println(">>> 아메리카노 추가");

        cafeKiosk.add(new Latte()); // 라떼 추가
        System.out.println(">>> 라떼 추가");

        int totalPrice = cafeKiosk.calculateTotalPrice();
        System.out.println(">>> 총 주문가격 : " + totalPrice + "원");

    }
}
