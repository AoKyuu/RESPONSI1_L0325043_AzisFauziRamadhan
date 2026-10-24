package Responsi;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class ManifestManager {
    private ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();
    
    public void tambahBarang (String kodeBarang, String namaBarang, String kategori, double beratKg)
        throws BeratInvalidException {
        if (!kodeBarang.startsWith("EDX-")) {
            throw new IllegalArgumentException ("Kode barang harus diawali EDX-");
        }
        
        if (beratKg <= 0) {
            throw new BeratInvalidException ("Berat barang harus lebih dari 0 kg");
        }
        
        daftarBarang.add(new BarangBantuan(kodeBarang, namaBarang, kategori, beratKg));
        }
    
    public int getTotalBarang(){
        return daftarBarang.size();
    }
    
    public double getTotalBerat(){
        double total = 0;
        for (BarangBantuan barang : daftarBarang) {
            total += barang.getBeratKg();
        }
        return total;
    }
    
    public ArrayList<BarangBantuan> getdaftarBarang() {
        return daftarBarang;
    }
    
    public String Armada() {
        double total = getTotalBerat();
        if (total <= 50) {
            return "Mobil Pickup";
        } else {
            return "Truk Ekspedisi";
        }
    }
    
    public void eksporManifest() throws IOException {
        try (BufferedWriter BW = new BufferedWriter(new FileWriter("manifest_pengiriman.txt"))) {
            BW.write("Manifest Pengiriman Logistik Pendidikan 3T");
            BW.newLine();
            BW.newLine();
        
            BW.write(String.format("%-10s | %-25s | %-12s | %11s", "KODE", "NAMA BARANG", "KATEGORI", "BERAT"));
            BW.newLine();
            BW.write("-".repeat(66));
            BW.newLine();
        
            for (BarangBantuan barang : daftarBarang) {
                BW.write(barang.toString());
                BW.newLine();
            }
        
            BW.write("-".repeat(66));
            BW.newLine();
            BW.write("Total Barang : " + getTotalBarang() + " dus");
            BW.newLine();
            BW.write(String.format("Total Berat  : %.2f kg", getTotalBerat()));
            BW.newLine();
            BW.write("Armada       : " + Armada());
            BW.newLine();
        }
    
        System.out.println("Data manifes berhasil diekspor ke manifest_pengiriman.txt");
    }
}