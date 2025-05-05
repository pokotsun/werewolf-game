package createvillage

import (
	"errors"
	"fmt"
	"github.com/charmbracelet/bubbles/cursor"
	"github.com/charmbracelet/bubbles/textinput"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
	client "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/pokotsun/werewolf-game/ui/components/errormsg"
	loggertype "github.com/pokotsun/werewolf-game/ui/components/logger"
	"github.com/pokotsun/werewolf-game/ui/context"
	"strconv"
	"strings"
	"time"
)

var (
	focusedStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("205"))
	blurredStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("240"))
	cursorStyle  = focusedStyle
	noStyle      = lipgloss.NewStyle()
	errStyle     = lipgloss.NewStyle().Foreground(lipgloss.Color("110")).PaddingLeft(2)

	focusedButton = focusedStyle.Render("[ Submit ]")
	blurredButton = fmt.Sprintf("[ %s ]", blurredStyle.Render("Submit"))
)

// Msg TODO #74 加入した村の状態を ProgramContext に保存する
type Msg struct {
	Village            domain.Village
	VillagePassword    string
	GameMasterName     string
	GameMasterPassword string
}

type Model struct {
	ctx            *context.ProgramContext
	villageCreator client.VillageCreator
	focusIndex     int
	inputs         []textinput.Model
	errorMsg       *errormsg.ErrorMessage
}

func validatePassword(input string) error {
	if input == "" || len(input) < 4 {
		return errors.New("password must not be empty, and be at least 4 characters long")
	}
	return nil
}

func validateNumber(input string) error {
	if _, err := strconv.Atoi(input); err != nil {
		return errors.New("number must not be empty, and be a numeric value")
	}
	return nil
}

func NewModel(ctx *context.ProgramContext, villageCreator client.VillageCreator) Model {
	m := Model{
		ctx:            ctx,
		villageCreator: villageCreator,
		focusIndex:     0,
		inputs:         make([]textinput.Model, 9),
	}

	var t textinput.Model
	for i := range m.inputs {
		t = textinput.New()
		t.Cursor.SetMode(cursor.CursorBlink)
		t.Cursor.Style = cursorStyle
		t.CharLimit = 32

		switch i {
		case 0:
			t.Placeholder = "Village Name"
			t.Focus()
			t.PromptStyle = focusedStyle
			t.TextStyle = focusedStyle
		case 1:
			t.Placeholder = "Village Password"
			t.EchoMode = textinput.EchoPassword
			t.EchoCharacter = '•'
			t.Validate = validatePassword
		case 2:
			t.Placeholder = "Citizen Count"
			t.CharLimit = 1
			t.Validate = validateNumber
		case 3:
			t.Placeholder = "Werewolf Count"
			t.CharLimit = 1
			t.Validate = validateNumber
		case 4:
			t.Placeholder = "Fortune Teller Count"
			t.CharLimit = 1
			t.Validate = validateNumber
		case 5:
			t.Placeholder = "Knight Count"
			t.CharLimit = 1
			t.Validate = validateNumber
		case 6:
			t.Placeholder = "Madman Count"
			t.CharLimit = 1
			t.Validate = validateNumber
		case 7:
			t.Placeholder = "GameMaster Name"
			t.CharLimit = 64
		case 8:
			t.Placeholder = "GameMaster Password"
			t.EchoMode = textinput.EchoPassword
			t.EchoCharacter = '•'
			t.Validate = validatePassword
		}

		m.inputs[i] = t
	}

	return m
}

func (m Model) Init() tea.Cmd {
	cmds := make([]tea.Cmd, len(m.inputs))
	cmds = append(cmds, textinput.Blink)
	return tea.Batch(cmds...)
}

