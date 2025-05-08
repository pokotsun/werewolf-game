package client

import "github.com/pokotsun/werewolf-game/pkg/domain"

type CreateVillageRequest struct {
	Name                  string
	CitizenCount          int
	WerewolfCount         int
	FortuneTellerCount    int
	KnightCount           int
	PsychicCount          int
	MadmanCount           int
	IsInitialActionActive bool
	Password              string
	GameMasterName        string
	GameMasterPassword    string
}

type VillageCreator interface {
	CreateVillage(request CreateVillageRequest) (*domain.JoinedVillage, error)
}
