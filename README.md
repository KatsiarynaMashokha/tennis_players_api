# _Tennis players/tournaments api_
####  _This application is written in Java with the help of Spark. Published on 08/25/2017._
#### By _**Katsiaryna Mashokha**_
## Description
This api give an ability to perform different operations on tennis players: addition of a new player, browse through all players, adding a tournament, mark a tournament won by player and etc. Below there are a couple of examples showing the functionality of the giveb api with the tool Postman:
1) Add a new player: ![Alt text] (public/img/addAPlayer.png)
2) See a particular player: ![Alt text] (public/img/player.png)
3) Get all players for a given country:  ![Alt text] (public/img/getAllPlayers.png)
4) Updating a player: ![Alt text] (public/img/update.png)
5) Show all tournaments ![Alt text] (public/img/allTournaments.png)

## Development Specifications
| Behavior      | Example Input         | Example Output        |
| ------------- | ------------- | ------------- |
| Addition of a new player|/players/new|{"name": Roger Federer, "age": 36, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16}|
| Add a country to a player|/players/1/country/Switzerland| Switzerland|
| Get all players of a particular country|/players/country/Switzerland|{"name": Roger Federer, "age": 36, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16}|
| See an information of a single player |/players/1|{"name": Roger Federer, "age": 36, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16}|
| See all players | /players |{"name": Roger Federer, "age": 36, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16}, {"name": Stan Wawrinka, "age": 32, "gender": "M", "ranking": 4, "points": 5690, "tournamentsPlayed": 18}|
| Update an information of a player |/players/1/update|{"name": Roger Federer, "age": 37, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16}|
| Remove a players | /players/1/delete |1|
| Remove all players | /players/delete |0|
| Add a new tournamnent | /tournaments/new | {"date": "September","id": 1, "title": "US Open"}|
| Add a player to a tournament | /players/1/tournaments/1 |{"name": Roger Federer, "age": 36, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16} |
| Get all tournaments won by a player | /players/1/tournaments |{"date": "September","id": 1, "title": "US Open"}, {"date": "January","id": 2, "title": "Australian Open"} |
| Add a tournamament to a player | /tournaments/1/players/1 | {"date": "September","id": 1, "title": "US Open"} |
| See a particular tournament | /tournaments/1 |{"date": "September","id": 1, "title": "US Open"}  |
| See all tournaments | /tournaments | {"date": "September","id": 1, "title": "US Open"}, {"date": "January","id": 2, "title": "Australian Open"} |
| See all players won the tournament | /tournaments/1/players |{"name": Roger Federer, "age": 36, "gender": "M", "ranking": 3, "points": 7145, "tournamentsPlayed": 16}, {"name": Stan Wawrinka, "age": 32, "gender": "M", "ranking": 4, "points": 5690, "tournamentsPlayed": 18} |
| Remove a tournament | /tournaments/1/delete | 1 |
| Remove all tournaments | /tournaments/delete | 0 |


## Setup/Installation Requirements
_Download the following project from the gitHub by tapping "Download" or using 'git clone' from the terminal_

## Support and contact details
_For any concerns or questions email to: katsiarynamashokha@gmail.com_

### License
Copyright © 2017 **_Katsiaryna Mashokha_**