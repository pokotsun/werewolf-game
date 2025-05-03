package errormsg

import (
	"github.com/charmbracelet/lipgloss"
	"time"
)

type ErrorMessage struct {
	Content   string
	Timestamp time.Time
	Visible   bool
}

type ClearErrorMsg struct{}

func NewErrorMessage(content string) *ErrorMessage {
	return &ErrorMessage{
		Content:   content,
		Timestamp: time.Now(),
		Visible:   true,
	}
}

var ErrorStyle = lipgloss.NewStyle().
	Foreground(lipgloss.Color("#FF0000")).
	Background(lipgloss.Color("#FFEEEE")).
	Padding(1).
	Border(lipgloss.NormalBorder()).
	BorderForeground(lipgloss.Color("#FF0000")).
	Width(60)

func (e ErrorMessage) Render() string {
	if e.Visible {
		return ErrorStyle.Render(e.Content)
	}
	return ""
}

func (e ErrorMessage) IsExpired() bool {
	return time.Since(e.Timestamp) > 5*time.Second
}
