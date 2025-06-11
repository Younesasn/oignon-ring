package fr.younesasn.models.classes;

import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Samourai extends Hero {

  public Samourai(String nom) {
    super("Samouraï", nom, 110, 17, 5, 10, 1);
  }

  @Override
  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      cible.prendreDegats(this.attaque + (this.attaque / 2));
      this.mana = this.mana - 10;
      System.out.println("\nAttaque spéciale : Essaim de mouches 🗡️\nAttaque +50% 🔝");
    }
  }
}
