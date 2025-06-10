package fr.younesasn.models.classes;

import fr.younesasn.interfaces.PouvoirSpecial;
import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Vagabond extends Hero implements PouvoirSpecial {

  public Vagabond(String nom) {
    super("Vagabond", nom, 110, 5, 3, 0, 1);
  }

  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      System.out.println("Pouvoir spécial utilisé !");
      cible.prendreDegats(this.mana);
      this.mana = super.mana - 10;
    }
  }
}
