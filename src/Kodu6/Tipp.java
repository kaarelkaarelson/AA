package Kodu6;

// Tipu klass puu kodeeringu puu konstrueerimiseks.
public class Tipp {
    String info;
    Tipp v;
    Tipp p;
    int x; //abiväli

    Tipp(Character info, Tipp v, Tipp p, int x) {
        this.info = String.valueOf(info);
        this.v = v;
        this.p = p;
        this.x = x;
    }

    Tipp(Character info, int x) {
        this.info = String.valueOf(info);
        this.x = x;
    }

    public String getInfo() {
        return info;
    }

    public int getX() {
        return x;
    }
}
