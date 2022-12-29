package kodu1;
import java.util.List;

public class Tabel {

    /**
     * Väljastab 3 veeruga tabeli. Lihtsuse mõttes on seatud kitsendus 3-le veerule.
     * @param veerud - etteantud järjend, kus on Veerg objektid.
     */
    public static void väljastaTabel3Veergu(List<Veerg> veerud) {

        System.out.println(String.format("%25s %10s %30s %10s %30s", veerud.get(0).getVeeruNimi(), "|",  veerud.get(1).getVeeruNimi(), "|", veerud.get(2).getVeeruNimi()));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));

        int andmeridu = veerud.get(0).getVeeruAndmed().length;
        for (int i = 0; i < andmeridu ; i++) {
            System.out.println(String.format("%20s %15s %25s %15s %25s", veerud.get(0).getVeeruAndmed()[i], "|",  veerud.get(1).getVeeruAndmed()[i], "|", veerud.get(2).getVeeruAndmed()[i]));
        }

        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
    }
}
