package client

import (
	"crypto/sha256"
	"encoding/json"
	"fmt"
	"github.com/pokotsun/werewolf-game/pkg/domain"
)

var _ VillageCreator = (*MockVillageCreator)(nil)

type MockVillageCreator struct{}

func (m *MockVillageCreator) CreateVillage(request CreateVillageRequest) (*domain.JoinedVillage, error) {
	jsonData, err := json.Marshal(request)
	if err != nil {
		fmt.Errorf("json marshal error: %v", err)
	}

	hash := sha256.Sum256(jsonData)
	hashString := fmt.Sprintf("%x", hash)

	joinedVillage := domain.NewJoinedVillageAsGameMaster(
		&domain.Village{
			Id:                    hashString,
			Name:                  request.Name,
			CitizenCount:          request.CitizenCount,
			WerewolfCount:         request.WerewolfCount,
			FortuneTellerCount:    request.FortuneTellerCount,
			KnightCount:           request.KnightCount,
			PsychicCount:          request.PsychicCount,
			MadmanCount:           request.MadmanCount,
			IsInitialActionActive: request.IsInitialActionActive,
			CurrentUserNumber:     1,
		},
		request.Password,
		hashString,
		request.GameMasterName,
		request.GameMasterPassword,
	)
	return &joinedVillage, nil
}
