package fr.younesasn.models.classes;

import fr.younesasn.interfaces.PouvoirSpecial;
import fr.younesasn.models.Hero;
import fr.younesasn.models.Personnage;

public class Samourai extends Hero implements PouvoirSpecial {

  public Samourai(String nom) {
    super("Samouraï", nom, 110, 17, 5, 10, 1);
  }

  public void utiliserPouvoir(Personnage cible) {
    if (this.mana != 0) {
      System.out.println("Pouvoir spécial utilisé !");
      cible.prendreDegats(this.mana);
      this.mana = super.mana - 10;
    }
  }
}
