package LivingThings;

import java.util.Random;

abstract public class LivingThing {

	public String name;
	public double health;
	public double attackPower;
	public boolean isDead = false;

	public LivingThing(String name, double health, double attackPower) {
		this.name = name;
		this.health = health;
		this.attackPower = attackPower;
	}

	public void attack(LivingThing target) {
		target.takeDamage(this.attackPower);
	}

	public void takeDamage(double damage) {
		this.health = this.health - damage;
	}

	public boolean checkDeath() {
		if (this.health <= 0) {
			this.isDead = true;
		}
		return this.isDead;
	}

	public static void main(String[] args) {
		LivingThing[] monsters = new LivingThing[5];
		monsters[0] = new Rat("Boris", 20, 15, 1);
		monsters[1] = new Rat("Jeremy", 20, 15, 2);
		monsters[2] = new Goblin("Glitter", 20, 3);
		monsters[3] = new Goblin("Dusty", 30, 3);
		monsters[4] = new Ogre("Shrek", 50, 4);
		Hero hero = new Hero("Jonathan", 105, 4);
		hero.setFaction("Scioa'Tael");
		for (int i = 0; i < 5; i++) {
			while (monsters[i].isDead == false && hero.isDead == false) {
				hero.attack(monsters[i]);
				monsters[i].attack(hero);
				hero.checkDeath();
				monsters[i].checkDeath();
			}
		if (hero.isDead)
		{
			System.out.printf("The hero is dead! He was killed by %s.\n", monsters[i].name);
		}
		else {System.out.printf("The hero has slain %s, the vile %s.\n", monsters[i].name, monsters[i].name);
		//monsters[i].getClass()
		}
		}
		if(!(hero.isDead)){
			System.out.printf("The hero is successful! He has survived with %f hitpoints.\n", hero.health);
		}
	}
}

class Rat extends LivingThing {
	private int whisker_length;

	public Rat(String name, double health, double attackPower, int whisker_length) {
		super(name, health, attackPower);
		this.whisker_length = whisker_length;
	}

	public void attack(LivingThing target) {
		Random r = new Random();
		double d = r.nextDouble();
		target.takeDamage(this.attackPower * d);
	}
}

class Goblin extends LivingThing {
	public Goblin(String name, double health, double attackPower) {
		super(name, health, attackPower);
	}
}

class Ogre extends LivingThing {
	public Ogre(String name, double health, double attackPower) {
		super(name, health, attackPower);

	}

	public void attack(LivingThing target) {
		target.takeDamage(this.attackPower * 1.2);
	} 
}

class Hero extends LivingThing {
	String faction;

	public void attack(LivingThing target) {
		target.takeDamage(this.attackPower * 2);
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public Hero(String name, double health, double attackPower) {
		super(name, health, attackPower);
	}
}