package com.wizardtdshooter.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.wizardtdshooter.model.Bullet;
import com.wizardtdshooter.model.GameObject;
import com.wizardtdshooter.model.ID;
import com.wizardtdshooter.view.Camera;
import com.wizardtdshooter.view.Window;

public class MouseInput extends MouseAdapter {

	private Handler handler;
	private Camera camera;
	private SpriteSheet ss;
	private Audio audio;

	public MouseInput(Handler handler, Camera camera, SpriteSheet ss) {
		this.handler = handler;
		this.camera = camera;
		this.ss = ss;
		this.audio = new Audio("magic-chime-01.wav");
	}

	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (Window.ammo > 0) {
					handler.addObject(
							new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, ss, mx, my));
					Window.ammo -= 1;

					// Play magic effect
					audio.play();
				}
			}
		}
	}
	
}


