import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
   public static char[] alfabet = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


        public static int randomGenerator() {
            int[] liczby_pierwsze = new int[] {2,3,5,7,11,13,17,19,23,29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
            int random = getRandomFromArray(liczby_pierwsze);
            return random;
        }

        public static String caesar(int losowaA, int losowaB, String string) {
            String zaszyfrowany = "";
            int l = string.length();
            char ch;
            int randomA = losowaA;
            int randomB = losowaB;


            for(int i=0; i<l; i++){
                ch = string.charAt(i);
                if(Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                    int indexP = findIndex(ch, alfabet);
                    int y = ((randomB*indexP)+randomA)%26;
                    ch = alfabet[y];
                    ch = Character.toUpperCase(ch);
                    zaszyfrowany += ch;
                }else {
                    int indexP = findIndex(ch, alfabet);
                    if(indexP==0&&ch!='a')
                        continue;
                    int y = ((randomB*indexP)+randomA)%26;
                    ch = alfabet[y];
                    zaszyfrowany += ch;
                }
            }

            return zaszyfrowany;
        }

        public static int findIndex(char p,char[] array) {
            int length = array.length;
            int indexP=0;
            for(int i=0; i<length; i++) {
                if(array[i]==p) {
                    indexP=i;
                    break;
                }
            }
            return indexP;
        }

        public static int getRandomFromArray(int[] a) {
            int rndm = new Random().nextInt(a.length);
            return a[rndm];
        }

    public static String deszyfruj(int a, int b, String string) {
        String newString = "";
        int l = string.length();
        char ch;
        for(int i=0; i<l; i++) {
            ch = string.charAt(i);
            if(Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
                int indexCh = findIndex(ch, alfabet);
                int index = indexCh - a;
                while(index<0)
                    index+=26;
                index = (index + 26) % 26;
                int aModular = modInverse(b, 26);
                int p = index * aModular;
                p = p % 26;
                ch = alfabet[p];
                ch = Character.toUpperCase(ch);
                newString += ch;
            } else {
                int indexCh = findIndex(ch, alfabet);
                int index = indexCh - a;
                while(index<0)
                    index+=26;
                index = (index + 26) % 26;
                int aModular = modInverse(b, 26);
                int p = index * aModular;
                p = p % 26;
                ch = alfabet[p];
                newString += ch;
            }
        }
        return newString;
    }

    public static int modInverse(int b, int m)
    {
        b=b%m;
        // 1=aa^{-1) %m
        //A naive method to find modulo multiplicative inverse of
        //// 'a' under modulo 'm'
        for (int x = 1; x < m; x++)
            if ((b * x) % m == 1)
                return x;
        return 1;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("SZYFR CEZARA");
        System.out.println("Wybierz opcję:...");
        System.out.println("1. Szyfrowanie");
        System.out.println("2. Deszyfracja");
        int numer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Wybierz opcje:");
        try{
            numer = Integer.parseInt(br.readLine());
        }catch(NumberFormatException | IOException nfe){
            System.err.println("Zly Format!");
        }
        switch (numer) {
            case 1: {
                System.out.println("wybrano szyfracje");
                System.out.flush();
               // int randomA = randomGenerator();
               // int randomB = randomGenerator();
                int randomA=0;
                int randomB=0;

                System.out.println("K0: " );
                BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));

                try{
                    randomA = Integer.parseInt(br.readLine());
                }catch(NumberFormatException | IOException nfe){
                    System.err.println("Zly Format!");
                }
                System.out.println("K1: ");
                BufferedReader br6 = new BufferedReader(new InputStreamReader(System.in));

                try{
                    randomB = Integer.parseInt(br.readLine());
                }catch(NumberFormatException | IOException nfe){
                    System.err.println("Zly Format!");
                }
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Podaj slowo do zaszyfrowania:");
                String essay = br1.readLine();
                String newEssay = "";
                String szyfrowany = caesar(randomA, randomB, essay);
                newEssay += szyfrowany + "";
                System.out.println("Source: " + essay);
                System.out.println("Encrypted: " + newEssay);
                String wynik =deszyfruj(randomA,randomB,newEssay);
                System.out.println(("Zdeszyfrowano:" + wynik));
            }

            case 2:{
                System.out.println("wybrano deszyfracje");
                System.out.flush();
                BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                String s="";
                System.out.print("Podaj Zaszyfrowany tekst");
                s = br2.readLine();
                System.out.print("Podaj K0:");
                int K0=0;
                try{
                    K0 = Integer.parseInt(br.readLine());
                }catch(NumberFormatException nfe){
                    System.err.println("Invalid Format!");
                }
                System.out.print("Podaj K1:");
                int K1=0;
                try{
                    K1 = Integer.parseInt(br.readLine());
                }catch(NumberFormatException nfe){
                    System.err.println("Invalid Format!");
                }
                String wynik =deszyfruj(K0,K1,s);
                System.out.println(("Zdeszyfrowano:" + wynik));
            }
        }}

}
