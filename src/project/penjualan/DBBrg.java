/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.penjualan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author karen
 */
public class DBBrg {
    private BrgModel dt=new BrgModel();    
    public BrgModel getBrgModel(){ return(dt);}
    public void setBrgModel(BrgModel s){ dt=s;}
    
    public ObservableList<BrgModel>  Load() {
        try {
            ObservableList<BrgModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from barang");
int i = 1;
            while (rs.next()) {
                BrgModel d=new BrgModel();
                d.setKodebrg(rs.getString("kodebrg"));                
                d.setNamabrg(rs.getString("namabrg"));               
                d.setTarif(rs.getDouble("tarif"));
               
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
     public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from barang where kodebrg = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
     
     public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into barang (kodebrg, namabrg, tarif) values (?,?,?)");
            con.preparedStatement.setString(1, getBrgModel().getKodebrg());           
            con.preparedStatement.setString(2, getBrgModel().getNamabrg());       
            con.preparedStatement.setDouble(3, getBrgModel().getTarif());        
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
     
     public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from barang where kodebrg  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
     
     public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update barang set namabrg = ?, tarif = ?  where  kodebrg = ? ");
            con.preparedStatement.setString(1, getBrgModel().getKodebrg());
            con.preparedStatement.setString(2, getBrgModel().getNamabrg());
            con.preparedStatement.setDouble(3, getBrgModel().getTarif());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        }
     
     public ObservableList<BrgModel>  CariBrg(String kode, String nama) {
        try {    
            ObservableList<BrgModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from barang WHERE kodebrg LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            BrgModel d = new BrgModel();
            d.setKodebrg(rs.getString("kodebrg"));
            d.setNamabrg(rs.getString("namabrg"));
            d.setTarif(rs.getDouble("tarif"));
            
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
