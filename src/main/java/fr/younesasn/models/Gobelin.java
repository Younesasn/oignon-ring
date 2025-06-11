package fr.younesasn.models;

import fr.younesasn.utils.Utils;

public class Gobelin extends Ennemi {
  public Gobelin() {
    super("Gobelin", 100, Utils.getRandomNumber(8, 12), Utils.getRandomNumber(1, 5));
  }
}
