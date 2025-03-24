package welcome

import (
	tea "github.com/charmbracelet/bubbletea"
	"strings"
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

type ChildMsg struct {
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
				return ChildMsg{Choice: m.choice}
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
	s.WriteString("Welcome to Werewolf Game CLI\n\n")

	for i := 0; i < len(choices); i++ {
		if int(m.choice) == i {
			s.WriteString("> ")
		} else {
			s.WriteString("  ")
		}
		s.WriteString(choices[i])
		s.WriteString("\n")
	}

	s.WriteString("\n(press q to quit)\n")

	return s.String()
}
