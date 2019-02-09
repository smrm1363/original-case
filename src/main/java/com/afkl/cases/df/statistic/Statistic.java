package com.afkl.cases.df.statistic;

import java.io.Serializable;

public class Statistic implements Serializable {
    private Integer totalRequestsProcessed = 0;
    private Integer totalRequestsOK = 0;
    private Integer totalRequests4xx = 0;
    private Integer totalRequests5xx = 0;
    private Long averageResponseTime = 0l;
    private Long minResponseTime = 0l;
    private Long maxResponseTime = 0l;

    public Integer getTotalRequestsProcessed() {
        return totalRequestsProcessed;
    }

    public void setTotalRequestsProcessed(Integer totalRequestsProcessed) {
        this.totalRequestsProcessed = totalRequestsProcessed;
    }

    public Integer getTotalRequestsOK() {
        return totalRequestsOK;
    }

    public void setTotalRequestsOK(Integer totalRequestsOK) {
        this.totalRequestsOK = totalRequestsOK;
    }

    public Integer getTotalRequests4xx() {
        return totalRequests4xx;
    }

    public void setTotalRequests4xx(Integer totalRequests4xx) {
        this.totalRequests4xx = totalRequests4xx;
    }

    public Integer getTotalRequests5xx() {
        return totalRequests5xx;
    }

    public void setTotalRequests5xx(Integer totalRequests5xx) {
        this.totalRequests5xx = totalRequests5xx;
    }

    public Long getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(Long averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public Long getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(Long minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public Long getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(Long maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }
}
