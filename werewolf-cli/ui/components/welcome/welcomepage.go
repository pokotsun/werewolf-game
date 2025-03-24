package welcome

import (
	"github.com/charmbracelet/bubbles/list"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
	"strings"
)

var (
	titleStyle = lipgloss.NewStyle().MarginLeft(2).Bold(true)
	docStyle   = lipgloss.NewStyle().Margin(1, 2)
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

type Msg struct {
	Choice Choice
}

type Model struct {
	list list.Model
}

func NewModel() Model {
	l := list.New(items, list.NewDefaultDelegate(), 0, 0)
	l.SetShowPagination(false)
	l.SetFilteringEnabled(false)
	l.SetShowTitle(false)
	l.SetShowStatusBar(false)

	return Model{list: l}
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
				return Msg{Choice: selectedChoice}
			}
		}
	case tea.WindowSizeMsg:
		h, v := docStyle.GetFrameSize()
		m.list.SetSize(msg.Width-h, msg.Height-v-2)
	}

	var cmd tea.Cmd
	m.list, cmd = m.list.Update(msg)
	return m, cmd
}

func (m Model) View() string {
	s := strings.Builder{}
	s.WriteString(titleStyle.Render("Welcome to Werewolf Game CLI"))
	s.WriteString("\n\n")
	s.WriteString(docStyle.Render(m.list.View()))

	return s.String()
}
