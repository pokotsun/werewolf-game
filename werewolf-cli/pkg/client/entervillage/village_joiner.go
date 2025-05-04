package client

type EnterVillageRequest struct {
	VillageId       string
	VillagePassword string
	UserName        string
	UserPassword    string
}

type EnterVillageResponse struct {
	VillageId string
	UserId    string
}

type VillageJoiner interface {
	// returns the village ID
	enterVillage(request EnterVillageRequest) (EnterVillageResponse, error)
}
