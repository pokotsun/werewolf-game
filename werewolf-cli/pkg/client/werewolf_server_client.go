package client

import (
	"context"
	client "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/pokotsun/werewolf-game/pkg/grpc/github.com/pokotsun/werewolf/grpc/village"
	"google.golang.org/grpc"
)

type WerewolfServerClient struct {
	ctx  *context.Context
	conn *grpc.ClientConn
}

func NewWerewolfServerClient(ctx *context.Context, conn *grpc.ClientConn) *WerewolfServerClient {
	return &WerewolfServerClient{ctx: ctx, conn: conn}
}

func (c *WerewolfServerClient) CreateVillage(request client.CreateVillageRequest) (*domain.Village, error) {
	// クライアントを作成
	cc := village.NewVillageServiceClient(c.conn)

	isInitialActionActive := int32(1)
	if !request.IsInitialActionActive {
		isInitialActionActive = 0
	}
	req := village.CreateVillageRequest{
		Name:                  *request.Name,
		CitizenCount:          request.CitizenCount,
		WerewolfCount:         request.WerewolfCount,
		FortuneTellerCount:    request.FortuneTellerCount,
		KnightCount:           request.KnightCount,
		PsychicCount:          request.PsychicCount,
		MadmanCount:           request.MadmanCount,
		IsInitialActionActive: isInitialActionActive,
	}

	// サーバーにリクエストを送信
	res, err := cc.CreateVillage(*c.ctx, &req)
	if err != nil {
		return nil, err
	}

	response := domain.Village{
		Id:           &res.Id,
		Name:         &res.Name,
		CitizenCount: res.CitizenCount,
	}

	return &response, nil
}
