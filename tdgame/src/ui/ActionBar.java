package ui;

import static main.GameStates.*;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helpers.Constants;
import helpers.Constants.Towers;
import objects.Tower;
import scenes.Menu;
import scenes.Play;

public class ActionBar extends Bar
{
	private Play play;
	private Buttons bMenu, bPause;
	private Buttons sellTower, upgradeTower;
	private Buttons[] towerButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	private DecimalFormat formatter;
	private boolean showTowerCost;
	private int towerCostType;
	private int bitcoin = Constants.Bitcoin.getInitialBudget();
	private int lives = 100;

	public ActionBar(int x, int y, int width, int height, Play play) 
	{
		super(x, y, width, height);
		this.play = play;
		formatter = new DecimalFormat("0.0");
		initButtons();
	}

	public void initButtons() 
	{
		bMenu = new Buttons("Menu", 8, 520, 125, 50);
		bPause = new Buttons("Pause", 8, 580, 125, 50);
		
		towerButtons = new Buttons[6];
		int width = 50;
		int height = 50;
		int xOffset = width / 4 + width;
		
		for (int i = 0; i < towerButtons.length; i++)
		{
			towerButtons[i] = new Buttons("", 150 + xOffset * i, 520, width, height, i);
			sellTower = new Buttons("Sell", 750, 597, 70, 20);
			upgradeTower = new Buttons("Upgrade", 845, 597, 70, 20);
		}
	}

	public void draw(Graphics g) 
	{
		g.setColor(new Color(133, 50, 168));
		g.fillRect(x, y, width, height);
		drawButtons(g);
		drawDisplayedTower(g);
		drawWaveInfo(g);
		g.setColor(Color.black);
		g.drawString("Lives: " + lives, 530, 580);
	}

	public void drawWaveInfo(Graphics g)
	{
		g.setFont(Menu.thaleah);
		g.setColor(Color.black);
		drawEnemiesLeft(g);
		drawWaveTimer(g);
		drawWavesLeft(g);
		drawBitcoinBalance(g);
		if (showTowerCost)
		{
			drawTowerCost(g);
		}
	}
	
	public void drawTowerCost(Graphics g) 
	{
		g.setColor(Color.gray);
		g.fillRect(330, 580, 180, 50);
		g.setColor(Color.black);
		g.drawRect(330, 580, 180, 50);
		
		g.drawString("" + getTowerCostName() + " Cost: ", 335, 600);
		g.drawString(getTowerCost() + " bitcoins", 335, 620);
		
		if (towerCannotAfford())
		{
			g.setColor(Color.red);
			g.drawString("Can't Afford", 150, 620);
		}
	}

	public void drawBitcoinBalance(Graphics g) 
	{
		g.drawString("Bitcoin: " + bitcoin, 150, 600);
	}

	public void drawWavesLeft(Graphics g)
	{
		int current = play.getWaveManager().getWaveIndex();
		int size = play.getWaveManager().getWaves().size();
		g.drawString("Wave " + current + " / " + size, 530, 540);
	}
	
	public void drawEnemiesLeft(Graphics g)
	{
		int remaining = play.getEnemyManager().getAliveEnemies();
		g.drawString("Enemies Left: " + remaining, 530, 560);
	}

	public void drawWaveTimer(Graphics g)
	{
		if (play.getWaveManager().isWaveTimerStarted())
		{
			float timeLeft = play.getWaveManager().getTimeLeft();
			String formattedText = formatter.format(timeLeft);
			g.drawString("Next Wave: " + formattedText, 530, 610);
		}
	}

	public void drawButtons(Graphics g) 
	{
		g.setFont(Menu.thaleah);

		bMenu.draw(g);
		bPause.draw(g);
		
		for (Buttons button : towerButtons)
		{
			g.setColor(Color.gray);
			g.fillRect(button.x, button.y, button.width, button.height);
			g.drawImage(play.getTowerManager().getTowerImages()[button.getID()], button.x, button.y, button.width, button.height, null);			
			drawButtonFeedback(g, button);
		}
	}
	
