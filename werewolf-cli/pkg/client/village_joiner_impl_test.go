//go:build integration
// +build integration

package client

import (
	"context"
	"github.com/pokotsun/werewolf-game/pkg/client/entervillage"
	"github.com/stretchr/testify/assert"
	"google.golang.org/grpc"
	"log"
	"testing"
	"time"
)

func TestSuccessOnVillageJoinerServerIntegration(t *testing.T) {
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

	c := NewWerewolfServerClient(conn)

	req := client.EnterVillageRequest{
		VillageId:       "814611f7-06fa-4c59-ba5c-67bf686588b4",
		VillagePassword: "test",
		UserName:        "Test User",
		UserPassword:    "test",
	}

	// when:
	res, err := c.EnterVillage(req)
	if err != nil {
		log.Fatalf("could not get village info: %v", err)
	}

	// レスポンスを表示
	assert.Equal(t, "814611f7-06fa-4c59-ba5c-67bf686588b4", res.VillageId)
	assert.Equal(t, "814611f7-06fa-4c59-ba5c-67bf686588b4", res.UserId)
}
