package com.example.nosdeputes;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class Deputy implements Serializable {
    int id, numCirco;
    private String firstName, lastName, department, nameCirco;
    private String email, groupeParlementaire;
    private String twitter;
    private ArrayList<String> collaborateurs;
    private String sexe;
    private int placeHemicycle;
    private String adressePerm;
    private String urlAN;
    public Deputy(int id, int numCirco, String firstName, String lastName, String department, String nameCirco, String twitter, String sexe, int placeHemicycle, String urlAN) {
        this.id = id;
        this.numCirco = numCirco;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.nameCirco = nameCirco;
        this.twitter=twitter;
        this.sexe=sexe;
        this.placeHemicycle=placeHemicycle;
        this.urlAN=urlAN;
        this.collaborateurs=new ArrayList<String>();
    }

    public Deputy(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getNumCirco() {

        return numCirco;
    }

    public void setNumCirco(int numCirco) {

        this.numCirco = numCirco;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getNameCirco() {

        return nameCirco;
    }

    public void setNameCirco(String nameCirco) {

        this.nameCirco = nameCirco;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getGroupeParlementaire() {

        return groupeParlementaire;
    }

    public void setGroupeParlementaire(String groupeParlementaire) {
        this.groupeParlementaire = groupeParlementaire;
    }

    public ArrayList<String> getCollaborateurs() {
        return collaborateurs;
    }

    public void setCollaborateurs(ArrayList<String> collaborateurs) {
        this.collaborateurs = collaborateurs;
    }
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    public void addCollaborateur(String nouveauCollaborateur) {
        this.collaborateurs.add(nouveauCollaborateur);
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getPlaceHemicycle() {
        return placeHemicycle;
    }

    public void setPlaceHemicycle(int placeHemicycle) {
        this.placeHemicycle = placeHemicycle;
    }

    public String getAdressePerm() {
        return adressePerm;
    }

    public void setAdressePerm(String adressePerm) {
        this.adressePerm = adressePerm;
    }

    public String getUrlAN() {
        return urlAN;
    }

    public void setUrlAN(String urlAN) {
        this.urlAN = urlAN;
    }

    public String getNameForAvatar() {
        return firstName.replace(' ', '-').toLowerCase() + '-' +
                lastName.replace(' ', '-').toLowerCase();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Deputy d = (Deputy) obj;
        return id == d.getId();
    }
}
