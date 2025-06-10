package fr.younesasn.models.classes;

import fr.younesasn.interfaces.PouvoirSpecial;
import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Sorcier extends Hero implements PouvoirSpecial {

  public Sorcier(String nom) {
    super("Sorcier", nom, 110, 10, 3, 40, 3);
  }

  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      System.out.println("Pouvoir spécial utilisé !");
      cible.prendreDegats(this.mana);
      this.mana = super.mana - 10;
    }
  }
}
