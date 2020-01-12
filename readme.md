# Coffree: back-end

The back-end component of the Coffree system. Exchanges information with database, receives data from Counter and serves data for Front-end.

## Endpoints

* `/room/` **GET** - get basic, up-to-date information about all rooms
* `/room/{roomId}` **GET** - get information about a `roomId` room
* `/count/{roomId}` **POST** - add information about current amount of people in a particular room 

## Running

Just run:

```
./gradlew bootRun
```

## Other components
* [Front-end](https://github.com/Solvinden-Team/bitehack-front)
* [Counter](https://github.com/Solvinden-Team/BITEhack2020-counter)