func (m Model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case errormsg.ClearErrorMsg:
		m.errorMsg = nil
		return m, nil
	case tea.KeyMsg:
		switch msg.String() {
		case "ctrl+c", "esc":
			return m, tea.Quit

		// Set focus to next input
		case "tab", "shift+tab", "enter", "up", "down":
			s := msg.String()
			erroredFocusIndex := -1

			// submit button が押されたとき
			if s == "enter" && m.focusIndex == len(m.inputs) {
				for i, input := range m.inputs {
					if input.Err != nil || input.Value() == "" {
						erroredFocusIndex = i
						break
					}
				}
				if erroredFocusIndex < 0 {
					return m, func() tea.Msg {
						villageName := m.inputs[0].Value()
						villagePassword := m.inputs[1].Value()
						citizenCount, _ := strconv.Atoi(m.inputs[2].Value())
						werewolfCount, _ := strconv.Atoi(m.inputs[3].Value())
						fortuneTellerCount, _ := strconv.Atoi(m.inputs[4].Value())
						knightCount, _ := strconv.Atoi(m.inputs[5].Value())
						madmanCount, _ := strconv.Atoi(m.inputs[6].Value())
						gameMasterName := m.inputs[7].Value()
						gameMasterPassword := m.inputs[8].Value()

						// Create village
						req := client.CreateVillageRequest{
							Name:               villageName,
							CitizenCount:       citizenCount,
							WerewolfCount:      werewolfCount,
							FortuneTellerCount: fortuneTellerCount,
							KnightCount:        knightCount,
							MadmanCount:        madmanCount,
							Password:           villagePassword,
							GameMasterName:     gameMasterName,
							GameMasterPassword: gameMasterPassword,
						}
						v, err := m.villageCreator.CreateVillage(req)
						if err != nil {
							m.errorMsg = errormsg.NewErrorMessage(err.Error())
							return tea.Tick(5*time.Second, func(_ time.Time) tea.Msg {
								return errormsg.ClearErrorMsg{}
							})
						}

						msg := Msg{
							Village: domain.Village{
								Id:                    v.Id,
								Name:                  villageName,
								CitizenCount:          citizenCount,
								WerewolfCount:         werewolfCount,
								FortuneTellerCount:    fortuneTellerCount,
								KnightCount:           knightCount,
								MadmanCount:           madmanCount,
								IsInitialActionActive: true,
							},
							VillagePassword:    villagePassword,
							GameMasterName:     gameMasterName,
							GameMasterPassword: gameMasterPassword,
						}

						m.errorMsg = errormsg.NewErrorMessage("Village: " + v.Id + " created successfully!")
						return loggertype.LogMsg{
							Entry: loggertype.LogEntry{
								Message: fmt.Sprintf("Village %v created successfully!", msg.Village.Id),
								Level:   loggertype.Info,
							},
						}
					}
				}
			}

			// Cycle indexes
			if s == "up" || s == "shift+tab" {
				m.focusIndex--
			} else {
				m.focusIndex++
			}

			if m.focusIndex > len(m.inputs) {
				m.focusIndex = 0
			} else if m.focusIndex < 0 {
				m.focusIndex = len(m.inputs)
			}

			if erroredFocusIndex >= 0 {
				m.focusIndex = erroredFocusIndex
			}

			cmds := make([]tea.Cmd, len(m.inputs))
			for i := 0; i <= len(m.inputs)-1; i++ {
				if i == m.focusIndex {
					// Set focused state
					cmds[i] = m.inputs[i].Focus()
					m.inputs[i].PromptStyle = focusedStyle
					m.inputs[i].TextStyle = focusedStyle
				} else {
					// Remove focused state
					m.inputs[i].Blur()
					m.inputs[i].PromptStyle = noStyle
					m.inputs[i].TextStyle = noStyle
				}
			}

			return m, tea.Batch(cmds...)
		}
	}

	// Handle character input and blinking
	cmds := make([]tea.Cmd, len(m.inputs))

	// Only text inputs with Focus() set will respond, so it's safe to simply
	// update all of them here without any further logic.
	for i := range m.inputs {
		m.inputs[i], cmds[i] = m.inputs[i].Update(msg)
	}

	return m, tea.Batch(cmds...)
}

func (m Model) View() string {
	var b strings.Builder

	for i := range m.inputs {
		input := m.inputs[i]
		b.WriteString(input.View())
		if input.Err != nil {
			b.WriteString("\n" + errStyle.Render(input.Err.Error()))
		}
		if i < len(m.inputs)-1 {
			b.WriteRune('\n')
		}
	}

	button := &blurredButton
	if m.focusIndex == len(m.inputs) {
		button = &focusedButton
	}
	b.WriteString("\n\n")
	b.WriteString(*button)
	b.WriteString("\n\n")
	if m.errorMsg != nil {
		b.WriteString("\n")
		b.WriteString(m.errorMsg.Render())
	}

	return b.String()
}
