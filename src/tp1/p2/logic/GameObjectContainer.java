package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean removeDead() {
		boolean ok = false;
		GameObject obj;
		for(int i = 0; i < gameObjects.size(); i++) {
			obj = gameObjects.get(i);
			if(!obj.isAlive()) {
				obj.onExit();
				gameObjects.remove(obj);
				ok = true;
			}
		}
		return ok;
	}

	// TODO add your code here
	public boolean addItem(GameObject item) {
		return gameObjects.add(item);
	}
	
	public GameItem get(int col, int row) {
		for(GameObject obj : gameObjects) {
			if(!obj.catchObject()&& obj.isInPosition(col, row)) return obj;
		}
		return null;
	}
	
	public void update() {
		GameObject obj;
		for(int i = 0; i < gameObjects.size(); i++) {
			obj = gameObjects.get(i);
			if(obj.isAlive()) obj.update();
		}
	}
	
	public boolean tryToCatchObject(int col, int row) {
		for(GameObject obj : gameObjects) {
			if(obj.getCol() == col && obj.getRow() == row && obj.catchObject()) {
				gameObjects.remove(obj);
				return true;
			}
		}
		return false;
	}
	
}
