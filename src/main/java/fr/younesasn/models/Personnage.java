package fr.younesasn.models;

public abstract class Personnage {
  protected String nom;
  protected int pv;
  protected int attaque;
  protected int defense;

  protected Personnage(String nom, int pv, int attaque, int defense) {
    this.nom = nom;
    this.pv = pv;
    this.attaque = attaque;
    this.defense = defense;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getPv() {
    return pv;
  }

  public void setPv(int pv) {
    this.pv = pv;
  }

  public int getDefense() {
    return defense;
  }

  public void setDefense(int attaque) {
    this.attaque = attaque;
  }

  public int getAttaque() {
    return attaque;
  }

  public void setAttaque(int attaque) {
    this.attaque = attaque;
  }

  public void attaquer(Personnage cible) {
    cible.prendreDegats(this.attaque);
  }

  public void prendreDegats(int degats) {
    this.pv -= degats - this.defense;
  }

  public boolean estVivant() {
    return this.pv != 0;
  }
}
