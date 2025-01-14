# werewolf-game
人狼オンラインを模してみる

## Quick Start
以下環境が最低限整っている必要がある
- docker-desktop
- asdf
    - asdf-go-plugin 

そのうえで、以下依存をインストール
```shell
$ brew install go-task/tap/go-task
$ brew install protobuf
$ brew install protoc-gen-grpc-java
$ go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
$ go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
$ brew install grpcurl
```

### code 自動生成
project root で、
```shell
$ task generate
```

### サーバー実行
```shell
$ docker-compose up -d
$ cd backend-kotlin
$ ./gradlew bootRun
```

動作確認
TODO: 仮実装のため、後ほど Quick TEST 等に変更
```shell
$ grpcurl --plaintext -d '{"name": "test"}' localhost:9090 village.VillageService/CreateVillage
{
  "id": "12345",
  "name": "VillageName",
  "created_at": "2022-01-01T00:00:00",
  "updated_at": "2022-01-01T00:00:15"
}
```
### CLI Client 側動作確認
```shell
$ cd werewolf-cli
$ go run cmd/create_village.go
2025/01/15 00:01:12 Village Info: id:"12345"  name:"VillageName"  created_at:"2022-01-01T00:00:00"  updated_at:"2022-01-01T00:00:15"
```
