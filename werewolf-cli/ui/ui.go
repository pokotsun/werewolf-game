package ui

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/client"
	"github.com/pokotsun/werewolf-game/ui/components/createvillage"
	loggertype "github.com/pokotsun/werewolf-game/ui/components/logger"
	"github.com/pokotsun/werewolf-game/ui/components/welcome"
	"github.com/pokotsun/werewolf-game/ui/context"
	"github.com/pokotsun/werewolf-game/ui/navigation"
	"strings"
)

type Model struct {
	viewState     navigation.ViewState
	context       *context.ProgramContext
	welcomePage   welcome.Model
	createVillage createvillage.Model
	logger        loggertype.Model
}

func NewModel(serverClient *client.WerewolfServerClient) Model {
	ctxt := context.NewProgramContext(serverClient)
	return Model{
		viewState:     navigation.WelcomeView,
		welcomePage:   welcome.NewModel(ctxt),
		createVillage: createvillage.NewModel(ctxt),
		logger:        loggertype.NewModel(),
	}
}

func (m Model) Init() tea.Cmd {
	var cmds []tea.Cmd
	cmds = append(cmds, tea.SetWindowTitle("Werewolf Game CLI"))

	switch m.viewState {
	case navigation.WelcomeView:
		cmds = append(cmds, m.welcomePage.Init())
	case navigation.CreateVillage:
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
	case navigation.Msg:
		m.viewState = msg.(navigation.Msg).Destination
	case loggertype.LogMsg, loggertype.ClearLogMsg:
		model, cmd := m.logger.Update(msg)
		m.logger = model.(loggertype.Model)
		return m, cmd
	}

	switch m.viewState {
	case navigation.WelcomeView:
		model, cmd := m.welcomePage.Update(msg)
		m.welcomePage = model.(welcome.Model)
		return m, cmd
	case navigation.CreateVillage:
		model, cmd := m.createVillage.Update(msg)
		m.createVillage = model.(createvillage.Model)
		return m, cmd
	}
	return m, nil
}

func (m Model) View() string {
	var b strings.Builder

	switch m.viewState {
	case navigation.WelcomeView:
		b.WriteString(m.welcomePage.View())
	case navigation.CreateVillage:
		b.WriteString(m.createVillage.View())
	default:
		s := strings.Builder{}
		s.WriteString("Selected Village\n\n")
		s.WriteString(fmt.Sprintf("Stage is on %v\n", m.viewState))
		s.WriteString("\n")
		s.WriteString("\n(press q to quit)\n")

		b.WriteString(s.String())
	}

	b.WriteString(m.logger.View())

	return b.String()
}
