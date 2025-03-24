package main

import (
	"fmt"
	"github.com/charmbracelet/lipgloss"
)

func main() {
	// Paddingを設定したスタイル
	paddingStyle := lipgloss.NewStyle().
		Border(lipgloss.NormalBorder()).
		Padding(2, 4) // 上下に2ユニット、左右に4ユニットのPadding

	// Marginを設定したスタイル
	marginStyle := lipgloss.NewStyle().
		Border(lipgloss.NormalBorder()).
		Margin(2, 4) // 上下に2ユニット、左右に4ユニットのMargin

	// スタイルをテキストに適用
	paddingText := paddingStyle.Render("This text has padding")
	marginText := marginStyle.Render("This text has margin")

	// スタイルが適用されたテキストを表示
	fmt.Println(paddingText)
	fmt.Println(marginText)
}
