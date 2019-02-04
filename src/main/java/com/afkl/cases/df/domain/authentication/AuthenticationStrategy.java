package com.afkl.cases.df.domain.authentication;
@FunctionalInterface
public interface AuthenticationStrategy {
    public String getToken();
}
