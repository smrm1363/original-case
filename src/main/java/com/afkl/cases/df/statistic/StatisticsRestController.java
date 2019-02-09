package com.afkl.cases.df.statistic;

import com.afkl.cases.df.ApplicationException;
import com.afkl.cases.df.fare.Fare;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsRestController {
    private final StatisticService statisticService;

    public StatisticsRestController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @GetMapping("/statistics")
    public Statistic getStatistics() throws ApplicationException {
        return statisticService.getStatistic();
    }
}
