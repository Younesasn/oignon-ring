package fr.younesasn.models.classes;

import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Guerrier extends Hero {

  public Guerrier(String nom) {
    super("Guerrier", nom, 110, 20, 9, 10, 1);
  }

  @Override
  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      cible.prendreDegats(this.attaque * 2);
      this.mana = this.mana - 10;
      System.out.println("\nAttaque spéciale : Explosion exaltée 💥\nAttaque double ➿");
    }
  }
}
