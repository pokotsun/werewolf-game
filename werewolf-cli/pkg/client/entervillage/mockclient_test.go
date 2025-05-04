package client

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSuccess(t *testing.T) {
	// given:
	mockVillageJoiner := MockVillageJoiner{}

	villageId := "TestVillage"
	villagePassword := "password"
	userName := "GameMaster"
	userPassword := "GameMasterPassword"
	req := EnterVillageRequest{
		VillageId:       &villageId,
		VillagePassword: &villagePassword,
		UserName:        &userName,
		UserPassword:    &userPassword,
	}

	// when:
	actual, err := mockVillageJoiner.enterVillage(req)

	// then:
	assert.Equal(t, nil, err)
	assert.Equal(t, "e0ce72dcd7bd53b61f0ebdd96ce4d16c4c5760a7de0b72542b86a92512a9d050", actual.VillageId)
	assert.Equal(t, "e0ce72dcd7bd53b61f0ebdd96ce4d16c4c5760a7de0b72542b86a92512a9d050", actual.UserId)
}
