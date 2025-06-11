package fr.younesasn.models.classes;

import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Mage extends Hero {

  public Mage(String nom) {
    super("Mage", nom, 110, 10, 3, 40, 3);
  }

  @Override
  public void utiliserPouvoir(Personnage cible) {
    if (this.mana >= 20) {
      cible.prendreDegats(this.mana);
      this.mana = this.mana - 20;
      System.out.println("\nAttaque spéciale : Comète d'Azur 🧊\nAttaque +50% 🔝");
    }
  }
}
