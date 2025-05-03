package client

import (
	"context"
	client "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/pokotsun/werewolf-game/pkg/grpc/github.com/pokotsun/werewolf/grpc/village"
	"google.golang.org/grpc"
	"time"
)

type WerewolfServerClient struct {
	conn                 *grpc.ClientConn
	villageServiceClient *village.VillageServiceClient
}

func NewWerewolfServerClient(conn *grpc.ClientConn) *WerewolfServerClient {
	villageServiceClient := village.NewVillageServiceClient(conn)
	return &WerewolfServerClient{
		conn:                 conn,
		villageServiceClient: &villageServiceClient,
	}
}

func (c *WerewolfServerClient) CreateVillage(request client.CreateVillageRequest) (*domain.Village, error) {
	req := village.CreateVillageRequest{
		Name:                  *request.Name,
		CitizenCount:          request.CitizenCount,
		WerewolfCount:         request.WerewolfCount,
		FortuneTellerCount:    request.FortuneTellerCount,
		KnightCount:           request.KnightCount,
		PsychicCount:          request.PsychicCount,
		MadmanCount:           request.MadmanCount,
		IsInitialActionActive: request.IsInitialActionActive,
		Password:              *request.Password,
		GameMasterName:        *request.GameMasterName,
		GameMasterPassword:    *request.GameMasterPassword,
	}

	// コンテキストを作成し、タイムアウトを設定
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	// サーバーにリクエストを送信
	res, err := (*c.villageServiceClient).CreateVillage(ctx, &req)
	if err != nil {
		return nil, err
	}

	response := domain.Village{
		Id:                    &res.Id,
		Name:                  &res.Name,
		CitizenCount:          res.CitizenCount,
		WerewolfCount:         res.WerewolfCount,
		FortuneTellerCount:    request.FortuneTellerCount,
		KnightCount:           request.KnightCount,
		PsychicCount:          request.PsychicCount,
		MadmanCount:           request.MadmanCount,
		IsInitialActionActive: true,
		CurrentUserNumber:     res.CurrentUserNumber,
	}

	return &response, nil
}

func (c *WerewolfServerClient) ListVillages() ([]*domain.Village, error) {
	// コンテキストを作成し、タイムアウトを設定
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	// サーバーにリクエストを送信
	req := village.ListVillagesRequest{}
	res, err := (*c.villageServiceClient).ListVillages(ctx, &req)
	if err != nil {
		return nil, err
	}

	var targetList []*domain.Village
	for _, villageResponse := range res.Villages {
		target := domain.Village{
			Id:                    &villageResponse.Id,
			Name:                  &villageResponse.Name,
			CitizenCount:          villageResponse.CitizenCount,
			WerewolfCount:         villageResponse.WerewolfCount,
			FortuneTellerCount:    villageResponse.FortuneTellerCount,
			KnightCount:           villageResponse.KnightCount,
			PsychicCount:          villageResponse.PsychicCount,
			MadmanCount:           villageResponse.MadmanCount,
			IsInitialActionActive: true,
			CurrentUserNumber:     villageResponse.CurrentUserNumber,
		}
		targetList = append(targetList, &target)
	}

	return targetList, nil
}
