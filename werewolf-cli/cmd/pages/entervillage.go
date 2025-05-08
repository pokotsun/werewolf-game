package main

import (
	"context"
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/client"
	vjc "github.com/pokotsun/werewolf-game/pkg/client/entervillage"
	"github.com/pokotsun/werewolf-game/pkg/domain"
	"github.com/pokotsun/werewolf-game/ui/components/entervillage"
	uictxt "github.com/pokotsun/werewolf-game/ui/context"
	"google.golang.org/grpc"
	"log"
	"os"
	"time"
)

type enterModel struct {
	page entervillage.Model
}

func (m enterModel) Init() tea.Cmd {
	return m.page.Init()
}

func (m enterModel) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
			return m, tea.Quit
		}
	}

	var cmds []tea.Cmd
	newPage, cmd := m.page.Update(msg)
	m.page = newPage.(entervillage.Model)

	cmds = append(cmds, cmd)

	return m, tea.Batch(cmds...)
}

func (m enterModel) View() string {
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
	var vc vjc.VillageJoiner = c

	programContext := uictxt.ProgramContext{
		JoinedVillage: &domain.JoinedVillage{
			Village: domain.Village{
				Id:                    "TEST VILLAGE ID",
				Name:                  "TEST VILLAGE NAME",
				CitizenCount:          4,
				WerewolfCount:         2,
				FortuneTellerCount:    1,
				KnightCount:           1,
				PsychicCount:          1,
				MadmanCount:           1,
				IsInitialActionActive: false,
				CurrentUserNumber:     2,
			},
			VillagePassword:  "",
			YourUserName:     "",
			YourUserPassword: "",
			AreYouGameMaster: false,
		},
	}

	m := enterModel{
		page: entervillage.NewModel(&programContext, vc),
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
