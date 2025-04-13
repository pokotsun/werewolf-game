package main

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/ui/components/createvillage"
	"os"
)

type createModel struct {
	page createvillage.Model
}

func (m createModel) Init() tea.Cmd {
	return nil
}

func (m createModel) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
			return m, tea.Quit
		}
	}

	var cmds []tea.Cmd
	newPage, cmd := m.page.Update(msg)
	m.page = newPage.(createvillage.Model)

	cmds = append(cmds, cmd)

	return m, tea.Batch(cmds...)
}

func (m createModel) View() string {
	return fmt.Sprintf(
		m.page.View(),
	)
}

func main() {
	m := createModel{
		page: createvillage.NewModel(),
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
