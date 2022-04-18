USE Taco;
DELETE FROM Taco_Order_Tacos;
DELETE FROM Taco_Ingredients;
DELETE FROM Taco;
DELETE FROM Taco_Order ;
DELETE FROM Ingredient  WHERE id=1 OR 1=1;

INSERT INTO Ingredient(id, name, type) VALUES ('FLTO','Flour Tortilla','WRAP');
INSERT INTO Ingredient(id,name,type) VALUES('COTO','Corn Tortilla','WRAP');
INSERT INTO Ingredient(id, name, type) VALUES ('GRBF','Ground Beef','PROTEIN');
INSERT INTO Ingredient(id, name, type) VALUES ('CARN','Carnitas','PROTEIN');
INSERT INTO Ingredient(id, name, type) VALUES ('TMTO','Diced Tomatoes','VEGGIES');
INSERT INTO Ingredient(id, name, type) VALUES ('LETC','Lettuce','VEGGIES');
INSERT INTO Ingredient(id, name, type) VALUES ('CHED','Cheddar','CHEESE');
INSERT INTO Ingredient(id, name, type) VALUES ('JACK','Monterrey Jack','CHEESE');
INSERT INTO Ingredient(id, name, type) VALUES ('SLSA','Salsa','SAUCES');
INSERT INTO Ingredient(id, name, type) VALUES ('SRCR','Sour Cream','SAUCES');
