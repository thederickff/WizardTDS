package com.wizardtdshooter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.wizardtdshooter.controller.BufferedImageLoader;
import com.wizardtdshooter.controller.Handler;
import com.wizardtdshooter.controller.KeyInput;
import com.wizardtdshooter.model.Block;
import com.wizardtdshooter.model.ID;
import com.wizardtdshooter.model.Wizard;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Timer timer;
	private Handler handler;
	private BufferedImage level = null;
	private Camera camera;

	public Game() {
		////////////////////////
		handler = new Handler();
		camera = new Camera(0, 0);

		BufferedImageLoader loader = new BufferedImageLoader();
		this.level = loader.loadImage("/wizard_level.png");

		loadLevel(level);
		/////////////////////////
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.addKeyListener(new KeyInput(handler));
		this.start();
	}

	private void start() {
		isRunning = true;
		timer = new Timer(10, this);
		timer.start();
	}

	private void stop() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isRunning) {
			tick();
			repaint();
		} else {
			stop();
		}
	}

	public void tick() {
		
		// Update Camera
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		handler.tick();
	}

	public void paint(Graphics g) {
		osSupport();
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.red.darker().darker());
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		////////////////////////////////
		g2.translate(-camera.getX(), -camera.getY());
		
		handler.render(g);
		
		g2.translate(camera.getX(), camera.getY());
		////////////////////////////////
		g.dispose();
	}

	// Loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				// int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (blue == 255) {
					handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler));
				}
				if (red == 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block));
				}

			}
		}
	}

	private void osSupport() {
		if (Window.operatingSystem.equals("Linux")) {
			Toolkit.getDefaultToolkit().sync();
		}
	}
}
