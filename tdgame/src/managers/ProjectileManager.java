package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.SaveLoader;
import main.GamePanel;
import objects.Projectile;
import objects.Tower;
import scenes.Menu;
import scenes.Play;
import static helpers.Constants.Towers.*;
import static helpers.Constants.Projectiles.*;

public class ProjectileManager
{
	private Play play;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private BufferedImage[] projectileImages;
	private BufferedImage[] explosionImages;
	private int projectileID = 0;
	
	public ProjectileManager(Play play) 
	{
		this.play = play;
		loadProjectileImages();
	}
	
	public void loadProjectileImages() 
	{
		BufferedImage spriteSheet = SaveLoader.getSpriteSheet();
		projectileImages = new BufferedImage[5];
		projectileImages[0] = spriteSheet.getSubimage(Menu.unit * 2, Menu.unit * 6, Menu.unit, Menu.unit);
		projectileImages[1] = spriteSheet.getSubimage(Menu.unit * 9, Menu.unit * 6, Menu.unit, Menu.unit);
		projectileImages[2] = spriteSheet.getSubimage(Menu.unit * 0, Menu.unit * 7, Menu.unit, Menu.unit);
		projectileImages[3] = spriteSheet.getSubimage(Menu.unit * 1, Menu.unit * 7, Menu.unit, Menu.unit);
		projectileImages[4] = spriteSheet.getSubimage(Menu.unit * 2, Menu.unit * 7, Menu.unit, Menu.unit);
		importExplosion(spriteSheet);
	}

	public void newProjectile(Tower tower, Enemy enemy)
	{
		int type = getProjectileType(tower);	
		int xDist = (int)(tower.getX() - enemy.getX());
		int yDist = (int)(tower.getY() - enemy.getY());
		int totalDist = Math.abs(xDist) + Math.abs(yDist);

		float xPer = (float) Math.abs(xDist) / totalDist;
		float xSpeed = xPer * helpers.Constants.Projectiles.getSpeed(type);
		float ySpeed = helpers.Constants.Projectiles.getSpeed(type) - xSpeed;
		
		if (tower.getX() > enemy.getX())
		{
			xSpeed *= -1;
		}
		if (tower.getY() > enemy.getY())
		{
			ySpeed *= -1;
		}
		
		float rotate = 0;
		
		if (type != LIGHTNING)
		{
			float arcValue = (float)Math.atan(yDist / (float) xDist);
			rotate = (float)Math.toDegrees(arcValue);
		}
		if (xDist < 0)
		{
			rotate += 180;
		}
		projectiles.add(new Projectile(tower.getX() + 16, tower.getY() + 16, xSpeed, ySpeed, tower.getDmg(), rotate, projectileID++, type));
	}

	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		for (Projectile p : projectiles)
		{
			if (p.isActive())
			{
				if (p.getProjectileType() != LIGHTNING)
				{
					g2d.translate(p.getPos().x, p.getPos().y);
					g2d.rotate(Math.toRadians(p.getRotation()));
					g2d.drawImage(projectileImages[p.getProjectileType()], -16, -16	, null);
					g2d.rotate(-Math.toRadians(p.getRotation()));
					g2d.translate(-p.getPos().x, -p.getPos().y);
				}
				else
				{
					g2d.drawImage(projectileImages[p.getProjectileType()], (int)p.getPos().x - 16, (int)p.getPos().y - 16, null);
				}
			}
			drawExplosions(g2d);
		}
	}
	
	public void drawExplosions(Graphics2D g2d) 
	{
		for (Explosion explosion : explosions)
		{
			if (explosion.getExplosionIndex() < 6)
			{
				g2d.drawImage(explosionImages[explosion.getExplosionIndex()], (int)explosion.getPos().x - 16, (int)explosion.getPos().y - 16, null);
			}	
		}
	}
	
	public void importExplosion(BufferedImage spriteSheet) 
	{
		explosionImages = new BufferedImage[6];
		for (int i = 0; i < explosionImages.length; i++)
		{
			explosionImages[i] = spriteSheet.getSubimage(i * Menu.unit + Menu.unit * 3, 6 * Menu.unit, Menu.unit, Menu.unit);
		}
	}
	
	public void update()
	{
		for (Projectile p : projectiles)
		{
			if (p.isActive())	
			{
				p.move();
				if (isProjHittingEnemy(p))
				{
					p.setActive(false);
					if (p.getProjectileType() == FIREBALL)
					{
						explosions.add(new Explosion(p.getPos()));
						explodeOnEnemies(p);
					}
				}
				else if (isProjOutOfBounds(p))
				{
					p.setActive(false);
				}
			}
		}
		for (Explosion explosion : explosions)
		{
			if (explosion.getExplosionIndex() < 6)
			{
				explosion.update();
			}
		}
	}
	
	private boolean isProjOutOfBounds(Projectile p)
	{
		if (p.getPos().x >= 0)
		{
			if (p.getPos().x <= GamePanel.screenWidth)
			{
				if (p.getPos().y >= 0)
				{
					if (p.getPos().y <= GamePanel.screenHeight)
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	public void explodeOnEnemies(Projectile p) 
	{
		for (Enemy enemy : play.getEnemyManager().getEnemies()) 
		{
			if (enemy.isAlive()) 
			{
				float radius = 40.0f;
				float xDist = Math.abs(p.getPos().x - enemy.getX());
				float yDist = Math.abs(p.getPos().y - enemy.getY());
				float realDist = (float) Math.hypot(xDist, yDist);

				if (realDist <= radius)	
				{
					enemy.hurt(p.getDmg());
				}
			}
		}
	}

	public boolean isProjHittingEnemy(Projectile p)
	{
		for (Enemy enemy : play.getEnemyManager().getEnemies()) 
		{
			if (enemy.getBounds().contains(p.getPos())) 
			{
				enemy.hurt(p.getDmg());	
				if (p.getProjectileType() == ARROW)
				{
					enemy.slow();
				}
				return true;
			}	
		}
		return false;
	}

	public int getProjectileType(Tower tower)
	{
		switch (tower.getTowerType())
		{
		case FIREWALL:
			return FIREBALL;
		case ANTI_VIRUS:
			return LASERS;
		case ROBOLMER:
			return JAVACUP;
		case TESLA:
			return LIGHTNING;
		case VPN_KNIGHT:
			return ARROW;
		}
		return 0;
	}

	public class Explosion
	{
		private Point2D.Float pos;
		private int explosionTick = 0, explosionIndex = 0;
		
		public Explosion(Point2D.Float pos)
		{
			this.pos = pos;
		}
		
		public void update()
		{
			explosionTick++;
			if (explosionTick >= 10)
			{
				explosionTick = 0;
				explosionIndex++;
			}
		}
		
		public int getExplosionIndex()
		{
			return explosionIndex;
		}
		
		public Point2D.Float getPos()
		{
			return pos;
		}
	}
	
	public void resetProj()
	{
		projectiles.clear();
		explosions.clear();
		projectileID = 0;
	}
}
