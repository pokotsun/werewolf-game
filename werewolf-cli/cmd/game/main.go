package main

import (
	"context"
	"fmt"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/pokotsun/werewolf-game/pkg/client"
	"github.com/pokotsun/werewolf-game/ui"
	"google.golang.org/grpc"
	"log"
	"os"
	"time"
)

func main() {
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

	uiModel := ui.NewModel(c)
	if _, err := tea.NewProgram(
		uiModel,
		tea.WithInput(os.Stdin),
		tea.WithOutput(os.Stdout),
	).Run(); err != nil {
		fmt.Printf("error starting program: %v\n", err)
		os.Exit(1)
	}
}
