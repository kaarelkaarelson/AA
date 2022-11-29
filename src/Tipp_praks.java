//public class TippVana {
//    String info;
//    int value;
//    TippVana v;
//    TippVana p;
//
//    public Tipp(String info) {
//        this.info = info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//    public void setV(Tipp v) {
//        this.v = v;
//    }
//
//    public void setP(Tipp p) {
//        this.p = p;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public Tipp getV() {
//        return v;
//    }
//
//    public Tipp getP() {
//        return p;
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public void setValue(int value) {
//        this.value = value;
//    }
//
//    public static int tippudeArv(Tipp t) {
//        return t == null ? 0 : (1 + tippudeArv(t.v) + tippudeArv(t.p));
//    }
//
//    public static int kõrgus(Tipp t) {
//        return t == null ? 0 : (1 + Math.max(kõrgus(t.v), kõrgus(t.p)));
//    }
//
//    public static int suurimKirje(Tipp t) {
//        return t.p == null ? t.getValue() : suurimKirje(t.p);
//    }
///*
//    public static Tipp juhuslikAVL(int h ){
//        if (h == 0) return null;
//        if (h == 1) return new Tipp("0");
//       int variant = (int) (Math.random()*3);
//      return switch (variant) {
//          case 0 -> new Tipp()
//      }
//    }
//*/
//
//    public static void väljastaKülili(Tipp t) {
//        väljastaKülili(t, 0);
//    }
//    public static void väljastaKülili(Tipp t, int sügavus) {
//        if(t == null)return;
//        väljastaKülili(t.p, sügavus+1);
//        System.out.println(" ".repeat(sügavus) + t.info);
//        väljastaKülili(t.v, sügavus +1);
//    }
//
//}
