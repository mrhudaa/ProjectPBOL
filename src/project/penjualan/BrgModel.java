/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.penjualan;

/**
 *
 * @author karen
 */
public class BrgModel {
    private String kodebrg, namabrg, gambar;
    private double tarif;
    
    public String getKodebrg() {
        return kodebrg;    }
    
    public void setKodebrg(String kodebrg) {        
        this.kodebrg = kodebrg;  }
        
    public String getNamabrg() {
        return namabrg;    }
    
    public void setNamabrg(String namabrg) {        
        this.namabrg = namabrg;  }
    
    public String getGambar() {
        return gambar;    }
    
    public void setGambar(String gambar) {        
        this.gambar = gambar;  }
    
    public double getTarif() {
        return tarif;    }
    
    public void setTarif(double tarif) {
        this.tarif = tarif;    }
    
}
