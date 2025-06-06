package fr.younesasn.models;

public class Ennemi extends Personnage {
  public Ennemi(String nom, int attaque, int defense) {
    super(nom, 100, attaque, defense);
  }

  @Override
  public String toString() {
    return "\nNom: " + this.nom + "\nPV: " + this.pv + "\nAttaque: " + this.attaque + "\nDéfense: " + this.defense;
  }
}
