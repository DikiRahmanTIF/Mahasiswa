package DR.Mahasiswa;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class DataMahasiswa {
    // Atribut
    private String nama;
    private LocalDate tanggalLahir;
    private long ukt;
    private String nomor;

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String Nomor) {
        this.nomor = nomor;
    }

    public void setmObject(String[][] mObject) {
        this.mObject = mObject;
    }

    public DataMahasiswa() {}
    // Constructor
    public DataMahasiswa(String nama, LocalDate tanggalLahir, long ukt) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.ukt = ukt;
    }

    // Setter and Getter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public long getUkt() {
        return ukt;
    }

    public void setUkt(long ukt) {
        this.ukt = ukt;
    }

    // Method untuk menghitung usia berdasarkan tanggal lahir
    public String hitungUsia() {
        // Menghitung selisih tanggal sekarang dengan tanggal lahir menggunakan Period
        Period selisih = Period.between(tanggalLahir, LocalDate.now());
        // Mengambil tahun, bulan, dan hari dari selisih
        int tahun = selisih.getYears();
        int bulan = selisih.getMonths();
        int hari = selisih.getDays();
        // Menggabungkan tahun, bulan, dan hari menjadi string
        String usia = tahun + " tahun " + bulan + " bulan " + hari + " hari";
        return usia;
    }

    // Method untuk mengkategorikan UKT
    public String kategorikanUkt() {
        if (ukt < 500000) {
            return "UKT 1";
        } else if (ukt >= 500000 && ukt < 1500000) {
            return "UKT 2";
        } else if (ukt >= 1500000 && ukt < 3000000) {
            return "UKT 3";
        } else if (ukt >= 3000000 && ukt < 4500000) {
            return "UKT 4";
        } else if (ukt >= 4500000 && ukt < 5000000) {
            return "UKT 5";
        } else if (ukt >= 5000000 && ukt < 6000000) {
            return "UKT 6";
        } else if (ukt >= 6000000 && ukt < 7000000) {
            return "UKT 7";
        } else {
            return "UKT tidak diketahui";
        }
    }

    private String[][] mObject;

    public String[][] getmObject() throws FileNotFoundException {
        this.readingData();
        return mObject;
    }

    //Membaca Data di TempStrArr menggunakan scanner
    public void readingData() throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "/src/TempStrArr.txt");
        Scanner sc2 = new Scanner(file);
        Scanner sc = new Scanner(file);

        int totalLine = 0;
        while (sc2.hasNextLine()){
            sc2.nextLine();
            totalLine += 1;
        }

        String[][] StoDArray = new String[totalLine][6];

        int startLoopMain = 0;
        while (sc.hasNextLine()) {
            String[] sObject = String.valueOf(sc.nextLine()).split("#");
            for (int i = 0; i < sObject.length; i++) {
                StoDArray[startLoopMain][i] = sObject[i];
            }
            startLoopMain++;
        }
        this.mObject = StoDArray;
    }

}