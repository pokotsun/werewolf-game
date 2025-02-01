package client

import (
	"github.com/pokotsun/werewolf-game/pkg/domain"
)

type MockVillageListMaker struct{}

func (m *MockVillageListMaker) ListVillage() ([]*domain.Village, error) {
	return []*domain.Village{}, nil
}
