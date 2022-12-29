package TT2;

public class TippG {
    public String info;
    public TippG v;
    public TippG p;
    public int x; //abiväli

    public TippG(String info, TippG v, TippG p) {
        this.info = info;
        this.v = v;
        this.p = p;
    }

    public TippG(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public int getVäärtus() {
        return x;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setVäärtus(int väärtus) {
        this.x = väärtus;
    }

}//klass Tipp