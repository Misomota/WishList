
create table IF NOT EXISTS WishList
(
    WishListId INT AUTO_INCREMENT PRIMARY KEY,
    WishListName VARCHAR(255) NOT NULL
);

--init-data

INSERT INTO WishList (WishListName) VALUES ('test');
INSERT INTO WishList (WishListName) VALUES ('fefe');