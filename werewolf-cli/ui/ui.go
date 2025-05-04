package ui

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/client"
	cc "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	lvmc "github.com/pokotsun/werewolf-game/pkg/client/listvillages"
	"github.com/pokotsun/werewolf-game/ui/components/createvillage"
	"github.com/pokotsun/werewolf-game/ui/components/listvillages"
	loggertype "github.com/pokotsun/werewolf-game/ui/components/logger"
	"github.com/pokotsun/werewolf-game/ui/components/welcome"
	"github.com/pokotsun/werewolf-game/ui/context"
	"github.com/pokotsun/werewolf-game/ui/navigation"
	"strings"
)

type Model struct {
	viewState         navigation.ViewState
	context           *context.ProgramContext
	welcomePage       welcome.Model
	createVillagePage createvillage.Model
	listVillagesPage  listvillages.Model
	logger            loggertype.Model
}

func NewModel(serverClient *client.WerewolfServerClient) Model {
	ctxt := context.NewProgramContext()
	var villageCreator cc.VillageCreator = serverClient
	var villageListMaker lvmc.VillageListMaker = serverClient
	return Model{
		viewState:         navigation.WelcomeView, // 初期状態
		welcomePage:       welcome.NewModel(ctxt),
		createVillagePage: createvillage.NewModel(ctxt, villageCreator),
		listVillagesPage:  listvillages.NewModel(ctxt, villageListMaker),
		logger:            loggertype.NewModel(),
	}
}

func (m Model) Init() tea.Cmd {
	var cmds []tea.Cmd
	cmds = append(cmds, tea.SetWindowTitle("Werewolf Game CLI"))

	// 初期ページの初期化
	cmds = append(cmds, m.welcomePage.Init())

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
		// 画面遷移時には、WindowSize の更新も行う
		cmds := []tea.Cmd{tea.WindowSize()}
		m.viewState = msg.(navigation.Msg).Destination
		switch m.viewState {
		case navigation.WelcomeView:
			cmds = append(cmds, m.welcomePage.Init())
		case navigation.CreateVillage:
			cmds = append(cmds, m.createVillagePage.Init())
		case navigation.ListVillages:
			cmds = append(cmds, m.listVillagesPage.Init())
		default:
			panic("unhandled default case")
		}
		return m, tea.Batch(cmds...)
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
		model, cmd := m.createVillagePage.Update(msg)
		m.createVillagePage = model.(createvillage.Model)
		return m, cmd
	case navigation.ListVillages:
		model, cmd := m.listVillagesPage.Update(msg)
		m.listVillagesPage = model.(listvillages.Model)
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
		b.WriteString(m.createVillagePage.View())
	case navigation.ListVillages:
		b.WriteString(m.listVillagesPage.View())
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
