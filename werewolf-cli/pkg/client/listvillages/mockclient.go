package client

import (
	"github.com/pokotsun/werewolf-game/pkg/domain"
)

var _ VillageListMaker = (*MockVillageListMaker)(nil)

type MockVillageListMaker struct{}

func (m *MockVillageListMaker) ListVillages() ([]*domain.Village, error) {
	return []*domain.Village{}, nil
}
