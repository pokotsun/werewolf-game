package client

import "github.com/pokotsun/werewolf-game/pkg/domain"

type CreateVillageRequest struct {
	Name                  *string
	CitizenCount          int32
	WerewolfCount         int32
	FortuneTellerCount    int32
	KnightCount           int32
	PsychicCount          int32
	MadmanCount           int32
	IsInitialActionActive bool
	Password              *string
	GameMasterName        *string
	GameMasterPassword    *string
}

type VillageCreator interface {
	createVillage(request CreateVillageRequest) (*domain.Village, error)
}
