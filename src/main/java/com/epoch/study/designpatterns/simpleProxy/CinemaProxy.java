package com.epoch.study.designpatterns.simpleProxy;

public class CinemaProxy implements Movie{

    private RealMovie realMovie;
    public CinemaProxy(RealMovie realMovie){
        super();
        this.realMovie=realMovie;
    }

    @Override
    public void play() {
        guanggao(true);
        realMovie.play();
        guanggao(false);
    }

    public void guanggao(boolean isStart) {
        if (isStart) {
            System.out.println("电影马上开始了，爆米花可乐，口香糖9折，快来买啊");
        }else {
            System.out.println("电影马上结束了，爆米花可乐，口香糖9折，买回去吃吧");
        }
    }
}
