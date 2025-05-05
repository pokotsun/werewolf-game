package context

import (
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/domain"
)

type State = int

const (
	TaskStart State = iota
	TaskFinished
	TaskError
)

type Task struct {
	State State
	Error error
}

type ProgramContext struct {
	JoinedVillage *domain.JoinedVillage
	CurrentTask   func(task Task) tea.Cmd
}

func NewProgramContext() *ProgramContext {
	return &ProgramContext{
		JoinedVillage: nil,
		CurrentTask:   nil,
	}
}
