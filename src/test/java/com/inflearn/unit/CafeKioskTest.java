package com.inflearn.unit;

import com.inflearn.lecture.unit.CafeKiosk;
import com.inflearn.lecture.unit.beverage.Americano;
import com.inflearn.lecture.unit.beverage.Latte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CafeKioskTest {
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 담긴 음료 수: "+cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: "+cafeKiosk.getBeverages().get(0).getName());

        cafeKiosk.add(new Latte());
        System.out.println(">>> 담긴 음료 수: "+cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: "+cafeKiosk.getBeverages().get(1).getName());

    }
}