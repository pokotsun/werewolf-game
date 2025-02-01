package client

import "github.com/pokotsun/werewolf-game/pkg/domain"

type VillageListMaker interface {
	ListVillage() ([]*domain.Village, error)
}
