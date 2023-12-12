DROP TABLE IF EXISTS tbl_joueur;
DROP TABLE IF EXISTS tbl_club;
DROP TABLE IF EXISTS tbl_poste;
DROP TABLE IF EXISTS  tbl_selection;
DROP TABLE IF EXISTS  tbl_blog;
DROP TABLE IF EXISTS  tbl_titre;

CREATE TABLE tbl_club (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    pays VARCHAR(255)
);

INSERT INTO tbl_club VALUES (1,'Paris SG','France'),(2,'Milan AC','Italie'),(3,'Bayer Munich','Allemagne'),(4,'Juventus','Italie'),(5,'Inter Milan','Italie'),(6,'Barcelone','Espagne'),(7,'Real Madrid','Espagne'),(8,'Dortmund','Allemagne'),(9,'Manchester United','Angleterre'),(10,'Liverpool','Angleterre'),(11,'FC Arsenal','Angleterre'),(12,'Newcastle United','Angleterre'),(13,'Marseille','France'),(14,'Lyon','France'),(15,'Lazio','Italie'),(16,'Anderlecht','Belgique'),(17,'FC Nantes','France'),(19,'FC Seville','Espagne'),(20,'Chelsea','Angleterre'),(21,'AS Rome','Italie'),(22,'AS Monaco','France'),(23,'FC Porto','Portugal'),(24,'Ajax Amsterdam','Pays-Bas'),(25,'Benfica','Portugal'),(26,'PSV Eindhoven','Pays-Bas');


CREATE TABLE tbl_poste (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

INSERT INTO tbl_poste (description,name)VALUES ('Avant-centre, Attaquant droit ou gauche, second attaquant, ailier droit, ailier gauche','Attaquant'),('Milieu offensif, milieu droit, milieux gauche, milieu defensif, milieux relayeur','Milieu'),('Defenseur droit, defenseur gauche, Defenseur central, Libero','Defenseur'),('Gardien de but','Gardien'),('Entraineur','Entraineur');


CREATE TABLE tbl_selection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    confederation VARCHAR(255)
);

INSERT INTO tbl_selection (id,name,confederation)VALUES (1,'Cameroun','CAF'),(2,'Bresil','CONMEBOL'),(3,'France','UEFA'),(4,'Allemagne','UEFA'),(5,'Italie','UEFA'),(6,'Nigeria','CAF'),(7,'Argentine','CONMEBOL'),(8,'Espagne','UEFA'),(9,'Pays-Bas','UEFA'),(10,'Liberia','CAF'),(11,'Republique Tcheque','UEFA'),(12,'Finlande','UEFA'),(13,'Angleterre','UEFA'),(14,'Côte d''Ivoire','CAF'),(15,'Egypte','CAF'),(16,'Maroc','CAF'),(17,'Suede','UEFA'),(18,'Suisse','UEFA'),(19,'Colombie','CONMEBOL'),(20,'Ghana','CAF'),(21,'Bulgarie','UEFA'),(22,'Croatie','UEFA'),(23,'Yougoslavie','UEFA'),(24,'Portugal','UEFA'),(25,'Ukraine','UEFA');


CREATE TABLE tbl_joueur ( 
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL,
  prenom varchar(255) DEFAULT NULL,
  date_naissance date DEFAULT NULL,
  poste_id int DEFAULT NULL,
  nbr_point_obtenu int DEFAULT NULL,
  classement int DEFAULT NULL,
  image_url varchar(255) DEFAULT NULL,
  selection_id int DEFAULT NULL,
  annee_Recompense varchar(255) DEFAULT NULL,
  club_id int DEFAULT NULL,
  description varchar(1000) DEFAULT NULL,
  surnom varchar(255) DEFAULT NULL,
  KEY `FK2x94s8fj9cfkanreals85ilmy` (club_id),
  KEY `FKkogmy3nvqgggfdk639kw32nsk` (poste_id),
  KEY `FK4pkcy6o3m4r21d93i354q5pav` (selection_id),
  CONSTRAINT `FK2x94s8fj9cfkanreals85ilmy` FOREIGN KEY (club_id) REFERENCES tbl_club (id),
  CONSTRAINT `FK4pkcy6o3m4r21d93i354q5pav` FOREIGN KEY (selection_id) REFERENCES tbl_selection (id),
  CONSTRAINT `FKkogmy3nvqgggfdk639kw32nsk` FOREIGN KEY (poste_id) REFERENCES tbl_poste (id)
);

