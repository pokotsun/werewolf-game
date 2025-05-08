package client

import (
	"context"
	cvc "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	vjc "github.com/pokotsun/werewolf-game/pkg/client/entervillage"
	lvmc "github.com/pokotsun/werewolf-game/pkg/client/listvillages"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/pokotsun/werewolf-game/pkg/grpc/github.com/pokotsun/werewolf/grpc/village"
	"google.golang.org/grpc"
	"time"
)

var _ cvc.VillageCreator = (*WerewolfServerClient)(nil)
var _ lvmc.VillageListMaker = (*WerewolfServerClient)(nil)
var _ vjc.VillageJoiner = (*WerewolfServerClient)(nil)

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

func (c *WerewolfServerClient) CreateVillage(request cvc.CreateVillageRequest) (*domain.JoinedVillage, error) {
	req := village.CreateVillageRequest{
		Name:                  request.Name,
		CitizenCount:          int32(request.CitizenCount),
		WerewolfCount:         int32(request.WerewolfCount),
		FortuneTellerCount:    int32(request.FortuneTellerCount),
		KnightCount:           int32(request.KnightCount),
		PsychicCount:          int32(request.PsychicCount),
		MadmanCount:           int32(request.MadmanCount),
		IsInitialActionActive: request.IsInitialActionActive,
		Password:              request.Password,
		GameMasterName:        request.GameMasterName,
		GameMasterPassword:    request.GameMasterPassword,
	}

	// コンテキストを作成し、タイムアウトを設定
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	// サーバーにリクエストを送信
	res, err := (*c.villageServiceClient).CreateVillage(ctx, &req)
	if err != nil {
		return nil, err
	}

	response := domain.JoinedVillage{
		Village: domain.Village{
			Id:                    res.Id,
			Name:                  res.Name,
			CitizenCount:          int(res.CitizenCount),
			WerewolfCount:         int(res.WerewolfCount),
			FortuneTellerCount:    request.FortuneTellerCount,
			KnightCount:           request.KnightCount,
			PsychicCount:          request.PsychicCount,
			MadmanCount:           request.MadmanCount,
			IsInitialActionActive: true,
			CurrentUserNumber:     int(res.CurrentUserNumber),
		},
		VillagePassword:  request.Password,
		YourUserId:       res.GameMasterUserId,
		YourUserName:     req.GameMasterName,
		YourUserPassword: req.GameMasterPassword,
		AreYouGameMaster: false,
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
			Id:                    villageResponse.Id,
			Name:                  villageResponse.Name,
			CitizenCount:          int(villageResponse.CitizenCount),
			WerewolfCount:         int(villageResponse.WerewolfCount),
			FortuneTellerCount:    int(villageResponse.FortuneTellerCount),
			KnightCount:           int(villageResponse.KnightCount),
			PsychicCount:          int(villageResponse.PsychicCount),
			MadmanCount:           int(villageResponse.MadmanCount),
			IsInitialActionActive: true,
			CurrentUserNumber:     int(villageResponse.CurrentUserNumber),
		}
		targetList = append(targetList, &target)
	}

	return targetList, nil
}

func (c *WerewolfServerClient) EnterVillage(request vjc.EnterVillageRequest) (*vjc.EnterVillageResponse, error) {
	// コンテキストを作成し、タイムアウトを設定
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	req := village.EnterVillageRequest{
		VillageId:       request.VillageId,
		VillagePassword: request.VillagePassword,
		UserName:        request.UserName,
		UserPassword:    request.UserPassword,
	}

	// サーバーにリクエストを送信
	res, err := (*c.villageServiceClient).EnterVillage(ctx, &req)
	if err != nil {
		return nil, err
	}

	villageId, userId := res.VillageId, res.UserId

	return &vjc.EnterVillageResponse{
		VillageId: villageId,
		UserId:    userId,
	}, nil
}
