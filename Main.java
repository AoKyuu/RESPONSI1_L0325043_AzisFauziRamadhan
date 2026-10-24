package Responsi;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ManifestManager manager = new ManifestManager();
        int pilihan = 0;

        // Spesifikasi 2a: perulangan menu sampai user memilih "Selesai"
        do {
            tampilkanMenu();
            try {
                pilihan = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Pilihan harus berupa angka 1-3.");
                pilihan = 0;
                continue;
            }

            switch (pilihan) {
                case 1:
                    tambahBarangInteraktif(manager);
                    break;
                case 2:
                    lihatDaftar(manager);
                    break;
                case 3:
                    cetakManifes(manager);
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Pilih 1-3.");
            }
        } while (pilihan != 3);

        System.out.println("Program selesai.");
    }

    private static void tampilkanMenu() {
        System.out.println();
        System.out.println("=== SISTEM LOGISTIK PENDIDIKAN DAERAH 3T ===");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Lihat Daftar Barang");
        System.out.println("3. Selesai dan Cetak Manifes");
        System.out.print("Pilih menu: ");
    }

    // Spesifikasi 3b-iii: input diulang sampai valid
    private static void tambahBarangInteraktif(ManifestManager manager) {
        boolean berhasil = false;

        while (!berhasil) {
            try {
                System.out.print("Kode barang (awali EDX-) : ");
                String kode = input.nextLine().trim();
                System.out.print("Nama barang              : ");
                String nama = input.nextLine().trim();
                System.out.print("Kategori                 : ");
                String kategori = input.nextLine().trim();
                System.out.print("Berat (kg)               : ");
                double berat = Double.parseDouble(input.nextLine().trim());

                manager.tambahBarang(kode, nama, kategori, berat);
                System.out.println("Barang berhasil ditambahkan.");
                berhasil = true;

            } catch (BeratInvalidException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                // NumberFormatException adalah turunan IllegalArgumentException,
                // jadi pesan dibedakan di bawah
                if (e instanceof NumberFormatException) {
                    System.out.println("Error: Berat harus berupa angka.");
                } else {
                    System.out.println("Error: " + e.getMessage());
                }
            } finally {
                if (!berhasil) {
                    System.out.println("Silakan ulangi input barang.\n");
                }
            }
        }
    }

    private static void lihatDaftar(ManifestManager manager) {
        if (manager.getTotalBarang() == 0) {
            System.out.println("Belum ada barang yang dicatat.");
            return;
        }
        System.out.println("--- Daftar Barang ---");
        for (BarangBantuan b : manager.getdaftarBarang()) {
            System.out.println(b);
        }
        System.out.printf("Total: %d dus, %.2f kg%n",
                manager.getTotalBarang(), manager.getTotalBerat());
    }

    private static void cetakManifes(ManifestManager manager) {
        try {
            manager.eksporManifest();
            System.out.println("Armada: " + manager.Armada());
        } catch (IOException e) {
            System.out.println("Gagal menulis file: " + e.getMessage());
        }
    }
}