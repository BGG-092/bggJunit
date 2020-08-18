package me.bgg.junit5.bggjunit;

public class BggJunit03 {

    private BggJunit03Status status = BggJunit03Status.DRAFT;

    private int limit;

    private int a;
    private int b;

    public BggJunit03(int limit) {
        this.limit = limit;
    }

    public BggJunit03(int a,int b){
        if(a <0){
            throw new IllegalArgumentException("a가 0보다 작으면 안된다.");
        }
        this.limit = a+b;
    }

    public BggJunit03Status getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
