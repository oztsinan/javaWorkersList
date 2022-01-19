import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    static int kisiSayisi = 0;
    static int veriSayisi = 5;
    static String[][] CalisanVeriler;
    static ArrayList veriler = new ArrayList();
    static File f = new File("workers.data");

    public static void main(String[] args) throws FileNotFoundException {


        // CalisanVeriler[ kişi ][ kişiye ait veriler ]
        // Arkaplanda yazdıgım fonksiyonlar içinde otomatik olarak kişi sayısına göre
        // dizi oluşturulup verileri bize gösteriyor.
        // 2 boyutlu dizi kullanarak verilere kolayca erişebiliyorum.
        // CalisanVeriler[0][0] ilk kişinin id 'si' ----------------- [0][1] ilk kişinin ismi gibi...



        Scanner input = new Scanner(System.in);

            verileriOku();
            verileriDiziyeYaz();


            System.out.println("Merhaba , asagidaki numarlari kullanarak seciminizi yapabilirsiniz;");
            System.out.println("1. Çalışanları göster");
            System.out.println("2. Çalışan ekle");
            System.out.println("3. Şehirdeki çalışanları ara");
            System.out.println("4. Çalışanlara ödenen toplam maaşı göster");

            int baslangic = input.nextInt();
            
            if (baslangic == 1) {
                System.out.println("Kayıtlı Çalışanlar");
                calisanlariGoster();
                System.out.println("---------------------------");
                System.out.println();

            } else if (baslangic == 2) {
                System.out.println("Çalışan Ekle");
                verileriOku();
                verileriDiziyeYaz();
                addNewWorker();
                System.out.println("---------------------------");
                System.out.println();

            } else if (baslangic == 3) {
                System.out.println("Aramak istediginiz sehri yaziniz : ");
                String sehir = input.next();
                findWorkersByCity(sehir);
                System.out.println("---------------------------");
                System.out.println();

            } else if (baslangic == 4) {

                calisanlaraOdenenToplamMaas();
                System.out.println("---------------------------");
                System.out.println();

            } else {
                System.out.println("Lütfen 1 ile 4 arasında bir değer giriniz!");
            }
            
        
    }

    public static void verileriOku() {

        try {
            Scanner scanner = new Scanner(f);
            scanner.nextLine();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                veriler.add(line);
                kisiSayisi++;
            }

            scanner.close();
            CalisanVeriler = new String[kisiSayisi][veriSayisi];

        } catch (Exception e) {

            System.out.println("Error while searching");
        }

    }

    public static void verileriDiziyeYaz() {

        for (int sutun = 0; sutun < veriSayisi; sutun++) {
            for (int satir = 0; satir < kisiSayisi; satir++) {
                String tumVeriyiAl2 = (String) veriler.get(satir);
                String[] strParts2 = tumVeriyiAl2.split(" , "); // alınan verileri virgülden ayır

                if (sutun == 0) {
                    CalisanVeriler[satir][sutun] = strParts2[0]; // id verisini diziye ekler
                } else if (sutun == 1) {
                    CalisanVeriler[satir][sutun] = strParts2[1]; // name verisini diziye ekler
                } else if (sutun == 2) {
                    CalisanVeriler[satir][sutun] = strParts2[2]; // name verisini diziye ekler
                } else if (sutun == 3) {
                    CalisanVeriler[satir][sutun] = strParts2[3]; // name verisini diziye ekler
                } else if (sutun == 4) {
                    CalisanVeriler[satir][sutun] = strParts2[4]; // name verisini diziye ekler
                }
            }
        }

    }

    public static void calisanlariGoster() {
        for (int k = 0; k < kisiSayisi; k++) {

            for (int a = 0; a < 5; a++) {
                System.out.print(CalisanVeriler[k][a] + " ");
            }
            System.out.println("");

        }
    }

    public static void createFile() {
        try {
            if (!f.exists()) {
                FileWriter fileWriter = new FileWriter(f);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("ID , Name , Salary , Department , City");
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
                System.out.println("File has been created successfully");

            } else {
                System.out.println("The file exists");
            }

        } catch (Exception e) {
            System.out.println("Error while creating a file");
        }
    }

    public static void addNewWorker() {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter your ID");
        String id = input.nextLine();

        System.out.println("Enter your name");
        String name = input.nextLine();

        System.out.println("Enter your salary");
        String salary = input.nextLine();

        System.out.println("Enter your department");
        String department = input.nextLine();

        System.out.println("Enter your city");
        String city = input.nextLine();

        try {

            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(id + " , " + name + " , " + salary + " , " + department + " , " + city);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("A new record has been added to file");

        } catch (Exception e) {
            System.out.println("An error while adding new student to file" + e.getLocalizedMessage());
        }

        verileriOku();
        verileriDiziyeYaz();

    }

    public static void findWorkersByCity(String searchedValue) {

        int sayac = 0;
        for (int i = 0; i < kisiSayisi; i++) {
            if (CalisanVeriler[i][4].equals(searchedValue)) {
                sayac++;
            }
        }

        if (sayac == 0) {
            System.out.println(" " + searchedValue + " " + " sehrinde yasayan hicbir insan yok");

        } else if (sayac > 0) {
            System.out.println(" " + searchedValue + " " + " sehrinde yasayan insanlarin listesi");
            for (int k = 0; k < kisiSayisi; k++) {

                if (CalisanVeriler[k][4].equals(searchedValue)) {

                    for (int a = 0; a < 5; a++) {
                        System.out.print(CalisanVeriler[k][a] + " ");
                    }
                    System.out.println("");

                }
            }

        }

    }

    public static void calisanlaraOdenenToplamMaas() {
        int toplamMaas = 0;
        int maas = 0;

        for(int i=0;i<kisiSayisi;i++){
            maas = Integer.parseInt(CalisanVeriler[i][2]);
            toplamMaas += maas;
        }
        System.out.println("Calisanlara ödenen toplam maaş : "+ toplamMaas + " TL");
    }

}
