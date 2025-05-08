package client

import (
	"crypto/sha256"
	"encoding/json"
	"fmt"
)

type MockVillageJoiner struct{}

func (m *MockVillageJoiner) EnterVillage(request EnterVillageRequest) (*EnterVillageResponse, error) {
	jsonData, err := json.Marshal(request)
	if err != nil {
		fmt.Errorf("json marshal error: %v", err)
	}

	hash := sha256.Sum256(jsonData)
	hashString := fmt.Sprintf("%x", hash)

	res := EnterVillageResponse{
		VillageId: hashString,
		UserId:    hashString,
	}
	return &res, nil
}
