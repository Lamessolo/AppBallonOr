DROP TABLE IF EXISTS tbl_joueur;
DROP TABLE IF EXISTS tbl_club;
DROP TABLE IF EXISTS tbl_poste;
DROP TABLE IF EXISTS  tbl_selection;

CREATE TABLE tbl_club (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    pays VARCHAR(255)
);

INSERT INTO tbl_club (name,pays) VALUES ('Paris SG','France'),('Milan AC','Italie'),('Bayer Munich','Allemagne'),('Juventus','Italie'),('Inter Milan','Italie'),('Barcelone','Espagne'),('Real Madrid','Espagne'),('Dortmund','Allemagne'),('Manchester United','Angleterre'),('Liverpool','Angleterre'),('Arsenal','Angleterre'),('Newcastle','Angleterre');


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

INSERT INTO tbl_selection (confederation,name) VALUES ('CAF','Cameroun'),('CONMEBOL','Bresil'),('UEFA','France'),('UEFA','Allemagne'),('UEFA','Italie'),('CAF','Nigeria'),('CONMEBOL','Argentine'),('UEFA','Espagne'),('UEFA','Pays-Bas'),('CAF','Liberia'),('UEFA','Republique Tcheque'),('UEFA','Finlande'),('UEFA','Angleterre');


