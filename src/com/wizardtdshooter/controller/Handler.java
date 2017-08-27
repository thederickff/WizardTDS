package com.wizardtdshooter.controller;

import java.awt.Graphics;
import java.util.LinkedList;

import com.wizardtdshooter.model.GameObject;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject gameObject) {
		this.object.add(gameObject);
	}
	public void removeObject(GameObject gameObject) {
		this.object.remove(gameObject);
	}
}
