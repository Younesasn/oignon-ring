package fr.younesasn.services;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.younesasn.models.*;
import fr.younesasn.models.bosses.*;
import fr.younesasn.models.classes.*;
import fr.younesasn.utils.Utils;

public class CombatManager {
  private static Scanner sc = new Scanner(System.in);
  private static Hero hero;
  private static int compteur = 0;

  private CombatManager() {
  }

  public static void initialisationPartie() {
    System.out.println("\nLancement du jeu...");
    System.out.println("\n=== Bienvenue dans Oignon Ring 🗡️  ===\n");
    System.out.print("Veuillez indiquez le nom de votre héro : ");
    String nom = sc.nextLine();

    System.out.println("\nSalut " + nom + " ! Choisissez la classe de votre héro : ");

    choix(nom);
    combat();
  }

  public static void choix(String nom) {
    try {
      System.out.println("\n1: " + new Samourai(nom));
      System.out.println("\n2: " + new Guerrier(nom));
      System.out.println("\n3: " + new Sorcier(nom));
      System.out.println("\n4: " + new Vagabond(nom));
      System.out.print("\nClasse de votre héros : ");
      int choix = sc.nextInt();

      switch (choix) {
        case 1:
          System.out.println("\nClasse choisi : Samouraï 🥷🏾");
          hero = new Samourai(nom);
          break;
        case 2:
          System.out.println("\nClasse choisi : Guerrier 🤺");
          hero = new Guerrier(nom);
          break;
        case 3:
          System.out.println("\nClasse choisi : Sorcier 🧙‍♂️");
          hero = new Sorcier(nom);
          break;
        case 4:
          System.out.println("\nClasse choisi : Vagabond 🧍‍♂️");
          hero = new Vagabond(nom);
          break;
        default:
          System.out.println("\nVeuillez réessayez...");
          choix(nom);
          break;
      }
    } catch (InputMismatchException e) {
      System.out.println("Erreur : Veuillez saisir un chiffre entre 1 et 4.");
      // choix(nom);
    }
  }

  public static void combat() {
    genererEnnemi();
    for (Ennemi ennemi : genererEnnemi()) {
      System.out.println("\n" + ennemi.getNom() + " vous attaque ! ⚔️");
      compteur++;
      while (ennemi.estVivant()) {
        System.out.println("\n👤 Votre état actuel : " + hero);
        System.out.println("👾 Votre ennemi : " + ennemi);
        System.out.println("\n🎮 À vous de jouez ! Vous pouvez : \n1: Attaquez \n2: Utiliser pouvoir spécial");
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();
        switch (choix) {
          case 1:
            hero.attaquer(ennemi);
            // System.out.println(
            // "\n ⚔️ Vous avez attaqué ! Vous avez infligé à votre ennemi : " +
            // hero.calculerDegats() + " PV");
            break;
          case 2:
            // hero.utiliserPouvoir();
            // System.out.println("Vous avez attaqué fort !! Dégâts infligés : " +
            // hero.calculerDegats(ennemi.getAttaque()) + " PV");
          default:
            break;
        }
        ennemi.attaquer(hero);
        if (!hero.estVivant()) {
          System.out.println("Vous êtes mort...💀 Fin du jeu.");
          System.out.println("\nVous avez exterminé " + compteur + " ennemis !");
          return;
        }
      }
    }
  }

  public static List<Ennemi> genererEnnemi() {
    List<Ennemi> ennemis = new ArrayList<>();
    List<Ennemi> bosses = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Gobelin gobelin = new Gobelin();
      ennemis.add(gobelin);
    }

    Margit margit = new Margit();
    bosses.add(margit);

    Godrick godrick = new Godrick();
    bosses.add(godrick);

    Rennala rennala = new Rennala();
    bosses.add(rennala);

    Radhan radhan = new Radhan();
    bosses.add(radhan);

    RadhanPrime radhanPrime = new RadhanPrime();
    bosses.add(radhanPrime);

    for (int i = 0; i < Utils.getRandomNumber(1, 5); i++) {
      ennemis.add(bosses.get(Utils.getRandomNumber(0, 5)));
    }
    return ennemis;
  }
}
