package com.wizardtdshooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.wizardtdshooter.controller.Handler;

public class Enemy extends GameObject {

	private Handler handler;
	private Random r;
	private int choose;
	private int hp;

	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.choose = 0;
		this.hp = 100;
		this.handler = handler;
		this.r = new Random();
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;

		choose = r.nextInt(10);

		for (int i = 0; i < this.handler.object.size(); i++) {
			GameObject tempObject = this.handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (this.getBoundsBig().intersects(tempObject.getBounds())) {
					this.x += (velX * 2) * -1;
					this.y += (velY * 2) * -1;
					this.velX = 0;
					this.velY = 0;
					// this.x += velX * -1;
					// this.y += velY * -1;
				} else if (choose == 0) {
					this.velX = (r.nextInt(4 - -4) + -4);
					this.velY = (r.nextInt(4 - -4) + -4);
				}
			}

			if (tempObject.getId() == ID.Bullet) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.hp -= 50;
					this.handler.removeObject(tempObject);
				}
			}

			if (tempObject.getId() == ID.Player) {
				if (this.getBounds().intersects(tempObject.getBounds())) {

				}
			}
		}

		if (this.hp <= 0) {
			this.handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA.darker());
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public Rectangle getBoundsBig() {
		return new Rectangle(x - 16, y - 16, 64, 64);
	}

}
