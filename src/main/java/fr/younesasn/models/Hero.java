package fr.younesasn.models;

import fr.younesasn.interfaces.PouvoirSpecial;

public abstract class Hero extends Personnage implements PouvoirSpecial {
  private String nomClasse;
  protected int mana;
  protected int potion;

  protected Hero(String nomClasse, String nom, int pv, int attaque, int defense, int mana, int potion) {
    super(nom, pv, attaque, defense);
    this.nomClasse = nomClasse;
    this.mana = mana;
    this.potion = potion;
  }

  public int getMana() {
    return mana;
  }

  public void setMana(int mana) {
    this.mana = mana;
  }

  public int getPotion() {
    return potion;
  }

  public void setPotion(int potion) {
    this.potion = potion;
  }

  public void utiliserPotion() {
    if (this.potion <= 0) {
      return;
    }
    this.pv += 110 - this.pv;
    this.potion -= 1;

    // Régénération du mana selon la classe
    switch (this.nomClasse) {
      case "Guerrier":
        this.mana = Math.min(10, this.mana + 10);
        break;
      case "Samouraï":
        this.mana = Math.min(10, this.mana + 10);
        break;
      case "Mage":
        this.mana = Math.min(40, this.mana + 20);
        break;
      case "Vagabond":
        // Le Vagabond n'a pas de mana, donc pas de régénération
        break;
      default:
        break;
    }
  }

  public void utiliserPouvoir(Personnage cible) {}

  @Override
  public String toString() {
    return "{ Classe: " + this.nomClasse + ", PV: 110, Attaque: " + this.attaque + ", Défense: "
        + this.defense
        + ", Mana: " + this.mana + ", Potion: " + this.potion + " }";
  }

  public String getEtat() {
    return "{ PV: " + this.pv + ", Mana: " + this.mana + ", Potion: " + this.potion + " }";
  }
}