CREATE TABLE tbl_blog (
  id int NOT NULL AUTO_INCREMENT,
  author varchar(255) DEFAULT NULL,
  content varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  image_url varchar(255) DEFAULT NULL,
  is_visible bit(1) DEFAULT NULL,
  date_published datetime(6) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  url_handle varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
  );

/*INSERT INTO tbl_blog VALUES (1,'Jules MESSOLO','egeqrgergergregGEFVEHR GRHYTJIUNLMYUTRTYZSZHRBZ FBTJYJ','le staffBoxe est en train de monter un site internet...','assets/images/products/staffboxe.png',null,'2023-09-30 02:00:00.000000','Pblog sur elle Phenomeno','this is a url Handle'),
(2,'Jules MESSOLO','Ronaldo Luís Après cinq saisons avec le club madrilène, il rejoint le Milan AC. En 2009, il retourne dans son pays natal et il est transféré aux Corinthians, club dans lequel il joue ses derniers matchs. Il annonce sa retraite le 14 février 2011.','le staffBoxe est en train de monter un site internet...','assets/images/products/staffboxe.png',_binary,'2023-09-30 02:00:00.000000','blogII sur elle Phenomeno','this is a url Handle'),
(3,'Jules MESSOLO','Cela le place haut, très haut dans la hiérarchie des meilleurs footballeurs français de l\'histoire, 'lui qui est devenu en mars 2022 le meilleur marqueur français de tous les temps avec plus de 400 buts, devant Thierry Henry',"KB9 est-il le meilleur ballon d\'or Français de tous les temps",'assets/images/joueurs/MatthiasSammer.jpg',_binary,'2023-10-02 02:00:00.000000',"KB9 est-il le meilleur ballon d\'or Français de tous les temps","KB9 Ballon d\'or chiffres"),
(4,'Jules MESSOLO','fufhurhuir','Et si vous devez en retenir trois?','assets/images/joueurs/MatthiasSammer.jpg',null,'2023-10-02 02:00:00.000000',"Les GOAT\'s du Bresil",'GOATs du bresil');
*/

