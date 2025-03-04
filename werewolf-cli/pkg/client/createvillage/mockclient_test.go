package client

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSuccess(t *testing.T) {
	// given:
	mockVillageCreator := MockVillageCreator{}

	name := "TestVillage"
	password := "password"
	gameMasterName := "GameMaster"
	gameMasterPassword := "GameMasterPassword"
	req := CreateVillageRequest{
		Name:                  &name,
		CitizenCount:          10,
		WerewolfCount:         2,
		FortuneTellerCount:    1,
		KnightCount:           1,
		PsychicCount:          1,
		MadmanCount:           1,
		IsInitialActionActive: true,
		Password:              &password,
		GameMasterName:        &gameMasterName,
		GameMasterPassword:    &gameMasterPassword,
	}

	// when:
	actual, err := mockVillageCreator.createVillage(req)

	// then:
	assert.Equal(t, nil, err)
	assert.Equal(t, "ffd8a8822eddbac951b1ece03fcbd3623c56d8d8d532aa945dd9970406cf1efd", *actual.Id)
	assert.Equal(t, *req.Name, *actual.Name)
	assert.Equal(t, req.CitizenCount, actual.CitizenCount)
	assert.Equal(t, req.WerewolfCount, actual.WerewolfCount)
	assert.Equal(t, req.FortuneTellerCount, actual.FortuneTellerCount)
	assert.Equal(t, req.KnightCount, actual.KnightCount)
	assert.Equal(t, req.PsychicCount, actual.PsychicCount)
	assert.Equal(t, req.MadmanCount, actual.MadmanCount)
	assert.Equal(t, req.IsInitialActionActive, actual.IsInitialActionActive)
}
