insert into players(playerName, robotId) values ('Player1', 1), ('Player2', 2);

insert into choice(gameId, turnId, playerId, choiceType) values (1, 1, 1, 1);
insert into move(gameId, turnId, playerId, reg1, reg2, reg3, reg4, reg5) values (1, 1, 1, 1, 2, 3, 4, 5);
insert into move(gameId, turnId, playerId, reg1, reg2, reg3, reg4, reg5) values (1, 1, 2, 5, 4, 3, 2, 1);


insert into games(gameName, boardId, gameStatus, turnId, maxPlayers) values ('Game1', 1, 0, 1, 6), ('Game2', 2, 0, 1, 6), ('Game3', 3, 0, 1, 6);