package com.epoch.study.designpatterns.simpleProxy;

public class ProxyTest {

    public static void main(String[] args) {
        RealMovie realMovie = new RealMovie();
        CinemaProxy cinemaProxy = new CinemaProxy(realMovie);

        cinemaProxy.play();
    }
}
