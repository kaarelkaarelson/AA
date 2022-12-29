package kodu5;


public class Tipp {
        public String info;
        public Tipp v;
        public Tipp p;
        public int x; //abiväli

        public Tipp(String info, Tipp v, Tipp p) {
            this.info = info;
            this.v = v;
            this.p = p;
        }

        public Tipp(String info) {
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
    }
