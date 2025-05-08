package domain

type JoinedVillage struct {
	Village
	VillagePassword  string
	YourUserId       string
	YourUserName     string
	YourUserPassword string
	AreYouGameMaster bool
}

func NewJoinedVillageAsGameMaster(
	village *Village,
	villagePassword string,
	gameMasterId string,
	gameMasterName string,
	gameMasterPassword string,
) JoinedVillage {
	return JoinedVillage{
		Village:          *village,
		VillagePassword:  villagePassword,
		YourUserId:       gameMasterId,
		YourUserName:     gameMasterName,
		YourUserPassword: gameMasterPassword,
		AreYouGameMaster: true,
	}
}

func NewJoinedVillageAsOthers(
	village *Village,
	villagePassword string,
	userId string,
	userName string,
	userPassword string,
) JoinedVillage {
	return JoinedVillage{
		Village:          *village,
		VillagePassword:  villagePassword,
		YourUserId:       userId,
		YourUserName:     userName,
		YourUserPassword: userPassword,
		AreYouGameMaster: false,
	}
}
