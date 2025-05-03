//go:build integration
// +build integration

package client

import (
	"context"
	serverClient "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	"github.com/stretchr/testify/assert"
	"google.golang.org/grpc"
	"log"
	"testing"
	"time"
)

func TestSuccessOnVillageCreatorServerIntegration(t *testing.T) {
	// given:
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

	client := NewWerewolfServerClient(&ctx, conn)

	// リクエスト作成
	name := "Test Village 1st"
	password := "password"
	gameMasterName := "GameMaster"
	gameMasterPassword := "GameMasterPassword"
	req := serverClient.CreateVillageRequest{
		Name:                  &name,
		CitizenCount:          7,
		WerewolfCount:         2,
		FortuneTellerCount:    1,
		KnightCount:           1,
		PsychicCount:          1,
		MadmanCount:           1,
		IsInitialActionActive: true,
		Password:              &password,
		GameMasterName:        &gameMasterName,
		GameMasterPassword:    &gameMasterPassword,
	}

	// when:
	res, err := client.CreateVillage(req)
	if err != nil {
		log.Fatalf("could not get village info: %v", err)
	}

	res, err = client.CreateVillage(req)
	if err != nil {
		log.Fatalf("could not get village info: %v", err)
	}

	// レスポンスを表示
	assert.Equal(t, name, *res.Name)
	assert.Equal(t, int32(7), res.CitizenCount)
	assert.Equal(t, int32(2), res.WerewolfCount)
	assert.Equal(t, int32(1), res.FortuneTellerCount)
	assert.Equal(t, int32(1), res.KnightCount)
	assert.Equal(t, int32(1), res.PsychicCount)
	assert.Equal(t, int32(1), res.MadmanCount)
	assert.Equal(t, true, res.IsInitialActionActive)
	assert.Equal(t, int32(1), res.CurrentUserNumber)
}
