package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.wizardtdshooter.controller.SpriteSheet;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected float velX = 0;
	protected float velY = 0;
	protected ID id;
	protected SpriteSheet ss;

	public GameObject(int x, int y, ID id, SpriteSheet ss) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.ss = ss;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}
