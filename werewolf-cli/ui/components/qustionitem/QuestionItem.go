package qustionitem

import (
	"github.com/charmbracelet/bubbles/textinput"
	tea "github.com/charmbracelet/bubbletea"
)

/**
 * QuestionItem
 * @description: 質問項目を表す構造体
 * @param question 質問文
 * @param textinput.Model textinput.Model
 */
type QuestionItem struct {
	Question string
	textinput.Model
}

func New() QuestionItem {
	return QuestionItem{
		Question: "",
		Model:    textinput.New(),
	}
}

func (m QuestionItem) Update(msg tea.Msg) (QuestionItem, tea.Cmd) {
	var cmd tea.Cmd
	m.Model, cmd = m.Model.Update(msg)
	return m, cmd
}
