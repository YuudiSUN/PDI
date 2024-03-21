package utils.entities;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import status.CharacterStatus;

public class CharacterManager {
    private List<CharacterStatus> characters;
    private int mapWidth;
    private int mapHeight;
    private Random random = new Random();

    public CharacterManager(List<CharacterStatus> characters, int mapWidth, int mapHeight) {
        this.characters = characters;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    // 移动所有角色
    public void moveCharacters() {
        for (CharacterStatus character : characters) {
            moveCharacter(character);
        }
    }

    // 移动单个角色
    private void moveCharacter(CharacterStatus character) {
        int currentX = character.getPosition().x;
        int currentY = character.getPosition().y;
        int newX = currentX + random.nextInt(3) - 1; // Generate -1, 0, or 1
        int newY = currentY + random.nextInt(3) - 1;
        newX = Math.max(0, Math.min(newX, mapWidth - 1)); // Constrain to map boundaries
        newY = Math.max(0, Math.min(newY, mapHeight - 1));

        character.setPosition(new Point(newX, newY)); // 更新角色位置
    }

    // 添加新角色
    public void addCharacter(CharacterStatus character) {
        characters.add(character);
    }

    // 删除角色
    public void removeCharacter(CharacterStatus character) {
        characters.remove(character);
    }

    // 获取所有角色
    public List<CharacterStatus> getCharacters() {
        return characters;
    }
}
