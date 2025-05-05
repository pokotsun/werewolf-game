package loggertype

import (
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
	"strings"
	"time"
)

type LogLevel int

const (
	Info LogLevel = iota
	Error
	Debug
)

type ClearLogMsg struct{}

type LogEntry struct {
	Message   string
	Level     LogLevel
	Timestamp time.Time
}

// LogMsg bubbletea Cmd として利用するためのメッセージ
type LogMsg struct {
	Entry LogEntry
}

var (
	InfoStyle = lipgloss.NewStyle().
			Foreground(lipgloss.Color("#FFFFFF"))

	ErrorStyle = lipgloss.NewStyle().
			Foreground(lipgloss.Color("#FF0000"))

	DebugStyle = lipgloss.NewStyle().
			Foreground(lipgloss.Color("#00FFFF"))

	LogContainerStyle = lipgloss.NewStyle().
				Border(lipgloss.NormalBorder()).
				BorderForeground(lipgloss.Color("#555555")).
				Padding(0, 1).
				Width(80)
)

type Model struct {
	Logs         []LogEntry
	MaxLogs      int
	IsVisible    bool
	ScrollOffset int
}

func NewModel() Model {
	return Model{
		Logs:      make([]LogEntry, 0),
		MaxLogs:   100,
		IsVisible: true,
	}
}

func (m *Model) addLog(message string, level LogLevel) {
	entry := LogEntry{
		Message:   message,
		Level:     level,
		Timestamp: time.Now(),
	}

	// 最大ログ数を超えたら古いものを削除
	if len(m.Logs) >= m.MaxLogs {
		m.Logs = m.Logs[1:]
	}
	// 古いログを削除
	for i := 0; i < len(m.Logs); i++ {
		if time.Since(m.Logs[i].Timestamp) > 5*time.Second {
			m.Logs = append(m.Logs[:i], m.Logs[i+1:]...)
			i-- // インデックスを調整
		}
	}

	m.Logs = append(m.Logs, entry)
}

func (m Model) Init() tea.Cmd {
	return nil
}

func (m Model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case LogMsg:
		m.addLog(msg.Entry.Message, msg.Entry.Level)
		return m, tea.Tick(5*time.Second, func(_ time.Time) tea.Msg {
			return ClearLogMsg{}
		})
	case ClearLogMsg:
		m.Logs = []LogEntry{}
	}
	return m, nil
}

// View はロガーのビューを返します
func (m Model) View() string {
	if !m.IsVisible || len(m.Logs) == 0 {
		return ""
	}

	var b strings.Builder
	b.WriteString("Log Messages:\n")

	// 表示するログの範囲を決定（スクロール対応）
	startIdx := 0
	if len(m.Logs) > 5 {
		startIdx = len(m.Logs) - 5
	}

	// ログエントリを表示
	for i := startIdx; i < len(m.Logs); i++ {
		entry := m.Logs[i]
		timeStr := formatTimeStamp(entry.Timestamp)
		logStyle := getLevelStyle(entry.Level)

		// タイムスタンプ + メッセージ
		formattedLog := lipgloss.JoinHorizontal(
			lipgloss.Left,
			"["+timeStr+"] ",
			logStyle.Render(entry.Message),
		)

		b.WriteString(formattedLog + "\n")
	}

	return LogContainerStyle.Render(b.String())
}

func getLevelStyle(level LogLevel) lipgloss.Style {
	switch level {
	case Info:
		return InfoStyle
	case Error:
		return ErrorStyle
	case Debug:
		return DebugStyle
	default:
		return InfoStyle
	}
}

func formatTimeStamp(t time.Time) string {
	return t.Format("15:04:05")
}
