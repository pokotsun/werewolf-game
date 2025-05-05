package main

import (
	"context"
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/client"
	"github.com/pokotsun/werewolf-game/ui/components/listvillages"
	context2 "github.com/pokotsun/werewolf-game/ui/context"
	"google.golang.org/grpc"
	"log"
	"os"
	"time"
)

type listVillagesModel struct {
	page listvillages.Model
}

func (m listVillagesModel) Init() tea.Cmd {
	return m.page.Init()
}

func (m listVillagesModel) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
			return m, tea.Quit
		}
	}

	var cmds []tea.Cmd
	newPage, cmd := m.page.Update(msg)
	m.page = newPage.(listvillages.Model)

	cmds = append(cmds, cmd)

	return m, tea.Batch(cmds...)
}

func (m listVillagesModel) View() string {
	return fmt.Sprintf(
		m.page.View(),
	)
}

func main() {
	// given:
	// コンテキストを作成し、タイムアウトを設定
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*10)
	defer cancel()

	// grpc.DialContext を使用して接続を確立
	conn, err := grpc.DialContext(ctx, "localhost:9090", grpc.WithInsecure(), grpc.WithBlock())
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer func(conn *grpc.ClientConn) {
		err := conn.Close()
		if err != nil {
			log.Fatalf("could not close connection: %v", err)
		}
	}(conn)

	c := client.NewWerewolfServerClient(conn)

	programContext := context2.ProgramContext{
		WerewolfClient: c,
	}
	m := listVillagesModel{
		page: listvillages.NewModel(&programContext),
	}

	if _, err := tea.NewProgram(
		m,
		tea.WithInput(os.Stdin),
		tea.WithOutput(os.Stdout),
	).Run(); err != nil {
		fmt.Printf("error starting program: %v\n", err)
		os.Exit(1)
	}
}
