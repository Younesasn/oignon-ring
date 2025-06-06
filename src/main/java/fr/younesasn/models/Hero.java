package fr.younesasn.models;

import fr.younesasn.interfaces.PouvoirSpecial;

public class Hero extends Personnage implements PouvoirSpecial {
  protected int mana;

  public Hero(String nom, int pv, int attaque, int defense, int mana) {
    super(nom, pv, attaque, defense);
    this.mana = mana;
  }

  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      cible.prendreDegats(this.mana);
      this.mana = 0;
    }
  }

  public int getMana() {
    return mana;
  }

  public void setMana(int mana) {
    this.mana = mana;
  }

  @Override
  public String toString() {
    return "\nPV: " + this.pv + "\nAttaque: " + this.attaque + "\nDéfense: " + this.defense
        + "\nMana: " + this.mana;
  }
}
