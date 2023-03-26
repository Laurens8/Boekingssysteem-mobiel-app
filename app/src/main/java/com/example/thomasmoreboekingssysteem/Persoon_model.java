package com.example.thomasmoreboekingssysteem;

public class Persoon_model {
    String Naam;
    String Voornaam;
    String Personeelnummer;
    Boolean Aanwezig,Admin;

    public Persoon_model(String Personeelnummer,String Naam, String Voornaam, Boolean Aanwezig, Boolean Admin){
        this.Personeelnummer=Personeelnummer;
        this.Naam =Naam;
        this.Voornaam=Voornaam;
        this.Aanwezig=Aanwezig;
        this.Admin=Admin;
    }

    public Persoon_model(){

    }

    public String getPersoneelnummer(){
        return Personeelnummer;
    }

    public String setPersoneelnummer(String Personeelnummer){
        return this.Personeelnummer = Personeelnummer;
    }

    public String getNaam(){
        return Naam;
    }

    public String setNaam(String Naam){
        return this.Naam = Naam;
    }

    public String getVoornaam(){
        return Voornaam;
    }

    public String setVoornaam(String Voornaam){
        return this.Voornaam = Voornaam;
    }

    public Boolean getAanwezig(){
        return Aanwezig;
    }
    public Boolean setAanwezig(Boolean Aanwezig){
        return this.Aanwezig = Aanwezig;
    }

    public Boolean getAdmin(){
        return Aanwezig;
    }
    public Boolean setAdmin(Boolean Admin){
        return this.Admin = Admin;
    }

    @Override
    public String toString() {
        return "Persoon_model{" +
                "Naam='" + Naam + '\'' +
                ", Voornaam='" + Voornaam + '\'' +
                ", Personeelnummer='" + Personeelnummer + '\'' +
                ", Aanwezig=" + Aanwezig +
                '}';
    }
}
