package createvillage

import (
	"errors"
	"fmt"
	"github.com/charmbracelet/bubbles/cursor"
	"github.com/charmbracelet/bubbles/textinput"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
	vcc "github.com/pokotsun/werewolf-game/pkg/client/createvillage"
	vjc "github.com/pokotsun/werewolf-game/pkg/client/entervillage"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/pokotsun/werewolf-game/ui/components/errormsg"
	loggertype "github.com/pokotsun/werewolf-game/ui/components/logger"
	"github.com/pokotsun/werewolf-game/ui/components/qustionitem"
	"github.com/pokotsun/werewolf-game/ui/constants"
	"github.com/pokotsun/werewolf-game/ui/context"
	"strconv"
	"strings"
	"time"
)

var (
	headingStyle = lipgloss.NewStyle().
			Bold(true).
			PaddingBottom(1)

	focusedStyle = lipgloss.NewStyle().
			Foreground(lipgloss.Color("205"))
	blurredStyle = lipgloss.NewStyle().
			Foreground(lipgloss.Color("240"))
	cursorStyle = focusedStyle
	noStyle     = lipgloss.NewStyle()

	// エラーメッセージのスタイル
	errStyle = lipgloss.NewStyle().
			Foreground(lipgloss.Color("110")).
			PaddingLeft(2)

	focusedButton = focusedStyle.Render("[ Submit ]")
	blurredButton = fmt.Sprintf("[ %s ]", blurredStyle.Render("Submit"))
)

type Model struct {
	ctx            *context.ProgramContext
	villageCreator vcc.VillageCreator
	focusIndex     int
	inputs         []qustionitem.QuestionItem
	errorMsg       *errormsg.ErrorMessage
}

func validateNotBlank(input string) error {
	if input == "" {
		return errors.New("input must not be empty")
	}
	return nil
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

func NewModel(
	ctx *context.ProgramContext,
	villageCreator vcc.VillageCreator,
	villageJoiner vjc.VillageJoiner,
) Model {
	m := Model{
		ctx:            ctx,
		villageCreator: villageCreator,
		focusIndex:     0,
		inputs:         make([]qustionitem.QuestionItem, 9),
	}

	var qItem qustionitem.QuestionItem
	for i := range m.inputs {
		qItem = qustionitem.New()
		qItem.Cursor.SetMode(cursor.CursorBlink)
		qItem.Cursor.Style = cursorStyle
		qItem.CharLimit = 32

		switch i {
		case 0:
			qItem.Question = "What is your village name?"
			qItem.Placeholder = "Village Name"
			qItem.Focus()
			qItem.PromptStyle = focusedStyle
			qItem.TextStyle = focusedStyle
			qItem.Validate = validateNotBlank
		case 1:
			qItem.Question = "What is your village password?"
			qItem.Placeholder = "Village Password"
			qItem.EchoMode = textinput.EchoPassword
			qItem.EchoCharacter = '•'
			qItem.Validate = validatePassword
		case 2:
			qItem.Question = "How many citizens are there?"
			qItem.Placeholder = "Citizen Count"
			qItem.CharLimit = 1
			qItem.Validate = validateNumber
		case 3:
			qItem.Question = "How many werewolves are there?"
			qItem.Placeholder = "Werewolf Count"
			qItem.CharLimit = 1
			qItem.Validate = validateNumber
		case 4:
			qItem.Question = "How many fortune tellers are there?"
			qItem.Placeholder = "Fortune Teller Count"
			qItem.CharLimit = 1
			qItem.Validate = validateNumber
		case 5:
			qItem.Question = "How many knights are there?"
			qItem.Placeholder = "Knight Count"
			qItem.CharLimit = 1
			qItem.Validate = validateNumber
		case 6:
			qItem.Question = "How many madmen are there?"
			qItem.Placeholder = "Madman Count"
			qItem.CharLimit = 1
			qItem.Validate = validateNumber
		case 7:
			qItem.Question = "GameMaster is you. What is your name?"
			qItem.Placeholder = "GameMaster Name"
			qItem.CharLimit = 64
			qItem.Validate = validateNotBlank
		case 8:
			qItem.Question = "What is GameMaster password?"
			qItem.Placeholder = "GameMaster Password"
			qItem.EchoMode = textinput.EchoPassword
			qItem.EchoCharacter = '•'
			qItem.Validate = validatePassword
		}

		m.inputs[i] = qItem
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
					if input.Err != nil {
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
						req := vcc.CreateVillageRequest{
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

						joinedVillage := domain.NewJoinedVillageAsGameMaster(
							&domain.Village{
								Id:                    v.Id,
								Name:                  villageName,
								CitizenCount:          citizenCount,
								WerewolfCount:         werewolfCount,
								FortuneTellerCount:    fortuneTellerCount,
								KnightCount:           knightCount,
								MadmanCount:           madmanCount,
								IsInitialActionActive: true,
							},
							villagePassword,
							// TODO: userId を受け取るようにする
							"",
							gameMasterName,
							gameMasterPassword,
						)

						m.ctx.JoinedVillage = &joinedVillage

						m.errorMsg = errormsg.NewErrorMessage("Village: " + v.Id + " created successfully!")
						return loggertype.LogMsg{
							Entry: loggertype.LogEntry{
								Message: fmt.Sprintf("Village %v created successfully!", joinedVillage.Id),
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

	b.WriteString(constants.TitleStyle.Render("Creating New Village"))
	b.WriteString("\n\n")

	for i := range m.inputs {
		input := m.inputs[i]
		b.WriteString(headingStyle.Render(input.Question) + "\n")
		b.WriteString(input.View())
		if input.Err != nil {
			b.WriteString("\n" + errStyle.Render(input.Err.Error()))
		}
		if i < len(m.inputs)-1 {
			b.WriteString("\n\n")
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
