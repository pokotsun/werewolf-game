package client

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSuccess(t *testing.T) {
	// given:
	mockVillageCreator := MockVillageCreator{}

	name := "TestVillage"
	req := CreateVillageRequest{
		Name:                  &name,
		CitizenCount:          10,
		WerewolfCount:         2,
		FortuneTellerCount:    1,
		KnightCount:           1,
		PsychicCount:          1,
		MadmanCount:           1,
		IsInitialActionActive: true,
	}

	// when:
	actual, err := mockVillageCreator.createVillage(req)

	// then:
	assert.Equal(t, nil, err)
	assert.Equal(t, "fa7f219efc9db23ca1611b50ff29d5e56de5920fd756669bb519622ada3a995f", *actual.Id)
	assert.Equal(t, *req.Name, *actual.Name)
	assert.Equal(t, req.CitizenCount, actual.CitizenCount)
	assert.Equal(t, req.WerewolfCount, actual.WerewolfCount)
	assert.Equal(t, req.FortuneTellerCount, actual.FortuneTellerCount)
	assert.Equal(t, req.KnightCount, actual.KnightCount)
	assert.Equal(t, req.PsychicCount, actual.PsychicCount)
	assert.Equal(t, req.MadmanCount, actual.MadmanCount)
	assert.Equal(t, req.IsInitialActionActive, actual.IsInitialActionActive)
}
