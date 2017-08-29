package com.wizardtdshooter.view;

import com.wizardtdshooter.model.GameObject;

public class Camera {

	private float x;
	private float y;

	public Camera() {
		this.x = 0;
		this.y = 0;
	}

	public void tick(GameObject object) {
		this.x += ((object.getX() - this.x) - Window.WIDTH / 2) * 0.05f;
		this.y += ((object.getY() - this.y) - Window.HEIGHT / 2) * 0.05f;

		if (this.x < 0) {
			this.x = 0;
		}
		if (this.y < 0) {
			this.y = 0;
		}
		if (this.x > Window.WIDTH + 48) {
			this.x = Window.WIDTH + 48;
		}
		if (this.y > Window.HEIGHT + 52) {
			this.y = Window.HEIGHT + 52;
		}

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
