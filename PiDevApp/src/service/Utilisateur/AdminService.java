/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Utilisateur;

import Util.Util;
import database.MyDB;
import entites.Utilisateur.Client;
import entites.Utilisateur.Vendeur;
import iservice.Utilisateur.IAdmin;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class AdminService implements IAdmin{
private ObservableList<Client>data;
private ObservableList<Vendeur>dat;
     private Statement stm;
    Connection con;
    @Override
    public ObservableList<Client> SelectClient() {
        
         try {
             con = MyDB.getInstance().getConnection();
             data=FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("Select * From users where type='client'");
            while (res.next()) {   
                
                
                data.add(new Client(res.getInt(1),res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10), res.getString(11),res.getString(13), res.getString(14), res.getString(15), res.getString(16)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur"+ex);
        }
    return data;
    }
    public AdminService(){
        try {
            con = MyDB.getInstance().getConnection();
            stm = con.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public boolean UpdateClient(String email, String npassword) {
       
        
          
            boolean insert = false;
        
    try {
        stm.executeUpdate("UPDATE users SET password='" + npassword + "'WHERE email='" +email + "' and type = 'client'");
        insert = true;
    } catch (SQLException ex) {
        Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
    }
            System.out.println("Update Password Client");
            
            
        


    
        return insert;
    }
      
    

    @Override
    public void SupprimerClient(Client c) {
        try {
            stm.executeUpdate("DELETE FROM users WHERE id=" + c.getId() + "");

        } catch (SQLException ex) {
            System.out.println("Supprimer");
            System.out.println(ex);
        }
    }
     public boolean recherche(String mail) {
        boolean existe = false;
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='vendeur' AND email ='" + mail + "'");
            System.out.println("ooook"); 
           if(res.next())
           {
               existe = true;
           }
            else
           {
               existe=false;
           }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
      public void modifierVendeur(Vendeur v,String email) {
        try {
           
            
            stm.executeUpdate("UPDATE users SET nom='"+v.getNom()+"',prenom='"+v.getPrenom()+"',dateDeNaissance='"+v.getDate_naissance()+"',sexe='"+v.getSexe()+"',adresse='"+v.getAdresse()+"',ville='"+v.getVille()+"',zip="+v.getZip()+",numeroDuTelephone='"+v.getNt()+"'WHERE email='"+email+"'and type='vendeur'");
       
        } catch (SQLException ex) {
            System.out.println("modifier");
            System.out.println(ex);
        }
      }
       public void SupprimerVendeur(Vendeur v) {
        try {
            stm.executeUpdate("DELETE FROM users WHERE id=" + v.getId() + "");

        } catch (SQLException ex) {
            System.out.println("Supprimer");
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Vendeur> SelectVendeur() {
try {
             con = MyDB.getInstance().getConnection();
        dat=FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("Select * From users where type='vendeur'");
            while (res.next()) {   
                
                dat.add(new Vendeur(res.getInt(1),res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10), res.getString(11),res.getString(13), res.getString(17), res.getString(18)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur"+ex);
        }   
    return dat;
    }
      public void modifierIDBVendeur(Vendeur v,String email) {
        try {
           
            
            stm.executeUpdate("UPDATE users SET situaitionFiscal='"+v.getSutiation_fiscal()+"',ribBancaire='"+v.getNumero_bancaire()+"'WHERE email='"+email+"'and type='vendeur'");
       
        } catch (SQLException ex) {
            System.out.println("IDB modifier");
            System.out.println(ex);
        }
      }
   
}
