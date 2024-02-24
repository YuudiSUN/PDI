package gui;

import game.Item;
import game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InventoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;

    private Player player;

    // 构造方法，接收玩家实例作为参数
    public InventoryPanel(Player player) {
        this.player = player;
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new GridLayout(1, 2));

        // 创建显示物品列表的 JList
        List<Item> inventory = player.getInventory(); // 假设玩家的物品列表在 Player 类中定义
        DefaultListModel<Item> listModel = new DefaultListModel<>();
        for (Item item : inventory) {
            listModel.addElement(item);
        }
        JList<Item> itemList = new JList<>(listModel);

        // 创建滚动面板，以便能够滚动查看所有物品
        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane);
    }

    // 其他可能需要的方法和逻辑
}