	public void drawDisplayedTower(Graphics g) 
	{
		if (displayedTower != null)
		{
			g.setColor(Color.gray);
			g.fillRect(715, 525, 230, 100);
			g.setColor(Color.black);
			g.drawRect(715, 525, 230, 100);
			g.setColor(Color.white);
			g.fillRect(725, 535, 50, 50);
			g.setColor(Color.black);
			g.drawRect(725, 535, 50, 50);
			g.drawImage(play.getTowerManager().getTowerImages()[displayedTower.getTowerType()], 725, 535, 50, 50, null);
			g.setFont(Menu.thaleah);
			g.drawString("" + Towers.getName(displayedTower.getTowerType()), 790, 545);
			g.setFont(Menu.thaleahMedium);
			g.drawString("ID: " + displayedTower.getID(), 790, 560);
			g.drawString("Level: " + displayedTower.getLevel(), 790, 570);
			
			drawDisplayedTowerBorder(g);
			drawDisplayedTowerRange(g);
			
			g.setFont(Menu.thaleahSmall);
			
			if (displayedTower.getLevel() < 4 && bitcoin >= getUpgradeAmount(displayedTower))
			{
				upgradeTower.draw(g);
				drawButtonFeedback(g, upgradeTower);
			}
			sellTower.draw(g);
			drawButtonFeedback(g, sellTower);
			if (sellTower.isMouseOver())
			{
				g.setColor(Color.red);
				g.drawString("Sell for: " + getSellAmount(displayedTower) + " bitcoin", 790, 585);
			}
			else if (upgradeTower.isMouseOver() && bitcoin >= getUpgradeAmount(displayedTower))
			{
				g.setColor(Color.cyan);
				g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + " bitcoin", 790, 585);
			}
		}
	}

	public void drawDisplayedTowerBorder(Graphics g) 
	{
		g.setColor(Color.yellow);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), Menu.unit, Menu.unit);
	}

	public void drawDisplayedTowerRange(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawOval(displayedTower.getX() + 16 - (int)(displayedTower.getRange() * 2) / 2, displayedTower.getY() + 16 - (int)(displayedTower.getRange() * 2 ) / 2,				                		          (int)displayedTower.getRange() * 2, (int)displayedTower.getRange() * 2);
	}
	
	public void displayTower(Tower tower) 
	{
		displayedTower = tower;
	}
	
	public void sellTowerClicked()
	{
		int upgradeCost  = (displayedTower.getLevel() - 1) * getUpgradeAmount(displayedTower);
		upgradeCost *= 0.5f;
		play.removeTower(displayedTower);
		bitcoin += helpers.Constants.Towers.getTowerCost(displayedTower.getTowerType()) / 2;
		bitcoin += upgradeCost;
		displayedTower = null;
	}
	
	public void upgradeTowerClicked()
	{
		play.upgradeTower(displayedTower);
		bitcoin -= getUpgradeAmount(displayedTower);	
	}
	
	public boolean towerCannotAfford()
	{
		return getTowerCost() > bitcoin;
	}
	
	public boolean enoughBitcoin(int towerType)
	{
		return bitcoin >= helpers.Constants.Towers.getTowerCost(towerType); 
	}
	
	public void pay(int towerType)
	{
		this.bitcoin -= helpers.Constants.Towers.getTowerCost(towerType);
	}

	public void addBitcoin(int reward)
	{
		this.bitcoin += reward;
	}

	public void togglePause() 
	{
		play.setGamePaused(!play.isGamePaused());
		if (play.isGamePaused())
		{
			bPause.setText("Unpause");
		}
		else
		{
			bPause.setText("Pause");
		}
	}
	
	public void removeLife(int dmg)
	{
		lives-= dmg;
		if (lives <= 0)
		{
			setGameState(GAME_OVER);
		}
	}
	
	public void resetAll() 
	{
		lives = 100;
		towerCostType = 0;
		showTowerCost = false;
		bitcoin = 100;
		selectedTower = null;
		displayedTower = null;
	}
	
	public void mouseClicked(int x, int y) 
	{
		if (bMenu.getBounds().contains(x, y))
		{
			setGameState(MENU);
		}
		else if (bPause.getBounds().contains(x, y))
		{
			togglePause();
		}
		else
		{
			if (displayedTower != null)
			{
				if (sellTower.getBounds().contains(x, y))
				{
					sellTowerClicked();
					return;
				}
				else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getLevel() < 4 && bitcoin >= getUpgradeAmount(displayedTower))
				{
					upgradeTowerClicked();
					return;
				}
			}
		}
		for (Buttons button: towerButtons)
		{
			if (button.getBounds().contains(x, y))
			{
				if (enoughBitcoin(button.getID()))
				{
					selectedTower = new Tower(0, 0, -1, button.getID());
					play.setSelectedTower(selectedTower);
					return;
				}
			}
		}
	}
	
	public void mouseMoved(int x, int y) 
	{
		bMenu.setMouseOver(false);
		bPause.setMouseOver(false);
		showTowerCost = false;
		sellTower.setMouseOver(false);
		upgradeTower.setMouseOver(false);
		
		for (Buttons button : towerButtons)
		{
			button.setMouseOver(false);
		}
		if (bMenu.getBounds().contains(x, y))
		{
			bMenu.setMouseOver(true);
		}
		else if (bPause.getBounds().contains(x, y))
		{
			bPause.setMouseOver(true);
		}
		else 
		{
			if (displayedTower != null)
			{
				if (sellTower.getBounds().contains(x, y))
				{
					sellTower.setMouseOver(true);
					return;
				}
				else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getLevel() < 4)
				{
					upgradeTower.setMouseOver(true);
					return;
				}
			}
			for (Buttons button : towerButtons)
			{
				if (button.getBounds().contains(x, y)) 
				{
					button.setMouseOver(true);
					showTowerCost = true;
					towerCostType = button.getID();
					return;
				}
			}	
		}
	}

	public void mousePressed(int x, int y) 
	{
		if (bMenu.getBounds().contains(x, y))
		{
			bMenu.setMousePressed(true);
		}
		else if (bPause.getBounds().contains(x, y))
		{
			bPause.setMousePressed(true);
		}
		else
		{
			if (displayedTower != null)
			{
				if (sellTower.getBounds().contains(x, y))
				{
					sellTower.setMousePressed(true);
					return;
				}
				else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getLevel() < 4)
				{
					upgradeTower.setMousePressed(true);
					return;
				}
			}
			for (Buttons button : towerButtons)
			{
				if (button.getBounds().contains(x, y)) 
				{
					button.setMousePressed(true);
					return;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) 
	{
		bMenu.resetBooleans();
		bPause.resetBooleans();
		for (Buttons button : towerButtons)
		{
			button.resetBooleans();
		}
		sellTower.resetBooleans();
		upgradeTower.resetBooleans();
	}

	public int getUpgradeAmount(Tower displayedTower)
	{
		return (int)(helpers.Constants.Towers.getTowerCost(displayedTower.getTowerType()) * 0.3f);
	}

	public int getSellAmount(Tower displayedTower) 
	{
		int upgradeCost  = (displayedTower.getLevel() - 1) * getUpgradeAmount(displayedTower);
		upgradeCost *= 0.5f;
		return helpers.Constants.Towers.getTowerCost(displayedTower.getTowerType()) / 2 + upgradeCost;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public int getTowerCost()
	{
		return helpers.Constants.Towers.getTowerCost(towerCostType);
	}

	public String getTowerCostName() 
	{
		return helpers.Constants.Towers.getName(towerCostType);
	}
}
