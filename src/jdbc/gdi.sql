DROP TABLE IF EXISTS BagItem;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Monster;
DROP TABLE IF EXISTS Record;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Profession;
DROP TABLE IF EXISTS Unit;
DROP TABLE IF EXISTS Animal;
DROP TABLE IF EXISTS Animaltype;
DROP TABLE IF EXISTS MapElement;
DROP TABLE IF EXISTS Treasure;
DROP TABLE IF EXISTS Terrain;
DROP TABLE IF EXISTS Prize;
DROP TABLE IF EXISTS Obstacle;
DROP TABLE IF EXISTS Time;

CREATE TABLE Time (
    Time_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Time_timespan VARCHAR(255)
);

CREATE TABLE Unit (
    Unit_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Unit_name VARCHAR(255),
    Unit_HP INT,
    Unit_ATK INT,
    Unit_location VARCHAR(255),
    Time_id INT,
    FOREIGN KEY (Time_id) REFERENCES Time(Time_id)
);

CREATE TABLE Profession (
    Profession_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Profession_name VARCHAR(255),
    Profession_HP INT,
    Profession_ATK INT,
    Profession_ActionPoint INT,
    Profession_weightbearing INT,
    Profession_price DECIMAL(10, 2),
    Profession_characteristics TEXT
);

CREATE TABLE Player (
    Player_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Player_status ENUM('Healthy', 'Poisoned'),
    Player_name VARCHAR(255),
    Player_EnergyPoint INT,
    Player_ActionPoint INT,
    Player_weightbearing INT,
    Player_money DECIMAL(10, 0),
    Profession_id VARCHAR(25),
    FOREIGN KEY (Profession_id) REFERENCES Profession(Profession_id),
	FOREIGN KEY (Player_id) REFERENCES Unit(Unit_id)
);

CREATE TABLE Item (
    Item_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Item_name VARCHAR(255),
    Item_price DECIMAL(10, 2),
    Item_weight DECIMAL(10, 2),
    Item_uses_count INT,
    Item_description TEXT
);

CREATE TABLE BagItem (
    BagItem_id VARCHAR(25) NOT NULL PRIMARY KEY,
    BagItem_Remaining_count INT,
    Item_id VARCHAR(25),
    Player_id VARCHAR(25),
    FOREIGN KEY (Item_id) REFERENCES Item(Item_id),
    FOREIGN KEY (Player_id) REFERENCES Player(Player_id)
);

CREATE TABLE Animaltype (
    Animaltype_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Animaltype_name VARCHAR(255),
    Animaltype_HP INT,
    Animaltype_ATK INT
);

CREATE TABLE Animal (
    Animal_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Animal_hitrate DECIMAL(3, 1) CHECK (Animal_hitrate >= 0 AND Animal_hitrate <= 1),
    Animal_terrain VARCHAR(255),
    Animaltype_id VARCHAR(25),
    FOREIGN KEY (Animaltype_id) REFERENCES Animaltype(Animaltype_id),
	FOREIGN KEY (Animal_id) REFERENCES Unit(Unit_id)
);

CREATE TABLE MapElement (
    Element_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Element_name VARCHAR(255),
    Element_location VARCHAR(255)
);

CREATE TABLE Treasure (
    Treasure_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Treasure_state ENUM('Open', 'Closed') NOT NULL,
	FOREIGN KEY (Treasure_id) REFERENCES MapElement(MapElement_id)
);

CREATE TABLE Record (
    Time_id VARCHAR(25) NOT NULL,
    Treasure_id VARCHAR(25) NOT NULL,
    Time_discovery TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Time_id, Treasure_id),
    FOREIGN KEY (Time_id) REFERENCES Time(Time_id),
    FOREIGN KEY (Treasure_id) REFERENCES Treasure(Treasure_id)
);

CREATE TABLE Monster (
    Monster_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Treasure_id VARCHAR(25),
    FOREIGN KEY (Treasure_id) REFERENCES Treasure(Treasure_id),
    FOREIGN KEY (Monster_id) REFERENCES Unit(Unit_id)
);

