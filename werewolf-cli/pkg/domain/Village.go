package domain

type Village struct {
	Id                    *string
	Name                  *string
	CitizenCount          int32
	WerewolfCount         int32
	FortuneTellerCount    int32
	KnightCount           int32
	PsychicCount          int32
	MadmanCount           int32
	IsInitialActionActive bool
	CurrentUserNumber     int32
}
