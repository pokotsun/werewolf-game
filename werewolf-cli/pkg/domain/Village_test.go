package domain

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestSuccessOnCurrentVillageNumbers(t *testing.T) {
	// given:
	village := Village{
		CitizenCount:       10,
		WerewolfCount:      2,
		FortuneTellerCount: 1,
		KnightCount:        1,
		PsychicCount:       1,
		MadmanCount:        1,
		CurrentUserNumber:  1,
	}

	// when:
	actual := village.GetTotalMemberCount()

	expected := 16
	assert.Equal(t, expected, actual)
}
