package com.inflearn.lecture.unit;

import com.inflearn.lecture.unit.CafeKiosk;
import com.inflearn.lecture.unit.beverage.Americano;
import com.inflearn.lecture.unit.beverage.Latte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CafeKioskTest {
    @Test
    void add_manual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 담긴 음료 수: "+cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: "+cafeKiosk.getBeverages().get(0).getName());

        cafeKiosk.add(new Latte());
        System.out.println(">>> 담긴 음료 수: "+cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료: "+cafeKiosk.getBeverages().get(1).getName());

    }
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }
    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages().isEmpty());
    }
    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages().isEmpty());
    }

}