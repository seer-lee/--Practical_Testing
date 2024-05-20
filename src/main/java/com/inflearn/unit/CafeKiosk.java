package com.inflearn.unit;

import java.util.ArrayList;
import java.util.List;

class CafeKiosk {
    private final List<Beverage> beverages = new ArrayList<>(); // 음료 리스트

    // 음료 추가
    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    // 음료 제거
    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    // 음료 리스트 초기화
    public void clear() {
        beverages.clear();
    }

    // 총 주문 가격 계산
    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Beverage beverage : beverages) {
            totalPrice += beverage.getPrice();
        }
        return totalPrice;
    }
}
