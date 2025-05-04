package welcome

import (
	"github.com/charmbracelet/bubbles/list"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/ui/constants"
	"github.com/pokotsun/werewolf-game/ui/context"
	"github.com/pokotsun/werewolf-game/ui/navigation"
	"strings"
)

type Choice int

const (
	CreateVillage = iota
	EnterVillage
)

type item struct {
	title, desc string
	choice      Choice
}

func (i item) Title() string       { return i.title }
func (i item) Description() string { return i.desc }
func (i item) FilterValue() string { return i.title }

var items = []list.Item{
	item{"Create Village", "ゲームマスターとして村を作成します", CreateVillage},
	item{"Enter Village", "ゲームマスターが既に作成した村に村人として参加します", EnterVillage},
}

type Model struct {
	ctx  *context.ProgramContext
	list list.Model
}

func NewModel(ctx *context.ProgramContext) Model {
	l := list.New(items, list.NewDefaultDelegate(), 0, 0)
	l.SetShowPagination(false)
	l.SetFilteringEnabled(false)
	l.SetShowTitle(false)
	l.SetShowStatusBar(false)

	return Model{ctx: ctx, list: l}
}

func (m Model) Init() tea.Cmd {
	return nil
}

func (m Model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
			return m, tea.Quit
		case "enter":
			return m, func() tea.Msg {
				selectedChoice := m.list.SelectedItem().(item).choice

				var dest navigation.ViewState
				switch selectedChoice {
				case CreateVillage:
					dest = navigation.CreateVillage
				case EnterVillage:
					// 村人として参加する場合は、まず村のリストを表示する
					dest = navigation.ListVillages
				}

				return navigation.Msg{
					Destination: dest,
				}
			}
		}
	case tea.WindowSizeMsg:
		w, h := constants.DocStyle.GetFrameSize()
		m.list.SetSize(msg.Width-w, msg.Height-h-2)
	}

	var cmd tea.Cmd
	m.list, cmd = m.list.Update(msg)
	return m, cmd
}

func (m Model) View() string {
	s := strings.Builder{}
	s.WriteString(constants.TitleStyle.Render("Welcome to Werewolf Game CLI"))
	s.WriteString("\n\n")
	s.WriteString(constants.DocStyle.Render(m.list.View()))

	return s.String()
}
