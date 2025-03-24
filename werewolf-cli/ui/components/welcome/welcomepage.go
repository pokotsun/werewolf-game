package welcome

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
	"strings"
)

var (
	titleStyle        = lipgloss.NewStyle().MarginLeft(2)
	selectedItemStyle = lipgloss.NewStyle().PaddingLeft(2).Foreground(lipgloss.Color("170"))
	itemStyle         = lipgloss.NewStyle().PaddingLeft(4)
	quitTextStyle     = lipgloss.NewStyle().Margin(1, 0, 2, 4)
)

type Choice int

const (
	CreateVillage = iota
	EnterVillage
)

var choices = []string{
	"Create Village",
	"Enter Village",
}

type Msg struct {
	Choice Choice
}

type Model struct {
	choice Choice
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
				return Msg{Choice: m.choice}
			}
		case "down", "j":
			m.choice++
			if int(m.choice) >= len(choices) {
				m.choice = 0
			}
			return m, nil

		case "up", "k":
			m.choice--
			if m.choice < 0 {
				m.choice = Choice(len(choices) - 1)
			}
			return m, nil
		}
	}
	return m, nil
}

func (m Model) View() string {
	s := strings.Builder{}
	s.WriteString(titleStyle.Render("Welcome to Werewolf Game CLI"))
	s.WriteString("\n\n")

	for i := 0; i < len(choices); i++ {
		if int(m.choice) == i {
			s.WriteString(selectedItemStyle.Render(fmt.Sprintf("> %v", choices[i])))
		} else {
			s.WriteString(itemStyle.Render(fmt.Sprintf("  %v", choices[i])))
		}
		s.WriteString("\n")
	}

	s.WriteString(quitTextStyle.Render("\n(press q to quit)\n"))

	return s.String()
}
