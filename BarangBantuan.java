package Responsi;

public class BarangBantuan {
    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private double beratKg;

    public BarangBantuan (String kodeBarang, String namaBarang, String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public double getBeratKg() {
        return beratKg;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-25s | %-12s | %8.2f kg",
                kodeBarang, namaBarang, kategori, beratKg);
    }
}