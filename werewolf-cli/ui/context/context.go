package context

import (
	tea "github.com/charmbracelet/bubbletea"
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
	CurrentTask func(task Task) tea.Cmd
}

func NewProgramContext() *ProgramContext {
	return &ProgramContext{
		CurrentTask: nil,
	}
}
