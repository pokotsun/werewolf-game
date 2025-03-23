package main

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"os"
)

type Model struct{}

func (m Model) Init() tea.Cmd {
	return tea.SetWindowTitle("Werewolf Game CLI")
}

func (m Model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg.(type) {
	case tea.KeyMsg:
		return m, tea.Quit
	}
	return m, nil
}

func (m Model) View() string {
	return "\nPress any key to quit."
}

func main() {
	if _, err := tea.NewProgram(Model{}).Run(); err != nil {
		fmt.Printf("error starting program: %v\n", err)
		os.Exit(1)
	}
}