CREATE TABLE tbl_joueur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    prenom VARCHAR(255),
    date_naissance DATE,
    poste_id INT,
    nbr_point_obtenu INT,
    classement INT,
    image_url VARCHAR(255),
    selection_id INT,
    annee_Recompense VARCHAR(255),
    club_id INT   
);
INSERT INTO tbl_joueur (annee_Recompense, classement, date_naissance, image_url, name, nbr_point_obtenu, prenom, club_id, poste_id, selection_id)
VALUES 
    ('1990', 1, '1961-03-21', 'assets/images/joueurs/LotharMatthaus.jpg', 'Lothar Matthäus', 137, 'Lothar', 2, 3, 3),
    ('1991', 1, '1963-05-11', 'assets/images/joueurs/JeanPierrePapin.jpg', 'Papin', 141, 'Jean Pierre', 6, 1, 5),
    ('1992', 1, '1964-10-31', 'assets/images/joueurs/MarcoVanBasten.jpg', 'Van Basten', 98, 'Marco', 5, 1, 4),
    ('1993', 1, '1967-02-18', 'assets/images/joueurs/RobertoBaggio.jpg', 'Baggio', 192, 'Roberto', 3, 1, 6),
    ('1994', 1, '1966-02-08', 'assets/images/joueurs/HristoStoichkov.jpg', 'Stoichkov', 210, 'Hristo', 5, 1, 7),
    ('1995', 1, '1966-10-01', 'assets/images/joueurs/GeorgeWeah.jpg', 'Weah', 144, 'George', 8, 1, 10),
    ('1996', 1, '1967-09-05', 'assets/images/joueurs/MatthiasSammer.jpg', 'Sammer', 144, 'Matthias', 6, 3, 4),
    ('1997', 1, '1976-09-18', 'assets/images/joueurs/LuisNazarioRonaldo.jpg', 'Ronaldo', 222, 'Luis Nazario', 2, 1, 7),
    ('1998', 1, '1967-09-05', 'assets/images/joueurs/ZinedineZidane.jpg', 'Zidane', 244, 'Zinedine', 4, 2, 3),
    ('1999', 1, '1967-02-18', 'assets/images/joueurs/BarbosaRivaldo.jpg', 'Rivaldo', 219, 'Vitor Barbosa', 4, 2, 3),
    ('2000', 1, '1967-02-18', 'assets/images/joueurs/LuisFigo.jpg', 'Figo', 197, 'Luis', 3, 1, 8),
    ('2001', 1, '1963-05-11', 'assets/images/joueurs/MickaelOwen.jpg', 'Owen', 176, 'Mickael', 2, 1, 11),
    ('2002', 1, '1963-05-11', 'assets/images/joueurs/LuisNazarioRonaldo2.jpg', 'Ronaldo', 169, 'Luis Nazario', 5, 1, 4),
    ('1989', 1, '1963-05-11', 'assets/images/joueurs/MarcoVanBasten1989.jpg', 'Van Basten', 119, 'Marco', 2, 1, 5),
    ('2007', 1, '1963-05-11', 'assets/images/joueurs/RicardoKaka.jpg', 'Kaka', 445, 'Ricardo ', 6, 2, 9),
    ('2008', 1, '1966-10-01', 'assets/images/joueurs/CristianoRonaldo2008.jpg', 'Ronaldo', 446, 'Cristiano', 9, 1, 6),
    ('2009', 1, '1966-10-01', 'assets/images/joueurs/LionelMessi2009.jpg', 'Messi', 473, 'Lionel', 7, 1, 4),
    ('2003', 1, '1966-10-01', 'assets/images/joueurs/PavelNedved.jpg', 'Nedved', 190, 'Pavel', 1, 2, 7),
    ('2004', 1, '1966-10-01', 'assets/images/joueurs/AndriyChevtchenko.jpg', 'Chevtchenko', 175, 'Andriy', 3, 1, 11),
    ('2005', 1, '1966-10-01', 'assets/images/joueurs/Ronaldinho.jpg', 'Ronaldinho', 225, 'Ronaldo', 1, 1, 6),
    ('2006', 1, '1964-10-31', 'assets/images/joueurs/FabioCannavaro.jpg', 'Cannavaro', 173, 'Fabio', 5, 3, 2),
    ('2009', 1, '1964-10-31', 'assets/images/joueurs/CristianoRonaldo2009.jpg', 'Ronaldo', 233, 'Cristiano', 2, 1, 3),
    ('2009', 3, '1964-10-31', 'assets/images/joueurs/HernandezXavi.jpg', 'Xavi', 170, 'Xavier', 3, 2, 2),
    ('2008', 1, '1968-12-01', 'assets/images/joueurs/LionelMessi2008.jpg', 'Messi', 281, 'Lionel', 7, 1, 1),
    ('2008', 3, '1968-12-01', 'assets/images/joueurs/FernandoTorres2008.jpg', 'Torres', 179, 'Fernando', 6, 1, 4),
    ('2007', 1, '1968-12-01', 'assets/images/joueurs/CristianoRonaldo2007.jpg', 'Ronaldo', 277, 'Cristiano', 5, 1, 7),
    ('2007', 1, '1970-12-01', 'assets/images/joueurs/DidierDrogba2007.jpg', 'Drogba', 106, 'Didier', 3, 1, 12),
    ('2007', 1, '1970-12-01', 'assets/images/joueurs/LionelMessi2007.jpg', 'Messi', 255, 'Lionel', 4, 1, 9),
    ('2006', 4, '1970-12-01', 'assets/images/joueurs/GianluigiBuffon2006.jpg', 'Buffon', 124, 'Gianluigi', 5, 4, 4),
    ('2006', 1, '1970-12-01', 'assets/images/joueurs/ThierryHenry2006.jpg', 'Henry', 121, 'Thierry', 6, 1, 8),
    ('2005', 2, '1970-12-01', 'assets/images/joueurs/FranckLampard2005.jpg', 'Lampard', 148, 'Franck', 8, 2, 3),
    ('2005', 2, '1970-12-01', 'assets/images/joueurs/StevenGerrard2005.jpg', 'Gerrard', 142, 'Steven', 3, 2, 3),
    ('2004', 2, '1964-10-31', 'assets/images/joueurs/AndersonDeco2004.jpg', 'Deco', 139, 'Anderson Luis', 4, 2, 6),
    ('2004', 2, '1964-10-31', 'assets/images/joueurs/Ronaldinho2004.jpg', 'Ronaldinho', 133, 'Ronaldo', 5, 2, 4),
    ('2003', 1, '1964-10-31', 'assets/images/joueurs/ThierryHenry2003.jpg', 'Henry', 128, 'Thierry', 6, 1, 7),
    ('2003', 3, '1980-12-08', 'assets/images/joueurs/PaoloMaldini2003.jpg', 'Maldini', 123, 'Paolo', 5, 3, 7),
    ('2002', 3, '1980-12-08', 'assets/images/joueurs/RobertoCarlos2002.jpg', 'Roberto Carlos', 145, '', 6, 3, 5),
    ('2002', 4, '1980-12-08', 'assets/images/joueurs/OliverKhan2002.jpg', 'Khan', 110, 'Oliver', 1, 4, 11),
    ('2001', 1, '1980-12-08', 'assets/images/joueurs/RaulGonzalez2001.jpg', 'Raul', 140, 'Gonzalez', 1, 1, 10),
    ('2001', 4, '1980-12-08', 'assets/images/joueurs/OliverKhan2001.jpg', 'Khan', 114, 'Oliver', 1, 4, 9),
    ('2000', 2, '1980-12-08', 'assets/images/joueurs/ZinedineZidane2000.jpg', 'Zidane', 181, 'Zinedine', 2, 2, 6),
    ('1994', 1, '1980-12-08', 'assets/images/joueurs/RobertoBaggio1994.jpg', 'Baggio', 136, 'Roberto', 3, 1, 1),
    ('1994', 3, '1980-12-08', 'assets/images/joueurs/PaoloMaldini1994.jpg', 'Maldini', 109, 'Paolo', 4, 3, 3),
    ('1995', 2, '1980-12-08', 'assets/images/joueurs/JurgenKlinsman.jpg', 'Klinsmann', 108, 'Jurgen', 5, 1, 6),
    ('1995', 3, '1980-12-08', 'assets/images/joueurs/JariLitmanen.jpg', 'Litmanen', 67, 'Jari', 6, 1, 3),
    ('2022', 1, '1980-12-08', 'assets/images/joueurs/KarimBenzema2022.jpg', 'Benzema', 549, 'Karim', 6, 1, 1);
