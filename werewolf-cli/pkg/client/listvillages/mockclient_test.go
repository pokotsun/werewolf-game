package client

import (
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSuccess(t *testing.T) {
	// given:
	mockVillageCreator := MockVillageListMaker{}

	// when:
	actualList, err := mockVillageCreator.ListVillages()

	// then:
	assert.Equal(t, nil, err)
	assert.Equal(t, []*domain.Village{}, actualList)
}
