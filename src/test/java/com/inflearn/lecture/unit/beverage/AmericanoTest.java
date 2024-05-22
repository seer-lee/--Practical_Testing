package com.inflearn.lecture.unit.beverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {
    @Test
    void getName() {
        Americano americano = new Americano();
        // assertEquals(americano.getName(),"아메리카노");
        assertThat(americano.getName()).isEqualTo("아메리카노"); // 메서드 체이닝 가능
    }

    @Test
    @DisplayName("아메리카노의 가격은 4000원")
    void getPrice() {
        Americano americano = new Americano();
        // assertEquals(americano.getPrice(),4000);
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}