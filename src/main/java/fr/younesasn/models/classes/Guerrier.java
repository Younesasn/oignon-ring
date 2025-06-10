package fr.younesasn.models.classes;

import fr.younesasn.interfaces.PouvoirSpecial;
import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Guerrier extends Hero implements PouvoirSpecial {

  public Guerrier(String nom) {
    super("Guerrier", nom, 110, 20, 9, 10, 1);
  }

  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      System.out.println("Pouvoir spécial utilisé !");
      cible.prendreDegats(this.mana);
      this.mana = super.mana - 10;
    }
  }
}
