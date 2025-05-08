package entervillage

import (
	"fmt"
	"github.com/charmbracelet/bubbles/cursor"
	"github.com/charmbracelet/bubbles/textinput"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
	client "github.com/pokotsun/werewolf-game/pkg/client/entervillage"
	loggertype "github.com/pokotsun/werewolf-game/ui/components/logger"
	"github.com/pokotsun/werewolf-game/ui/components/qustionitem"
	"github.com/pokotsun/werewolf-game/ui/constants"
	"github.com/pokotsun/werewolf-game/ui/context"
	"strings"
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
	ctx           *context.ProgramContext
	villageJoiner client.VillageJoiner
	focusIndex    int
	inputs        []qustionitem.QuestionItem
}

func validateNotBlank(input string) error {
	if input == "" {
		return fmt.Errorf("input must not be empty")
	}
	return nil
}

func validatePassword(input string) error {
	if input == "" || len(input) < 4 {
		return fmt.Errorf("password must not be empty, and be at least 4 characters long")
	}
	return nil
}

func NewModel(
	ctx *context.ProgramContext,
	villageJoiner client.VillageJoiner,
) Model {
	m := Model{
		ctx:           ctx,
		villageJoiner: villageJoiner,
		focusIndex:    0,
		inputs:        make([]qustionitem.QuestionItem, 3),
	}

	var qItem qustionitem.QuestionItem
	joiningVillage := ctx.JoinedVillage
	for i := range m.inputs {
		qItem = qustionitem.New()
		qItem.Cursor.SetMode(cursor.CursorBlink)
		qItem.Cursor.Style = cursorStyle
		qItem.CharLimit = 32

		switch i {
		case 0:
			qItem.Question = "What is joining village password?"
			qItem.Placeholder = "Joining Village Password"
			qItem.EchoMode = textinput.EchoPassword
			qItem.EchoCharacter = '•'
			qItem.Focus()
			qItem.PromptStyle = focusedStyle
			qItem.TextStyle = focusedStyle
			qItem.Validate = validatePassword

		case 1:
			qItem.Question = fmt.Sprintf("What is your user name in %s village?", joiningVillage.Name)
			qItem.Placeholder = "Your User Name"
			qItem.Validate = validateNotBlank

		case 2:
			qItem.Question = "What is your user password?"
			qItem.Placeholder = "Your Password"
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
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
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
						joiningGamePassword := m.inputs[0].Value()
						userName := m.inputs[1].Value()
						userPassword := m.inputs[2].Value()

						req := client.EnterVillageRequest{
							VillageId:       m.ctx.JoinedVillage.Id,
							VillagePassword: joiningGamePassword,
							UserName:        userName,
							UserPassword:    userPassword,
						}

						_, err := m.villageJoiner.EnterVillage(req)
						if err != nil {
							return loggertype.LogMsg{
								Entry: loggertype.LogEntry{
									Message: fmt.Sprintf("Failed to enter village: %v", err.Error()),
									Level:   loggertype.Error,
								},
							}
						}

						// 村に参加した場合、村の情報を更新する
						// TODO UserId 情報を JoinedVillage に含めるようにする
						m.ctx.JoinedVillage.VillagePassword = joiningGamePassword
						m.ctx.JoinedVillage.YourUserName = userName
						m.ctx.JoinedVillage.YourUserPassword = userName

						return loggertype.LogMsg{
							Entry: loggertype.LogEntry{
								Message: fmt.Sprintf("Successfully entered village: %s", m.ctx.JoinedVillage.Name),
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
	b.WriteString(constants.TitleStyle.Render("Joining a Village"))
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

	return b.String()
}
