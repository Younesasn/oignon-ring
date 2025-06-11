package fr.younesasn.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
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

    choixClasse(nom);
    combat();
  }

  public static void choixClasse(String nom) {
    boolean choixValide = false;

    while (!choixValide) {
      try {
        System.out.println("\n1: " + new Samourai(nom));
        System.out.println("\n2: " + new Guerrier(nom));
        System.out.println("\n3: " + new Mage(nom));
        System.out.println("\n4: " + new Vagabond(nom));
        System.out.print("\nClasse de votre héros : ");
        int choix = sc.nextInt();

        switch (choix) {
          case 1:
            System.out.println("\nClasse choisi : Samouraï 🥷🏾");
            hero = new Samourai(nom);
            choixValide = true;
            break;
          case 2:
            System.out.println("\nClasse choisi : Guerrier 🤺");
            hero = new Guerrier(nom);
            choixValide = true;
            break;
          case 3:
            System.out.println("\nClasse choisi : Mage 🧙‍♂️");
            hero = new Mage(nom);
            choixValide = true;
            break;
          case 4:
            System.out.println("\nClasse choisi : Vagabond 🧍‍♂️");
            hero = new Vagabond(nom);
            choixValide = true;
            break;
          default:
            System.out.println("\nErreur : Veuillez saisir un chiffre entre 1 et 4.");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Erreur : Veuillez saisir un chiffre entre 1 et 4.");
        sc.next();
      }
    }
  }

  public static void combat() {
    int compteurGobelins = 0;

    while (hero.estVivant()) {
      Ennemi ennemi = genererEnnemi(compteurGobelins);
      System.out.println("\n" + ennemi.getNom() + " N°" + (compteur + 1) + " vous attaque ! ⚔️");
      if (!combattreEnnemi(ennemi)) {
        return; // Le héros est mort
      }
      compteur++;
      hero.setPotion(hero.getPotion() + 1);

      if (ennemi instanceof Gobelin) {
        compteurGobelins++;
      } else {
        compteurGobelins = 0; // Reset le compteur après un boss
      }
    }

    System.out.println("\nVous avez exterminé " + compteur + " ennemis !");
    enregistrerScore();
  }

  private static boolean combattreEnnemi(Ennemi ennemi) {
    while (hero.estVivant() && ennemi.estVivant()) {
      afficherEtatCombat(ennemi);
      int choix = obtenirChoixJoueur();

      if (!executerActionJoueur(choix, ennemi)) {
        return false; // Le héros est mort
      }
    }
    return true; // L'ennemi est vaincu
  }

  private static void afficherEtatCombat(Ennemi ennemi) {
    System.out.println("\n👤 Votre état actuel : " + hero.getEtat());
    System.out.println("👾 Votre ennemi : " + ennemi);
    System.out.println("\n🎮 À vous de jouez ! Vous pouvez : \n1: Attaquez ⚔️");
    if (hero.getMana() > 0) {
      System.out.println("2: Utiliser pouvoir spécial ☄️");
    }
    if (hero.getPotion() > 0) {
      System.out.println("3: Utiliser potion 🧪");
    }
    System.out.println("4: Quittez le jeu 🚪");
  }

  private static int obtenirChoixJoueur() {
    int choix = 0;
    boolean choixValide = false;

    while (!choixValide) {
      try {
        System.out.print("\nVotre choix : ");
        choix = sc.nextInt();

        if (choix == 1 ||
            (choix == 2 && hero.getMana() > 0) ||
            (choix == 3 && hero.getPotion() > 0) ||
            choix == 4) {
          choixValide = true;
        } else {
          System.out
              .println("\n❌ Erreur : Veuillez saisir un choix valide (1, 2, ou 3 selon les options disponibles).");
        }
      } catch (InputMismatchException e) {
        System.out.println("\n❌ Erreur : Veuillez saisir un nombre valide.");
        sc.next();
      }
    }

    return choix;
  }

  private static boolean executerActionJoueur(int choix, Ennemi ennemi) {
    switch (choix) {
      case 1:
        int degats = hero.attaquer(ennemi);
        System.out.println("\n⚔️ Vous avez attaqué ! Vous avez infligé à votre ennemi : " + degats + " PV");
        break;
      case 2:
        hero.utiliserPouvoir(ennemi);
        break;
      case 3:
        hero.utiliserPotion();
        System.out
            .println("\nVous avez utilisé une potion ! 🧪 PV et Mana régénérés ! Potion restant : " + hero.getPotion());
        break;
      case 4:
        hero.setPv(0);
        break;
      default:
        break;
    }

    return executerAttaqueEnnemi(ennemi);
  }

  private static boolean executerAttaqueEnnemi(Ennemi ennemi) {
    if (!hero.estVivant()) {
      System.out.println("\nVous êtes mort...💀 Fin du jeu.");
      System.out.println("\nVous avez exterminé " + compteur + " ennemis !");
      enregistrerScore();
      return false;
    }
    int degats = ennemi.attaquer(hero);
    System.out.println("\nVotre ennemi vous a attaqué, Il vous a infligé : " + degats + " PV");
    return true;
  }

  public static Ennemi genererEnnemi(int compteurGobelins) {
    // Ajouter un boss tous les 3 gobelins
    if (compteurGobelins >= 3) {
      List<Ennemi> bosses = new ArrayList<>();
      bosses.add(new Margit());
      bosses.add(new Godrick());
      bosses.add(new Rennala());
      bosses.add(new Radhan());
      bosses.add(new RadhanPrime());

      return bosses.get(Utils.getRandomNumber(0, bosses.size() - 1));
    }

    // Sinon, générer un gobelin
    return new Gobelin();
  }

  public static void enregistrerScore() {
    List<String> contenu = List.of(
        "Date : " + new Date().toString(),
        "Partie joué par : " + hero.getNom(),
        "Personnage : " + hero,
        "Ennemis vaincus : " + compteur,
        "=====");
    try {
      Files.write(Paths.get("src/main/resources/score.txt"), contenu, StandardOpenOption.CREATE,
          StandardOpenOption.APPEND);
          System.out.println("\nDonnées sauvegardées dans score.txt 📊");
    } catch (IOException e) {
      System.out.println("Erreur : " + e);
    }
  }
}
