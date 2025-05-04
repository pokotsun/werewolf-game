package navigation

type ViewState int

const (
	WelcomeView = iota
	CreateVillage
	ListVillages
)

type Msg struct {
	Destination ViewState
	Data        interface{}
}
