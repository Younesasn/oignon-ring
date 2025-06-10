package fr.younesasn.models;

public abstract class Hero extends Personnage {
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

  public void utiliserPotion() {
    if (this.potion < 0) {
      return;
    }
    this.pv += this.pv % 2;
    this.potion -= 1;
  }

  @Override
  public String toString() {
    return "{ Classe: " + this.nomClasse + ", PV: " + this.pv + ", Attaque: " + this.attaque + ", Défense: " + this.defense
        + ", Mana: " + this.mana + " }";
  }

  public String getEtat() {
    return "{ PV=" + this.pv + " Mana=" + this.mana + " }";
  }
}
