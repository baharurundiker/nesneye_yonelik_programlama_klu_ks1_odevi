package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Kitap sınıfı
class Kitap {
    private String kitapAdi;
    private String yazar;

    public Kitap(String kitapAdi, String yazar) {
        this.kitapAdi = kitapAdi;
        this.yazar = yazar;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public String getYazar() {
        return yazar;
    }
}

// Uye sınıfı
class Uye {
    private String ad;
    private int uyeNo;

    public Uye(String ad, int uyeNo) {
        this.ad = ad;
        this.uyeNo = uyeNo;
    }

    public String getAd() {
        return ad;
    }

    public int getUyeNo() {
        return uyeNo;
    }
}

// Gorevli sınıfı
class Gorevli {
    private String ad;
    private int gorevliNo;

    public Gorevli(String ad, int gorevliNo) {
        this.ad = ad;
        this.gorevliNo = gorevliNo;
    }

    public String getAd() {
        return ad;
    }

    public int getGorevliNo() {
        return gorevliNo;
    }
}

// Kutuphane sınıfı
public class KutuphaneYonetimSistemi {
    private static ArrayList<Kitap> kitaplar = new ArrayList<>();
    private static ArrayList<Uye> uyeler = new ArrayList<>();
    private static ArrayList<Gorevli> gorevliler = new ArrayList<>();
    private static HashMap<Uye, Kitap> oduncKitaplar = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bahar Üründiker 1220505018");
            System.out.println("******* Kütüphane Yönetim Sistemi *******");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Çıkart");
            System.out.println("3. Üye Ekle");
            System.out.println("4. Üye Çıkart");
            System.out.println("5. Görevli Ekle");
            System.out.println("6. Görevli Çıkart");
            System.out.println("7. Kitap Ödünç Al");
            System.out.println("8. Kitap İade Et");
            System.out.println("9. Ödünç Alınan Kitapları Göster");
            System.out.println("0. Çıkış");
            System.out.print("Lütfen yapmak istediğiniz işlemi seçiniz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // Dummy bir nextLine() çağrısı, sorunları önlemek için

            switch (secim) {
                case 1:
                    System.out.print("Kitap Adı: ");
                    String kitapAdi = scanner.nextLine();
                    System.out.print("Yazar: ");
                    String yazar = scanner.nextLine();
                    kitapEkle(kitapAdi, yazar);
                    break;
                case 2:
                    System.out.print("Kitap Adı: ");
                    kitapAdi = scanner.nextLine();
                    kitapCikart(kitapAdi);
                    break;
                case 3:
                    System.out.print("Üye Adı: ");
                    String uyeAdi = scanner.nextLine();
                    System.out.print("Üye Numarası: ");
                    int uyeNo = scanner.nextInt();
                    uyeEkle(uyeAdi, uyeNo);
                    break;
                case 4:
                    System.out.print("Üye Numarası: ");
                    uyeNo = scanner.nextInt();
                    uyeCikart(uyeNo);
                    break;
                case 5:
                    System.out.print("Görevli Adı: ");
                    String gorevliAdi = scanner.nextLine();
                    System.out.print("Görevli Numarası: ");
                    int gorevliNo = scanner.nextInt();
                    gorevliEkle(gorevliAdi, gorevliNo);
                    break;
                case 6:
                    System.out.print("Görevli Numarası: ");
                    gorevliNo = scanner.nextInt();
                    gorevliCikart(gorevliNo);
                    break;
                case 7:
                    System.out.print("Üye Numarası: ");
                    uyeNo = scanner.nextInt();
                    Uye oduncUye = uyeBul(uyeNo);
                    if (oduncUye != null) {
                        System.out.print("Kitap Adı: ");
                        kitapAdi = scanner.next();
                        Kitap oduncKitap = kitapBul(kitapAdi);
                        if (oduncKitap != null) {
                            kitapOduncAl(oduncUye, oduncKitap);
                        } else {
                            System.out.println("Belirtilen kitap bulunamadı.");
                        }
                    } else {
                        System.out.println("Belirtilen üye bulunamadı.");
                    }
                    break;
                case 8:
                    System.out.print("Üye Numarası: ");
                    uyeNo = scanner.nextInt();
                    oduncUye = uyeBul(uyeNo);
                    if (oduncUye != null) {
                        kitapOduncIadeEt(oduncUye);
                    } else {
                        System.out.println("Belirtilen üye bulunamadı.");
                    }
                    break;
                case 9:
                    oduncKitaplariGoster();
                    break;
                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);
                default:
                    System.out.println("Hatalı seçenek! Lütfen tekrar deneyin.");
                    break;
            }
        }
    }

    // Kitap ekleme
    private static void kitapEkle(String kitapAdi, String yazar) {
        Kitap kitap = new Kitap(kitapAdi, yazar);
        kitaplar.add(kitap);
        System.out.println("Kitap eklenmiştir: " + kitapAdi);
    }

    // Kitap çıkartma
    private static void kitapCikart(String kitapAdi) {
        Kitap kitap = kitapBul(kitapAdi);
        if (kitap != null) {
            kitaplar.remove(kitap);
            System.out.println("Kitap çıkartılmıştır: " + kitapAdi);
        } else {
            System.out.println("Belirtilen kitap bulunamadı.");
        }
    }

    // Üye ekleme
    private static void uyeEkle(String ad, int uyeNo) {
        Uye uye = new Uye(ad, uyeNo);
        uyeler.add(uye);
        System.out.println("Üye eklenmiştir: " + ad);
    }

    // Üye çıkartma
    private static void uyeCikart(int uyeNo) {
        Uye uye = uyeBul(uyeNo);
        if (uye != null) {
            uyeler.remove(uye);
            System.out.println("Üye çıkartılmıştır: " + uye.getAd());
        } else {
            System.out.println("Belirtilen üye bulunamadı.");
        }
    }

    // Görevli ekleme
    private static void gorevliEkle(String ad, int gorevliNo) {
        Gorevli gorevli = new Gorevli(ad, gorevliNo);
        gorevliler.add(gorevli);
        System.out.println("Görevli eklenmiştir: " + ad);
    }

    // Görevli çıkartma
    private static void gorevliCikart(int gorevliNo) {
        Gorevli gorevli = gorevliBul(gorevliNo);
        if (gorevli != null) {
            gorevliler.remove(gorevli);
            System.out.println("Görevli çıkartılmıştır: " + gorevli.getAd());
        } else {
            System.out.println("Belirtilen görevli bulunamadı.");
        }
    }

    // Üye bulma
    private static Uye uyeBul(int uyeNo) {
        return uyeler.stream().filter(u -> u.getUyeNo() == uyeNo).findFirst().orElse(null);
    }

    // Görevli bulma
    private static Gorevli gorevliBul(int gorevliNo) {
        return gorevliler.stream().filter(g -> g.getGorevliNo() == gorevliNo).findFirst().orElse(null);
    }

    // Kitap bulma
    private static Kitap kitapBul(String kitapAdi) {
        return kitaplar.stream().filter(k -> k.getKitapAdi().equals(kitapAdi)).findFirst().orElse(null);
    }

    // Kitap ödünç alma
    private static void kitapOduncAl(Uye uye, Kitap kitap) {
        if (!oduncKitaplar.containsKey(uye)) {
            oduncKitaplar.put(uye, kitap);
            System.out.println(uye.getAd() + " adlı üyeye " + kitap.getKitapAdi() + " kitabı ödünç verilmiştir.");
        } else {
            System.out.println(uye.getAd() + " adlı üye zaten bir kitap ödünç almış durumda.");
        }
    }

    // Kitap ödünç iade etme
    private static void kitapOduncIadeEt(Uye uye) {
        Kitap oduncKitap = oduncKitaplar.get(uye);
        if (oduncKitap != null) {
            oduncKitaplar.remove(uye);
            System.out.println(uye.getAd() + " adlı üyenin " + oduncKitap.getKitapAdi() + " kitabı kütüphaneye iade edilmiştir.");
        } else {
            System.out.println(uye.getAd() + " adlı üye ödünç kitap almamış durumda.");
        }
    }

    // Ödünç alınan kitapları gösterme
    private static void oduncKitaplariGoster() {
        System.out.println("Şu anda ödünç alınan kitaplar:");
        for (HashMap.Entry<Uye, Kitap> entry : oduncKitaplar.entrySet()) {
            System.out.println(entry.getKey().getAd() + ": " + entry.getValue().getKitapAdi());
        }
    }
}
