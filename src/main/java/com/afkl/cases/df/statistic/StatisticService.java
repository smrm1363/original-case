package com.afkl.cases.df.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StatisticService {
    @Autowired
    HttpTraceEndpoint httpTraceEndpoint;
    public Statistic getStatistic()
    {
        Statistic statistic = new Statistic();
        List<HttpTrace> httpTraceList =httpTraceEndpoint.traces().getTraces();
        AtomicReference<Long> totalResponseTime = new AtomicReference<>((long) 0);
        httpTraceList.forEach(httpTrace ->
        {
            statistic.setTotalRequestsProcessed(statistic.getTotalRequestsProcessed()+1);
            if(httpTrace.getResponse().getStatus()==200)
                statistic.setTotalRequestsOK(statistic.getTotalRequestsOK()+1);
            else if(httpTrace.getResponse().getStatus()>=400 && httpTrace.getResponse().getStatus()<500)
                statistic.setTotalRequests4xx(statistic.getTotalRequests4xx()+1);
            else if(httpTrace.getResponse().getStatus()>=500 && httpTrace.getResponse().getStatus()<600)
                statistic.setTotalRequests5xx(statistic.getTotalRequests5xx()+1);
            if(httpTrace.getTimeTaken()>statistic.getMaxResponseTime()||statistic.getMaxResponseTime()==0)
                statistic.setMaxResponseTime(httpTrace.getTimeTaken());
            if(httpTrace.getTimeTaken()<statistic.getMinResponseTime()||statistic.getMinResponseTime()==0)
                statistic.setMinResponseTime(httpTrace.getTimeTaken());
            totalResponseTime.set(totalResponseTime.get()+httpTrace.getTimeTaken());
        });
        if(httpTraceList!=null)
            if(httpTraceList.size()>0)
                statistic.setAverageResponseTime(totalResponseTime.get()/httpTraceList.size());
        return statistic;
    }
}
