package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wizardtdshooter.controller.Handler;
import com.wizardtdshooter.controller.SpriteSheet;

public class Enemy extends GameObject {

	private Handler handler;
	private BufferedImage enemy_image;
	private int persuitTimer;
	private int hp;
	private boolean isMoving;

	public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.persuitTimer = 0;
		this.hp = 100;
		this.handler = handler;
		this.isMoving = false;
		this.enemy_image = ss.grabImage(4, 1, 32, 32);
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;

		if (!isMoving) {
			this.persuitTimer++;
		}
		for (int i = 0; i < this.handler.object.size(); i++) {
			GameObject tempObject = this.handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (this.getBoundsBig().intersects(tempObject.getBounds())) {
					this.turnBack();
					isMoving = false;
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
				if (this.isMoving) {
					this.persuitPlayer(tempObject);
				}
			}
		}

		if (this.hp <= 0) {
			this.handler.removeObject(this);
		}
		// end hp if
		if (this.persuitTimer >= 20) {
			this.isMoving = true;
			this.persuitTimer = 0;
		}
	}

	private void turnBack() {
		this.velX *= -1;
		this.velY *= -1;
	}

	private void persuitPlayer(GameObject player) {
		if(this.x - player.x  < 400 && this.y - player.y < 400) {
			if (this.x > player.getX()) {
				this.velX = -1;

			}
			if (this.x < player.getX()) {
				this.velX = 1;

			}
			if (this.y > player.getY()) {
				this.velY = -1;

			}
			if (this.y < player.getY()) {
				this.velY = 1;

			}
		} else {
			this.velX = 0;
			this.velY = 0;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.enemy_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public Rectangle getBoundsBig() {
		return new Rectangle(x - 16, y - 16, 64, 64);
	}
}
