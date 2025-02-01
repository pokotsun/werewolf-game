package domain

type Village struct {
	Id                    *string
	Name                  *string
	CitizenCount          int32
	werewolfCount         int32
	fortuneTellerCount    int32
	knightCount           int32
	psychicCount          int32
	madmanCount           int32
	isInitialActionActive bool
}
