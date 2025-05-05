package main

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/ui/components/welcome"
	"github.com/pokotsun/werewolf-game/ui/context"
	"os"
)

type model struct {
	page welcome.Model
}

func (m model) Init() tea.Cmd {
	return nil
}

func (m model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
			return m, tea.Quit
		}
	}

	var cmds []tea.Cmd
	newPage, cmd := m.page.Update(msg)
	m.page = newPage.(welcome.Model)

	cmds = append(cmds, cmd)

	return m, tea.Batch(cmds...)
}

func (m model) View() string {
	return fmt.Sprintf(
		m.page.View(),
	)
}

func main() {
	c := context.ProgramContext{
		WerewolfClient: nil,
	}
	m := model{
		page: welcome.NewModel(&c),
	}

	if _, err := tea.NewProgram(
		m,
		tea.WithInput(os.Stdin),
		tea.WithOutput(os.Stdout),
	).Run(); err != nil {
		fmt.Printf("error starting program: %v\n", err)
		os.Exit(1)
	}
}
