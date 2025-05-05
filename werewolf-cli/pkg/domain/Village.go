package domain

type Village struct {
	Id                    string
	Name                  string
	CitizenCount          int
	WerewolfCount         int
	FortuneTellerCount    int
	KnightCount           int
	PsychicCount          int
	MadmanCount           int
	IsInitialActionActive bool
	CurrentUserNumber     int
}

func (v *Village) GetTotalMemberCount() int {
	return v.CitizenCount + v.WerewolfCount + v.FortuneTellerCount + v.KnightCount + v.PsychicCount + v.MadmanCount
}
