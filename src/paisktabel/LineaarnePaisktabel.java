package paisktabel;

import java.util.LinkedList;

public class LineaarnePaisktabel {
    private final Isik[] tabel;


    public LineaarnePaisktabel(int n ) {
        this.tabel = new Isik[n];
    }

    private int paiska(int id){
        double T = (Math.sqrt(5) - 1) / 2;

        return (int) (tabel.length * (id * T - (int) (id * T)));
    }

   public void lisa(Isik isik) {
        int algneKoht = paiska(isik.getID());

       for (int i = 0; i < tabel.length; i++) {
          int koht = (algneKoht + i ) % tabel.length;
          if (tabel[koht] == null){
              tabel[koht] = isik;
             return;
          }
       }
       throw  new RuntimeException("Tabel on täis");
   }

   public Isik otsi(int id) {
        int algneKoht = paiska(id);

       for (int i = 0; i < tabel.length; i++) {
          int koht = (algneKoht + 1) % tabel.length;

          if(tabel[koht] == null) return null;

          if(tabel[koht].getID() == id) return tabel[koht];
       }
       return null;
   }

   public Isik eemalda(int id) {
       int algneKoht = paiska(id);

       Isik leitud = null;

       int i = 0;
       for (; i < tabel.length; i++) {
           int koht = (algneKoht + 1) % tabel.length;

           if(tabel[koht] == null) throw  new RuntimeException(" Sellise ID-ga Isikut paisktabelis pole  ");

           if(tabel[koht].getID() == id)  {
               leitud = tabel[koht];
               tabel[koht] = null;
               break;
           }
       }
       if ( leitud == null) throw  new RuntimeException(" Sellise ID-ga Isikut paisktabelis pole  ");

       LinkedList<Isik> liikuvad = new LinkedList<>();// Sest tulevad uuesti paisata
       for(; i < tabel.length; i++){

           int koht = (algneKoht + 1) % tabel.length;
          if (tabel[koht] == null) break;
          liikuvad.add(tabel[koht]);

       }
       for (Isik isik : liikuvad){
           lisa(isik);
       }

       return leitud;
   }
}
