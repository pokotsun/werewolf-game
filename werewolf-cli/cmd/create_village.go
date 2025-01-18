package main

import (
	"context"
	"github.com/pokotsun/werewolf-game/pkg/grpc/github.com/pokotsun/werewolf/grpc/village"
	_ "github.com/pokotsun/werewolf-game/pkg/grpc/github.com/pokotsun/werewolf/grpc/village"
	"google.golang.org/grpc"
	"log"
	"time"
)

func main() {
	// コンテキストを作成し、タイムアウトを設定
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*10)
	defer cancel()

	// grpc.DialContext を使用して接続を確立
	conn, err := grpc.DialContext(ctx, "localhost:9090", grpc.WithInsecure(), grpc.WithBlock())
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()

	// クライアントを作成
	c := village.NewVillageServiceClient(conn)

	// リクエストを作成
	req := &village.CreateVillageRequest{Name: "Test Village 1st", UserNumber: 7}

	// サーバーにリクエストを送信
	res, err := c.CreateVillage(ctx, req)
	if err != nil {
		log.Fatalf("could not get village info: %v", err)
	}

	// レスポンスを表示
	log.Printf("Village Info: %v", res)
}
