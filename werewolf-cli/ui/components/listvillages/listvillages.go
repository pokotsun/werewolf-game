package listvillages

import (
	"fmt"
	"github.com/charmbracelet/bubbles/list"
	tea "github.com/charmbracelet/bubbletea"
	client "github.com/pokotsun/werewolf-game/pkg/client/listvillages"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	loggertype "github.com/pokotsun/werewolf-game/ui/components/logger"
	"github.com/pokotsun/werewolf-game/ui/constants"
	"github.com/pokotsun/werewolf-game/ui/context"
	"strings"
	"time"
)

type fetchListItemsMsg struct {
	items []list.Item
}

type item struct {
	domain.Village
}

func (i item) Title() string {
	return fmt.Sprintf("%s", i.Name)
}

func (i item) Description() string {
	b := strings.Builder{}
	b.WriteString(fmt.Sprintf("村加入状況: %v / %v", i.CurrentUserNumber, i.GetTotalMemberCount()))
	b.WriteString("\n")
	b.WriteString(fmt.Sprintf("初日咬みあり: %v", i.IsInitialActionActive))
	b.WriteString("\n")
	b.WriteString(
		fmt.Sprintf(
			"🧑: %v, 🐺: %v, 🔮: %v, 🛡️: %v, 👁️: %v, 😈: %v",
			i.CitizenCount,
			i.WerewolfCount,
			i.FortuneTellerCount,
			i.KnightCount,
			i.PsychicCount,
			i.MadmanCount,
		),
	)

	return b.String()
}

func (i item) FilterValue() string {
	return i.Name
}

type Model struct {
	ctx              *context.ProgramContext
	villageListMaker client.VillageListMaker
	list             list.Model
}

func NewModel(ctx *context.ProgramContext, villageListMaker client.VillageListMaker) Model {
	l := newListModel()

	return Model{
		ctx:              ctx,
		villageListMaker: villageListMaker,
		list:             l,
	}
}

func newListModel() list.Model {
	delegate := list.NewDefaultDelegate()
	// 複数行の表示を有効にする
	delegate.Styles.SelectedDesc.Height(3)
	delegate.Styles.DimmedDesc.Height(3)
	delegate.SetHeight(4) // 説明文の行数に併せて調整

	l := list.New([]list.Item{}, delegate, 0, 0)
	l.Title = "Your join able Villages"
	l.SetStatusBarItemName("village", "villages")
	l.SetFilteringEnabled(false)

	return l
}

func (m Model) Init() tea.Cmd {
	return fetchCurrentVillageListCmd(m.villageListMaker)
}

func (m Model) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	var cmds []tea.Cmd
	switch msg := msg.(type) {
	case tea.WindowSizeMsg:
		w, h := constants.DocStyle.GetFrameSize()
		m.list.SetSize(msg.Width-w, msg.Height-h-2)
	case fetchListItemsMsg:
		m.list.SetItems(msg.items)
		cmds = append(cmds,
			// 10秒ごとに村一覧を更新する関数を実行
			tea.Tick(1*time.Second, func(t time.Time) tea.Msg {
				return fetchCurrentVillageListCmd(m.villageListMaker)()
			}),
		)
	}

	l, cmd := m.list.Update(msg)
	m.list = l
	cmds = append(cmds, cmd)
	return m, tea.Batch(cmds...)
}

func (m Model) View() string {
	s := strings.Builder{}
	s.WriteString(m.list.View())

	return s.String()
}

func fetchCurrentVillageListCmd(villageListMaker client.VillageListMaker) tea.Cmd {
	return func() tea.Msg {
		villages, err := villageListMaker.ListVillages()
		if err != nil {
			return loggertype.LogMsg{
				Entry: loggertype.LogEntry{
					Message: fmt.Sprintf("村一覧の取得に失敗: %v", err.Error()),
					Level:   loggertype.Error,
				},
			}
		}

		items := make([]list.Item, len(villages))
		for i, v := range villages {
			items[i] = item{Village: *v}
		}

		return fetchListItemsMsg{items: items}
	}
}
