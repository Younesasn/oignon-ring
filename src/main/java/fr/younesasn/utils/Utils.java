package fr.younesasn.utils;

public class Utils {
  private Utils(){}
  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
