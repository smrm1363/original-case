package com.afkl.cases.df.statistic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticServiceTest {

    @Autowired
    StatisticService statisticService;
    @Test
    public void getStatistic() {
        statisticService.getStatistic();
    }
}