package com.wizardtdshooter.view;

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
import com.wizardtdshooter.controller.MouseInput;
import com.wizardtdshooter.controller.SpriteSheet;
import com.wizardtdshooter.model.Block;
import com.wizardtdshooter.model.Crate;
import com.wizardtdshooter.model.Enemy;
import com.wizardtdshooter.model.ID;
import com.wizardtdshooter.model.Wizard;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Timer timer;
	private Handler handler;
	private SpriteSheet ss;
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	private Camera camera;
	
	
	public Game() {
		////////////////////////
		handler = new Handler();
		camera = new Camera();

		BufferedImageLoader loader = new BufferedImageLoader();
		this.level = loader.loadImage("/wizard_level.png");
		this.sprite_sheet = loader.loadImage("/wizard_images.png");
		this.ss = new SpriteSheet(sprite_sheet);
		this.floor = ss.grabImage(4, 2, 32, 32);
		loadLevel(level);
		/////////////////////////
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, ss));
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
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		handler.tick();
	}

	public void paint(Graphics g) {
		osSupport();
		Graphics2D g2 = (Graphics2D) g;

		////////////////////////////////
		g2.translate(-camera.getX(), -camera.getY());
		
		for (int xx = 0; xx < 30*72; xx+= 32){
			for (int yy = 0; yy < 30*72;yy+= 32) {
				g.drawImage(this.floor, xx, yy, null);
			}
		}
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
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
				}
				if (green == 255 && blue != 255) {
					handler.addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, handler, ss));
				}
				if (blue == 255 && green != 255) {
					handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler, ss));
				}
				if (blue == 255 && green == 255) {
					handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
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
