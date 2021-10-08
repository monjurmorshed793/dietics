package com.morshed.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.morshed.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BiochemicalTestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BiochemicalTest.class);
        BiochemicalTest biochemicalTest1 = new BiochemicalTest();
        biochemicalTest1.setId("id1");
        BiochemicalTest biochemicalTest2 = new BiochemicalTest();
        biochemicalTest2.setId(biochemicalTest1.getId());
        assertThat(biochemicalTest1).isEqualTo(biochemicalTest2);
        biochemicalTest2.setId("id2");
        assertThat(biochemicalTest1).isNotEqualTo(biochemicalTest2);
        biochemicalTest1.setId(null);
        assertThat(biochemicalTest1).isNotEqualTo(biochemicalTest2);
    }
}