INSERT INTO tbl_joueur VALUES 
(1,'Matthaus','Lothar','1961-03-21',3,137,1,'assets/images/joueurs/LotharMatthaus.jpg',3,'1990',2,NULL,NULL),
(2,'Papin','Jean Pierre','1963-05-11',1,141,1,'assets/images/joueurs/JeanPierrePapin.jpg',5,'1991',6,NULL,NULL),
(3,'Van Basten','Marco','1964-10-31',1,98,1,'assets/images/joueurs/MarcoVanBasten.jpg',4,'1992',5,NULL,NULL),
(4,'BAGGIO','Roberto','1967-02-18',1,142,1,'assets/images/joueurs/RobertoBaggio.jpg',5,'1993',4,'Roberto Baggio, né le 18 février 1967 à Caldogno dans la province de Vicence en Vénétie, est un footballeur international italien, évoluant au poste de milieu offensif ou attaquant des années 1980 à 2000, reconverti dirigeant.','Raffaelo'),
(5,'STOITCHKOV','Hristo','1966-02-08',1,210,1,'assets/images/joueurs/HristoStoichkov.jpg',21,'1994',6,NULL,NULL),
(6,'WEAH','George','1966-10-01',1,144,1,'assets/images/joueurs/GeorgeWeah.jpg',10,'1995',1,NULL,NULL),
(7,'SAMMER','Matthias','1967-09-05',3,144,1,'assets/images/joueurs/MatthiasSammer.jpg',4,'1996',8,NULL,NULL),
(8,'RONALDO','Luis Nazario','1976-09-18',1,222,1,'assets/images/joueurs/LuisNazarioRonaldo.jpg',2,'1997',5,NULL,NULL),
(9,'ZIDANE','Zinedine','1972-06-23',2,244,1,'assets/images/joueurs/ZinedineZidane.jpg',3,'1998',4,NULL,NULL),
(10,'RIVALDO','Vitor Borba','1972-04-19',2,219,1,'assets/images/joueurs/BarbosaRivaldo.jpg',2,'1999',6,NULL,NULL),
(11,'FIGO','Luis','1972-11-04',1,197,1,'assets/images/joueurs/LuisFigo.jpg',24,'2000',7,NULL,NULL),
(12,'OWEN','Michael James','1979-12-14',1,176,1,'assets/images/joueurs/MickaelOwen2001.jpg',13,'2001',10,NULL,NULL),
(13,'RONALDO','Luis Nazario','1976-09-22',1,169,1,'assets/images/joueurs/LuisNazarioRonaldo2.jpg',2,'2002',7,NULL,NULL),
(14,'Van Basten','Marco','1963-05-11',1,119,1,'assets/images/joueurs/MarcoVanBasten1989.jpg',5,'1989',2,NULL,NULL),
(15,'KAKA','Ricardo ','1982-04-22',2,445,1,'assets/images/joueurs/RicardoKaka.jpg',2,'2007',2,NULL,NULL),
(16,'Ronaldo','Cristiano','1966-10-01',1,446,1,'assets/images/joueurs/CristianoRonaldo2008.jpg',6,'2008',9,NULL,NULL),
(17,'Messi','Lionel','1966-10-01',1,473,1,'assets/images/joueurs/LionelMessi2009.jpg',4,'2009',7,NULL,NULL),
(18,'NEDVED','Pavel','1972-08-30',2,190,1,'assets/images/joueurs/PavelNedved.jpg',11,'2003',4,NULL,NULL),
(19,'CHEVTCHENKO','Andriy','1976-09-29',1,175,1,'assets/images/joueurs/AndriyChevtchenko.jpg',25,'2004',2,NULL,NULL),
(20,'RONALDINHO','Gaucho','1980-03-21',1,225,1,'assets/images/joueurs/Ronaldinho.jpg',2,'2005',6,NULL,NULL),
(21,'CANNAVARO','Fabio','1973-09-13',3,173,1,'assets/images/joueurs/FabioCannavaro.jpg',5,'2006',4,NULL,NULL),
(22,'Ronaldo','Cristiano','1964-10-31',1,233,1,'assets/images/joueurs/CristianoRonaldo2009.jpg',3,'2009',2,NULL,NULL),
(23,'Hernandez','Xavier','1964-10-31',2,170,3,'assets/images/joueurs/HernandezXavi.jpg',2,'2009',3,NULL,NULL),
(24,'Messi','Lionel','1968-12-01',1,281,1,'assets/images/joueurs/LionelMessi2008.jpg',1,'2008',7,NULL,NULL),
(25,'Torres','Fernando','1968-12-01',1,179,3,'assets/images/joueurs/FernandoTorres2008.jpg',4,'2008',6,NULL,NULL),
(26,'RONALDO','Cristiano','1985-02-05',1,277,2,'assets/images/joueurs/CristianoRonaldo2007.jpg',24,'2007',9,NULL,NULL),
(27,'Drogba','Didier','1978-06-06',1,106,4,'assets/images/joueurs/DidierDrogba2007.jpg',12,'2004',3,NULL,NULL),
(28,'MESSI','Lionel','1987-06-24',1,255,3,'assets/images/joueurs/LionelMessi2007.jpg',7,'2007',6,NULL,NULL),
(29,'BUFFON','Gianluigi','1978-01-28',4,124,2,'assets/images/joueurs/GianluigiBuffon2006.jpg',5,'2006',4,NULL,NULL),
(30,'HENRY','Thierry','1977-08-17',1,121,3,'assets/images/joueurs/ThierryHenry2006.jpg',3,'2006',11,NULL,NULL),
(31,'LAMPARD','Franck','1978-06-20',2,148,2,'assets/images/joueurs/FranckLampard2005.jpg',13,'2005',20,NULL,NULL),
(32,'GERRARD','Steven','1980-05-30',2,142,3,'assets/images/joueurs/StevenGerrard2005.jpg',13,'2005',10,NULL,NULL),
(33,'DECO','Anderson Luis','1977-08-27',2,139,2,'assets/images/joueurs/AndersonDeco2004.jpg',24,'2004',23,NULL,NULL),
(34,'Ronaldinho','Ronaldo','1964-10-31',2,133,2,'assets/images/joueurs/Ronaldinho2004.jpg',4,'2004',5,NULL,NULL),
(35,'HENRY','Thierry','1977-08-17',1,128,2,'assets/images/joueurs/ThierryHenry2003.jpg',3,'2003',11,NULL,NULL),
(36,'MALDINI','Paolo','1968-06-26',3,123,3,'assets/images/joueurs/PaoloMaldini2003.jpg',5,'2003',2,NULL,NULL),
(37,'ROBERTO CARLOS','','1976-04-10',3,145,2,'assets/images/joueurs/RobertoCarlos2002.jpg',2,'2002',7,NULL,NULL),
(38,'KHAN','Oliver','1969-06-15',4,110,3,'assets/images/joueurs/OliverKhan2002.jpg',4,'2002',3,NULL,NULL),
(39,'Raul','Gonzalez','1978-06-06',1,140,3,'assets/images/joueurs/RaulGonzalez2001.jpg',8,'2001',7,NULL,NULL),
(40,'KHAN','Oliver','1969-06-15',4,114,3,'assets/images/joueurs/OliverKhan2001.jpg',4,'2001',3,NULL,NULL),
(41,'ZIDANE','Zinedine','1972-06-23',2,181,2,'assets/images/joueurs/ZinedineZidane2000.jpg',3,'2000',4,NULL,NULL),
(42,'BAGGIO','Roberto','1967-02-18',1,136,2,'assets/images/joueurs/RobertoBaggio1994.jpg',5,'1994',4,NULL,NULL),
(43,'MALDINI','Paolo','1968-06-26',3,109,3,'assets/images/joueurs/PaoloMaldini1994.jpg',5,'1994',2,NULL,NULL),
(44,'KLINSMANN','Jurgen','1964-07-30',1,108,2,'assets/images/joueurs/JurgenKlinsman.jpg',4,'1995',3,NULL,NULL),
(45,'LITMANEN','Jari','1971-02-20',1,67,3,'assets/images/joueurs/JariLitmanen.jpg',12,'1995',24,NULL,NULL),
(46,'Benzema','Karim','1987-12-08',1,549,1,'assets/images/joueurs/KarimBenzema2022.jpg',3,'2022',7,'','KB9'),
(47,'Mane','Sadio','1980-12-08',1,193,2,'assets/images/joueurs/sadioMane2022.jpg',7,'2022',5,NULL,NULL),
(48,'Modric','Luka','1980-12-08',2,754,1,'assets/images/joueurs/lukaModric2018.jpg',4,'2018',7,NULL,NULL),
(49,'Mbappe','Kylian','1991-06-25',1,170,4,'assets/images/joueurs/KylianMbappe2022.jpg',3,'2022',1,NULL,NULL),
(50,'BERGKAMP','Dennis','1969-05-10',1,83,2,'assets/images/joueurs/DennisBerkamp1993.jpg',9,'1993',24,'Dennis Bergkamp, né le 10 mai 1969 à Amsterdam en Hollande-Septentrionale, est un footballeur international néerlandais évoluant au poste d\'attaquant entre 1986 et 2006. Lancé par Johan Cruyff, Bergkamp débute avec l\'Ajax Amsterdam en décembre 1986','Hollandais non volant'),
(51,'CANTONA','Eric','1966-05-24',1,34,3,'assets/images/joueurs/EricCantona1993.jpg',3,'1993',9,NULL,NULL),
(52,'RONALDO',' Luis Nazario','1976-09-22',1,143,2,'assets/images/joueurs/LuisNazarioRonaldo1996.jpg',2,'1996',6,NULL,NULL),
(53,'SHEARER','Alan','1970-08-13',1,107,3,'assets/images/joueurs/AlanShearer1996.jpg',13,'1996',12,NULL,NULL),
(54,'MIJATOVIC','Predrag','1969-01-19',1,68,2,'assets/images/joueurs/PredragMijatovic1997.jpg',8,'1997',7,NULL,NULL),
(55,'ZIDANE','Zinedine','1972-06-23',2,63,3,'assets/images/joueurs/ZinedineZidane1997.jpg',3,'1997',4,NULL,NULL),
(56,'SUKER','Davor','1968-01-01',1,68,2,'assets/images/joueurs/DavorSuker1998.jpg',22,'1998',7,NULL,NULL),
(57,'RONALDO','Luis Nazario','1976-09-22',1,66,3,'assets/images/joueurs/LuisNazarioRonaldo1998.jpg',2,'1998',5,NULL,NULL),
(58,'BECKHAM','David','1975-05-02',2,154,2,'assets/images/joueurs/DavidBeckham1999.jpg',13,'1999',9,'David Beckham, né le 2 mai 1975 à Leytonstone, est un footballeur international anglais ayant évolué au poste de milieu offensif. Sa carrière','Spice Boy'),
(59,' CHEVTCHENKO','Andriy','1976-09-29',1,64,3,'assets/images/joueurs/AndriyChevtchenko1999.jpg',3,'1999',2,'Andriy Le plus grand Joueur ukrainien de tous les temps ? ',NULL),
(60,'CHEVTCHENKO','Andryi','1976-09-29',1,64,3,'assets/images/joueurs/AndriyChevtchenko2000.jpg',25,'2000',10,NULL,NULL),
(61,'RONALDINHO','Gaucho','1980-03-21',1,133,3,'assets/images/joueurs/Ronaldinho_2004.jpg',2,'2004',6,NULL,NULL),
(62,'MESSI','Lionel','1987-06-24',1,0,1,'assets/images/joueurs/LionelMessi2010.jpg',7,'2010',6,NULL,NULL),
(63,'Iniesta','Andres','1984-05-11',2,0,2,'assets/images/joueurs/AndresIniesta2010.jpg',8,'2010',6,NULL,NULL),
(64,'HERNANDEZ','Xavier','1980-01-25',2,0,3,'assets/images/joueurs/XavierHernandez2010.jpg',8,'2010',6,NULL,NULL),
(65,'Sneijder','Wesley','1984-06-09',2,0,4,'assets/images/joueurs/WesleySneijder2010.jpg',9,'2010',5,NULL,NULL),
(66,'MESSI','Lionel','1987-06-24',1,0,1,'assets/images/joueurs/LionelMessi2011.jpg',7,'2011',6,NULL,NULL),
(67,'RONALDO','Cristiano','1985-02-05',1,0,1,'assets/images/joueurs/CristianoRonaldo2011.jpg',24,'2011',7,NULL,NULL),
(68,'HERNANDEZ','Xavier','1980-01-25',2,0,3,'assets/images/joueurs/XavierHernandez2011.jpg',8,'2011',6,NULL,NULL),
(69,'MESSI','Lionel','1987-06-24',1,0,1,'assets/images/joueurs/LionelMessi2011.jpg',7,'2012',6,NULL,NULL),
(70,'RONALDO','Cristiano','1985-02-05',1,0,2,'assets/images/joueurs/CristianoRonaldo2012.jpg',24,'2012',7,NULL,NULL),
(71,'Iniesta','Andres','1984-05-11',2,0,3,'assets/images/joueurs/AndresIniesta2012.jpg',8,'2012',6,NULL,NULL),
(72,'RONALDO','Cristiano','1985-02-05',1,0,1,'assets/images/joueurs/CristianoRonaldo2013.jpg',24,'2013',7,NULL,NULL),
(73,'MESSI','Lionel','1987-06-24',1,0,2,'assets/images/joueurs/LionelMessi2013.jpg',7,'2013',6,NULL,NULL),
(74,'Ribery','Franck','1983-04-07',1,0,3,'assets/images/joueurs/FranckRibery2013.jpg',3,'2013',3,NULL,NULL),
(75,'IBRAHIMOVIC','Zlatan','1983-10-03',1,0,4,'assets/images/joueurs/ZlatanIbra2013.jpg',17,'2013',1,NULL,NULL),
(76,'ANCELOTTI','Carlo','1959-06-10',5,0,0,'assets/images/joueurs/CarloAncelotti.jpg',5,'2022',7,'Ancelotti est considéré comme le meilleur entraineur du Monde',''),
(78,'Schillaci','Salvatore','1964-12-01',1,84,2,'assets/images/joueurs/SalvatoreSchillaci1990.jpg',5,'1990',4,'Salvatore Schillaci, appelé aussi Totò Schillaci, est un footballeur italien, né le 1er décembre 1964 à Palerme. Schillaci est sacré meilleur joueur et meilleur buteur de la coupe du monde 1990, où','Toto Schillaci'),
(79,'Savicevic','Dejan','1966-09-15',2,42,2,'assets/images/joueurs/DejanSavicevic1991.jpg',23,'1991',13,'Il est avec Paolo Maldini, Zvonimir Boban, Franco Baresi et Alessandro Costacurta l\'un des symboles de l\'ère Capello et de la domination du Milan au milieu des années 1990. Son sens du dribble avec notamment un pied gauche magique en ont fait l\'un des grands artistes du grand Milan AC des années 1990.\n\nIl est le vice-président de la Fédération de Serbie-et-Monténégro de football jusqu\'en 2006. Il est ensuite le premier président de la Fédération du Monténégro de football.','');
