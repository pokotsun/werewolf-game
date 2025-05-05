package domain

type JoinedVillage struct {
	Village
	VillagePassword  string
	YourUserName     string
	YourUserPassword string
	AreYouGameMaster bool
}

func NewJoinedVillageAsGameMaster(
	village *Village,
	villagePassword string,
	gameMasterName string,
	gameMasterPassword string,
) JoinedVillage {
	return JoinedVillage{
		Village:          *village,
		VillagePassword:  villagePassword,
		YourUserName:     gameMasterName,
		YourUserPassword: gameMasterPassword,
		AreYouGameMaster: true,
	}
}

func NewJoinedVillageAsOthers(
	village *Village,
	villagePassword string,
	userName string,
	userPassword string,
) JoinedVillage {
	return JoinedVillage{
		Village:          *village,
		VillagePassword:  villagePassword,
		YourUserName:     userName,
		YourUserPassword: userPassword,
		AreYouGameMaster: false,
	}
}
