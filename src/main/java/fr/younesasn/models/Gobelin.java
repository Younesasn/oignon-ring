package fr.younesasn.models;

import fr.younesasn.utils.Utils;

public class Gobelin extends Ennemi {
  public Gobelin() {
    super("Gobelin", 100, Utils.getRandomNumber(4, 10), Utils.getRandomNumber(0, 3));
  }
}