CREATE TABLE Terrain (
    Terrain_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Terrain_travespeed INT,
    Terrain_Combat_effectiveness DECIMAL(5, 2),
    Terrain_animal_quantity INT,
    Terrain_prize_quantity INT,
    Animal_id VARCHAR(25),
    FOREIGN KEY (Animal_id) REFERENCES Animal(Animal_id),
	FOREIGN KEY (Terrain_id) REFERENCES MapElement(MapElement_id)
);

CREATE TABLE Prize (
    Prize_id VARCHAR(25) NOT NULL PRIMARY KEY,
    Prize_reward VARCHAR(255),
    FOREIGN KEY (Prize_id) REFERENCES MapElement(MapElement_id)
);

CREATE TABLE Obstacle (
    Obstacle_id VARCHAR(25) NOT NULL PRIMARY KEY,
    FOREIGN KEY (Obstacle_id) REFERENCES MapElement(MapElement_id)
);

-- Inserting data into the Time table
INSERT INTO Time (Time_id, Time_timespan) VALUES
('Time1', '1 hour'),  
('Time2', '2 hours'), 
('Time3', '3 hours'), 
('Time4', '4 hours'), 
('Time5', '5 hours');

-- Inserting data into the Unit table
INSERT INTO Unit (Unit_id, Unit_name, Unit_HP, Unit_ATK, Unit_location, Time_id) VALUES
('Player1', 'leo', 100, 10, '1', 'Time1'),
('Player2', 'Alex', 150, 15, '1', 'Time1'),
('Animal1', 'Fox1', 20, 0, '20', 'Time1'),
('Animal2', 'Fox2', 20, 0, '21', 'Time1'),
('Animal3', 'Bear1', 100, 20, '54', 'Time1'),
('Animal4', 'Lion1', 100, 30, '89', 'Time1'),
('Animal5', 'Snake', 10, 5 , '33', 'Time1'),
('Monster1', 'Demon', 200, 40 ,'66', 'Time1');

-- Inserting data into the Profession table
INSERT INTO Profession (Profession_id, Profession_name, Profession_HP, Profession_ATK, Profession_ActionPoint, Profession_weightbearing, Profession_price, Profession_characteristics) VALUES
('Profession1', 'Explorer', 100, 1, 100, 0, 100, 'None'),
('Profession2', 'Hiker', 100, 1, 100, 100, 100, 'Backpack capacity +100'),
('Profession3', 'Priest', 70, 0.75, 200, 0, 200, 'HP +5/s for surrounding teammates'),
('Profession4', 'Warrior', 150, 0.5, 150, 0, 150, 'Carry your own weapon, damage *2'),
('Profession5', 'Elementalist', 50, 0.75, 175, 0, 175, 'Basic control, allows manipulation of certain environmental elements.');

-- Inserting data into the Player table
INSERT INTO Player (Player_id, Player_status, Player_name, Player_EnergyPoint, Player_ActionPoint, Player_weightbearing, Player_money, Profession_id) VALUES
('Player1', 'Active', 'Player 1', 100, 100, 0, 1000, 'Profession1'),
('Player2', 'Active', 'Player 2', 100, 100, 0, 1000, 'Profession2');

-- Inserting data into the Item table
INSERT INTO Item (Item_id, Item_name, Item_price, Item_weight, Item_uses_count, Item_description) VALUES
('Item1', 'Rope', 50, 10, 5, 'Used for climbing or traversing difficult terrain.'),
('Item2', 'Pickaxe', 75, 15, 10, 'Used for digging or clearing obstacles such as rocks.'),
('Item3', 'Map', 100, 0, 0, 'Provides detailed information about the area for locating and navigating.'),
('Item4', 'Medicine Bag', 80, 5, 10, 'Contains basic medical supplies for treating minor injuries or illnesses.'),
('Item5', 'Food and Water', 100, 3, 15, 'Provides energy and hydration, maintaining the adventurer\'s stamina and health.'),
('Item6', 'Tent', 150, 10, 20, 'Provides temporary shelter, suitable for multi-day adventures.'),
('Item7', 'Weapon', 150, 0, 15, 'For self-defense or combating wild animals.'),
('Item8', 'Communication Device', 120, 0, 10, 'With this, you can call your teammates for help.'),
('Item9', 'Torch', 50, 1, 10, 'Used to ward off wild animals except lions.');

