package client

import "github.com/pokotsun/werewolf-game/pkg/domain"

type VillageListMaker interface {
	ListVillages() ([]*domain.Village, error)
}
