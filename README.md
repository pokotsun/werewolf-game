# werewolf-game
人狼オンラインを模してみる

## local 環境を用意する
```shell
docker-compose up
```

## backend-kotlin を実行する
DB migration はアプリケーション実行時に行われるためアプリケーションを起動する。

```shell
cd backend-kotlin
./gradlew bootRun
```
