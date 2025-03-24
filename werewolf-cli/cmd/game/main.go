package main

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/ui"
	"os"
)

func main() {
	uiModel := ui.NewModel()
	if _, err := tea.NewProgram(
		uiModel,
		tea.WithInput(os.Stdin),
		tea.WithOutput(os.Stdout),
	).Run(); err != nil {
		fmt.Printf("error starting program: %v\n", err)
		os.Exit(1)
	}
}