-- Inserting data into the BagItem table
INSERT INTO BagItem (BagItem_id, BagItem_Remaining_count, Item_id, Player_id) VALUES
('BagItem1', 1, 'Item1', 'Player1'),
('BagItem2', 3, 'Item1', 'Player1'),
('BagItem3', 3, 'Item2', 'Player1'),
('BagItem4', 5, 'Item3', 'Player2'),
('BagItem5', 7, 'Item3', 'Player2'),
('BagItem6', 2, 'Item4', 'Player2'),
('BagItem7', 3, 'Item5', 'Player2');

-- Inserting data into the Animaltype table
INSERT INTO Animaltype (Animaltype_id, Animaltype_name, Animaltype_HP, Animaltype_ATK) VALUES
('Animaltype1', 'Fox', 20, 0),
('Animaltype2', 'Wolf', 30, 5),
('Animaltype3', 'Bear', 100, 20),
('Animaltype4', 'Lion', 100, 30),
('Animaltype5', 'Snake', 10, 5);

-- Inserting data into the Animal table
INSERT INTO Animal (Animal_id, Animal_hitrate, Animal_terrain, Animaltype_id) VALUES
('Animal1', 0.9, 'Any', 'Animaltype1'),
('Animal2', 0.9, 'Any', 'Animaltype1'),
('Animal3', 0.8, 'Forest', 'Animaltype3'),
('Animal4', 0.7, 'Forest', 'Animaltype4'),
('Animal5', 0.9, 'Swamp', 'Animaltype5');

-- Inserting data into the MapElement table
INSERT INTO MapElement (Element_id, Element_name, Element_location) VALUES
('Treasure1', 'mimic', '1'),
('Treasure2', 'final', '2'),
('Terrain1', 'plains', '1,2,3,4,5,6,7,8,9,10'),
('Terrain2', 'Mountain', '11,12,13,14,15,16,17'),
('Terrain3', 'Rivers', '18,19,20,21,22,23,24'),
('Terrain4', 'Swamps', '25,26,27,28'),
('Terrain5', 'Forests', '29,30,31,32,33,34,35'),
('Prize1', 'Rope','2'),
('Prize2', 'Weapon','3'),
('Prize3', 'Money','2'),
('Obstacle1','wall1','34'),
('Obstacle2', 'wall2','25'),
('Obstacle3', 'wall3','21');

-- Inserting data into the Treasure table
INSERT INTO Treasure (Treasure_id, Treasure_state) VALUES
('Treasure1', 'Closed'),
('Treasure2', 'Closed');

-- Inserting data into the Record table
INSERT INTO Record (Time_id, Treasure_id, Time_discovery) VALUES
('Time1', 'Treasure1', NOW()),
('Time2', 'Treasure2', NOW());

-- Inserting data into the Monster table
INSERT INTO Monster (Monster_id, Treasure_id) VALUES
('Monster1', 'Treasure1');

-- Inserting data into the Terrain table
INSERT INTO Terrain (Terrain_id, Terrain_travespeed, Terrain_Combat_effectiveness, Terrain_animal_quantity, Terrain_prize_quantity, Animal_id) VALUES
('Terrain1', 1, 1, 0, 0, 'Animal1'),
('Terrain2', 0.75, 1, 0.5, 0.5, 'Animal2'),
('Terrain3', 0.5, 1, 1, 0.5, 'Animal3'),
('Terrain4', 0.2, 0.2, 1.5, 2, 'Animal4'),
('Terrain5', 1, 1, 2, 2, 'Animal5');

-- Inserting data into the Prize table
INSERT INTO Prize (Prize_id, Prize_reward) VALUES
('Prize1', 'Rope'),
('Prize2', 'Weapon'),
('Prize3', 'Money +200');

-- Inserting data into the Obstacle table
INSERT INTO Obstacle (Obstacle_id) VALUES
('Obstacle1'),
('Obstacle2'),
('Obstacle3');


