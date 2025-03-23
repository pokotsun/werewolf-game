package main

import (
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/ui"
	"os"
)

func main() {
	uiModel := ui.Model{}
	if _, err := tea.NewProgram(uiModel).Run(); err != nil {
		fmt.Printf("error starting program: %v\n", err)
		os.Exit(1)
	}
}
