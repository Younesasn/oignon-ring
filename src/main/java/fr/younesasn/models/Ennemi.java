package fr.younesasn.models;

public abstract class Ennemi extends Personnage {
  protected Ennemi(String nom, int pv, int attaque, int defense) {
    super(nom, pv, attaque, defense);
  }

  @Override
  public String toString() {
    return "{ Ennemi: " + this.nom + ", PV: " + this.pv + ", Attaque: " + this.attaque + ", Défense: " + this.defense + " }";
  }
}
