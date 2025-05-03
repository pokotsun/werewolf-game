package context

import (
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/client"
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
	WerewolfClient *client.WerewolfServerClient
	CurrentTask    func(task Task) tea.Cmd
}

func NewProgramContext(werewolfClient *client.WerewolfServerClient) *ProgramContext {
	return &ProgramContext{
		WerewolfClient: werewolfClient,
		CurrentTask:    nil,
	}
}
