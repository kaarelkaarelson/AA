//package kodu7;

public class TippT implements Comparable<TippT>{

    String info;
    int x;
    int i;
    TippT eellane;

    TippT(String info, int i,  int x, TippT eellane) {
        this.info = info;
        this.i = i;
        this.x = x;
        this.eellane = eellane;
    }

    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    @Override
    public int compareTo(TippT t) {
        return this.x - t.x != 0 ? this.x - t.x : this.info.compareTo(t.info);
    }
}
