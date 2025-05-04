package navigation

type ViewState int

const (
	WelcomeView = iota
	CreateVillage
	EnterVillage
)

type Msg struct {
	Destination ViewState
	Data        interface{}
}
