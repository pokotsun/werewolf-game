package main

import (
	"context"
	serverClient "github.com/pokotsun/werewolf-game/pkg/client"
	createVillage "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
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
	defer func(conn *grpc.ClientConn) {
		err := conn.Close()
		if err != nil {
			log.Fatalf("could not close connection: %v", err)
		}
	}(conn)

	client := serverClient.NewWerewolfServerClient(&ctx, conn)

	// リクエストを作成
	name := "Test Village 1st"
	req := createVillage.CreateVillageRequest{
		Name:                  &name,
		CitizenCount:          7,
		WerewolfCount:         2,
		FortuneTellerCount:    1,
		KnightCount:           1,
		PsychicCount:          1,
		MadmanCount:           1,
		IsInitialActionActive: true,
	}

	// サーバーにリクエストを送信
	res, err := client.CreateVillage(req)
	if err != nil {
		log.Fatalf("could not get village info: %v", err)
	}

	// レスポンスを表示
	log.Printf("Village Info: %v, %v, %v", res, *res.Id, *res.Name)
}
