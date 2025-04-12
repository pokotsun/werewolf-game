package ui

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/ui/components/createvillage"
	"github.com/pokotsun/werewolf-game/ui/components/welcome"
	"strings"
)

type ViewState int

const (
	WelcomeView = iota
	CreateVillage
	EnterVillage
)

type Model struct {
	viewState     ViewState
	welcomePage   welcome.Model
	createVillage createvillage.Model
}

func NewModel() Model {
	return Model{
		viewState:     WelcomeView,
		welcomePage:   welcome.NewModel(),
		createVillage: createvillage.NewModel(),
	}
}

func (m Model) Init() tea.Cmd {
	var cmds []tea.Cmd
	cmds = append(cmds, tea.SetWindowTitle("Werewolf Game CLI"))

	switch m.viewState {
	case WelcomeView:
		cmds = append(cmds, m.welcomePage.Init())
	case CreateVillage:
		cmds = append(cmds, m.createVillage.Init())
	default:
		panic("unhandled default case")
	}

	return tea.Batch(cmds...)
}

func (m Model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {

	switch msg.(type) {
	case tea.KeyMsg:
		switch msg.(tea.KeyMsg).String() {
		case "q", "ctrl+c", "esc":
			return m, tea.Quit
		}
	case welcome.Msg:
		switch msg.(welcome.Msg).Choice {
		case welcome.CreateVillage:
			m.viewState = CreateVillage
		case welcome.EnterVillage:
			m.viewState = EnterVillage
		default:
			panic("unhandled default case")
		}
	}
	switch m.viewState {
	case WelcomeView:
		model, cmd := m.welcomePage.Update(msg)
		m.welcomePage = model.(welcome.Model)
		return m, cmd
	case CreateVillage:
		model, cmd := m.createVillage.Update(msg)
		m.createVillage = model.(createvillage.Model)
		return m, cmd
	}
	return m, nil
}

func (m Model) View() string {
	switch m.viewState {
	case WelcomeView:
		return m.welcomePage.View()
	case CreateVillage:
		return m.createVillage.View()
	default:
		s := strings.Builder{}
		s.WriteString("Selected Village\n\n")
		s.WriteString(fmt.Sprintf("Stage is on %v\n", m.viewState))
		s.WriteString("\n")
		s.WriteString("\n(press q to quit)\n")
		return s.String()
	}
}
