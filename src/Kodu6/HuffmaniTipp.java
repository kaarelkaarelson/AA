
// Huffmani tipu klass puu kodeeringu puu konstrueerimiseks.
public class HuffmaniTipp {
    String info;
    HuffmaniTipp v;
    HuffmaniTipp p;
    int x; //abiväli

    HuffmaniTipp(Character info, HuffmaniTipp v, HuffmaniTipp p, int x) {
        this.info = String.valueOf(info);
        this.v = v;
        this.p = p;
        this.x = x;
    }

    HuffmaniTipp(Character info, int x) {
        this.info = String.valueOf(info);
        this.x = x;
    }

    public String getInfo() {
        return info;
    }

    public int getX() {
        return x;
    }
}//klass HuffmaniTipp
